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


    /*2d rendering provider*/
    protected Render2DProvider renderProvider;
    /*list of idle panels*/
    protected Map<String, Panel> panels;
    /*active panel*/
    protected Panel activePanel;

    /*proxy used to delegate events to the root widget*/
    protected EventDispatcher eventDispatcher;
    /*used to dispatch actions to controllers*/
    protected ActionDispatcher actionDispatcher;
    /*Proxy used to render root widget*/
    protected GraphicProxy graphicProxy;
    /*Proxy used to send events to the root widget*/
    protected WidgetProxy widgetProxy;
    /*widget factory used to build widgets from xml*/
    protected WidgetFactory widgetFactory;

    /*cached ui event to minimize object creations*/
    protected UiEvent cachedUiEvent = new UiEvent();
    /*cached region rectangle to minimize object creations*/
    protected Rectangle cachedRectangle = new Rectangle();

    protected float cachedParams[] = {0, 0};

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public UiManager(Render2DProvider provider) {
        this.panels = new TreeMap<String, Panel>();
        this.renderProvider = provider;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void initialize() {
        this.widgetFactory = new WidgetFactory();

        /*create supported widget builders*/
        this.widgetFactory.addBuilder(new Panel.Builder());
        this.widgetFactory.addBuilder(new Label.Builder());
        this.widgetFactory.addBuilder(new Button.Builder());

        /*create supported layouts*/
        LayoutFactory.instance().addLayout(new AbsoluteLayout.Creator());

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

        /*set action dispatcher and event dispatcher*/
        panel.setEventDispatcher(this.eventDispatcher);
        panel.setActionDispatcher(this.actionDispatcher);

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

    public Panel getActivePanel() {
        return activePanel;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void hideActivePanel() {
        this.switchActivePanel(null);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public EventDispatcher getEventDispatcher() {
        return this.eventDispatcher;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setEventDispatcher(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public ActionDispatcher getActionDispatcher() {
        return actionDispatcher;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setActionDispatcher(ActionDispatcher actionDispatcher) {
        this.actionDispatcher = actionDispatcher;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public WidgetFactory getWidgetFactory() {
        return widgetFactory;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setWidgetFactory(WidgetFactory widgetFactory) {
        this.widgetFactory = widgetFactory;
    }


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onMouseMove(int x, int y) {
        this.cachedParams[0] = x;
        this.cachedParams[1] = y;

        this.cachedUiEvent.setAll(null, cachedParams, UiEvent.TYPE.MOUSE_MOVE);
        this.eventDispatcher.dispatchEvent(this.cachedUiEvent);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onClick(int x, int y) {
        this.cachedParams[0] = x;
        this.cachedParams[1] = y;

        this.cachedRectangle.setAll(x, y, 1, 1);
        this.cachedUiEvent.setAll(this.cachedRectangle, cachedParams, UiEvent.TYPE.MOUSE_CLICK);

        this.eventDispatcher.dispatchEvent(this.cachedUiEvent);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onDoubleClick(int x, int y) {
        this.cachedParams[0] = x;
        this.cachedParams[1] = y;

        this.cachedRectangle.setAll(x, y, 1, 1);
        this.cachedUiEvent.setAll(this.cachedRectangle, this.cachedParams, UiEvent.TYPE.MOUSE_DOUBLE_CLICK);

        this.eventDispatcher.dispatchEvent(this.cachedUiEvent);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onMouseUp(int x, int y) {
        this.cachedParams[0] = x;
        this.cachedParams[1] = y;

        this.cachedRectangle.setAll(x, y, 1, 1);
        this.cachedUiEvent.setAll(this.cachedRectangle, this.cachedParams, UiEvent.TYPE.MOUSE_UP);

        this.eventDispatcher.dispatchEvent(this.cachedUiEvent);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onMouseDown(int x, int y) {
        this.cachedParams[0] = x;
        this.cachedParams[1] = y;

        this.cachedRectangle.setAll(x, y, 1, 1);
        this.cachedUiEvent.setAll(this.cachedRectangle, this.cachedParams, UiEvent.TYPE.MOUSE_DOWN);

        this.eventDispatcher.dispatchEvent(this.cachedUiEvent);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onKeyUp(int key) {
        this.cachedParams[0] = key;

        this.cachedUiEvent.setAll(null, cachedParams, UiEvent.TYPE.KEY_UP);
        this.eventDispatcher.dispatchEvent(this.cachedUiEvent);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onKeyDown(int key) {
        this.cachedParams[0] = key;

        this.cachedUiEvent.setAll(null, cachedParams, UiEvent.TYPE.KEY_DOWN);
        this.eventDispatcher.dispatchEvent(this.cachedUiEvent);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onKeyPress(int key) {
        this.cachedParams[0] = key;

        this.cachedUiEvent.setAll(null, cachedParams, UiEvent.TYPE.KEY_PRESS);
        this.eventDispatcher.dispatchEvent(this.cachedUiEvent);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
