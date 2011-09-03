package edu.catalindumitru.bee.gui;

import edu.catalindumitru.bee.core.ActionDispatcher;
import edu.catalindumitru.bee.graphics.Render2DProvider;
import edu.catalindumitru.bee.input.InputManager;
import edu.catalindumitru.bee.input.InputObserver;
import edu.catalindumitru.bee.math.Rectangle;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/5/11
 * Time: 11:53 AM
 */
public class UiManager implements InputObserver {
    protected static UiManager instance = new UiManager();

    /*2d rendering provider*/
    protected Render2DProvider renderProvider;
    /*list of idle panels*/
    protected Map<String, Panel> panels;
    /*active panel*/
    protected Panel activePanel;

    /*proxy used to delegate events to the root widget*/
    protected EventDispatcher eventDispatcher;
    /*Proxy used to render root widget*/
    protected GraphicProxy graphicProxy;
    /*Proxy used to send events to the root widget*/
    protected WidgetProxy widgetProxy;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected UiManager() {
        this.panels = new TreeMap<String, Panel>();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void initialize() {
        /*add ourselves as a input observer*/
        InputManager.instance().addObserver(this);

        /*add the uit controller to the action dispatcher*/
        ActionDispatcher.instance().addController(new UiController());

        /*create supported widget builders*/
        WidgetFactory.instance().addBuilder(new Panel.Builder());
        WidgetFactory.instance().addBuilder(new Label.Builder());
        WidgetFactory.instance().addBuilder(new Button.Builder());

        /*create supported layouts*/
        LayoutFactory.instance().addLayout(new AbsoluteLayout.Creator());

        /*create event dispatcher*/
        this.getEventDispatcher();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static UiManager instance() {
        return instance;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Render2DProvider getRenderProvider() {
        return renderProvider;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setRenderProvider(Render2DProvider renderProvider) {
        this.renderProvider = renderProvider;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void addPanel(Panel panel) {
        this.panels.put(panel.getId(), panel);

        /*repack the panel to fit the current screen dimensions*/
        panel.setBounds(new Rectangle(this.renderProvider.getScreenWidth(), this.renderProvider.getScreenHeight()));
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removePanel(Panel panel) {
        this.panels.remove(panel.getId());

        if (this.activePanel == panel)
            this.switchActivePanel(null);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removePanel(String id) {
        if (this.activePanel == this.panels.remove(id))
            this.switchActivePanel(null);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void switchActivePanel(String id) {
        this.switchActivePanelImpl(this.panels.get(id));
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void switchActivePanelImpl(Panel panel) {
        this.activePanel = panel;

        /*reset root widget for event proxies*/
        this.graphicProxy.setRoot(this.activePanel);
        this.widgetProxy.setRoot(this.activePanel);

        /*switch and redraw active panel*/
        if (this.activePanel != null)
            this.activePanel.redraw();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void hideActivePanel() {
        this.switchActivePanel(null);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public EventDispatcher getEventDispatcher() {
        /*lazy initialisation*/
        if (this.eventDispatcher == null) {
            /*create new dispatcher and add all event proxies*/
            this.eventDispatcher = new EventDispatcher();

            /*render proxy*/
            this.graphicProxy = new GraphicProxy();
            this.graphicProxy.setProvider(this.renderProvider);
            this.graphicProxy.setRoot(this.activePanel);

            /*ui proxy*/
            this.widgetProxy = new WidgetProxy();
            this.widgetProxy.setRoot(this.activePanel);

            this.eventDispatcher.addProxy(this.graphicProxy);
            this.eventDispatcher.addProxy(this.widgetProxy);
        }

        return this.eventDispatcher;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setEventDispatcher(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onEvent(InputManager.EVENTS event, float[] params) {
        switch (event) {

            case E_MOUSE_CLICK:
                this.eventDispatcher.dispatchEvent(new UiEvent(new Rectangle(params[0], params[1], 1, 1), params,
                        UiEvent.TYPE.MOUSE_CLICK));
                break;
            case E_MOUSE_DOWN:
                this.eventDispatcher.dispatchEvent(new UiEvent(new Rectangle(params[0], params[1], 1, 1), params,
                        UiEvent.TYPE.MOUSE_DOWN));
                break;
            case E_MOUSE_UP:
                this.eventDispatcher.dispatchEvent(new UiEvent(new Rectangle(params[0], params[1], 1, 1), params,
                        UiEvent.TYPE.MOUSE_UP));
                break;
            case E_MOUSE_MOVE:
                this.eventDispatcher.dispatchEvent(new UiEvent(null, params, UiEvent.TYPE.MOUSE_MOVE));
                break;
            case E_MOUSE_WHEEL:
                this.eventDispatcher.dispatchEvent(new UiEvent(null, params, UiEvent.TYPE.MOUSE_WHEEL));
                break;
            case E_MOUSE_DOUBLE_CLICK:
                this.eventDispatcher.dispatchEvent(new UiEvent(new Rectangle(params[0], params[1], 1, 1), params,
                        UiEvent.TYPE.MOUSE_DOUBLE_CLICK));
                break;
            case E_KEY_UP:
                this.eventDispatcher.dispatchEvent(new UiEvent(null, params, UiEvent.TYPE.KEY_UP));
                break;
            case E_KEY_DOWN:
                this.eventDispatcher.dispatchEvent(new UiEvent(null, params, UiEvent.TYPE.KEY_DOWN));
                break;
        }
    }
}
