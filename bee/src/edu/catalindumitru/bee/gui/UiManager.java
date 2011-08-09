package edu.catalindumitru.bee.gui;

import edu.catalindumitru.bee.graphics.Render2DProvider;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/5/11
 * Time: 11:53 AM
 */
public class UiManager {
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

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected UiManager() {
        this.panels = new TreeMap<String, Panel>();
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
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removePanel(Panel panel) {
        this.panels.remove(panel.getId());
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removePanel(String id) {
        this.panels.remove(id);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void switchActivePanel(String id) {
        this.activePanel = this.panels.get(id);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void hideActivePanel() {
        this.activePanel = null;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public EventDispatcher getEventDispatcher() {
        /*lazy initialisation*/
        if(this.eventDispatcher == null) {
            /*create new dispatcher and add all event proxies*/
            this.eventDispatcher = new EventDispatcher();

            /*render proxy*/
            this.graphicProxy = new GraphicProxy();
            this.graphicProxy.setProvider(this.renderProvider);
            this.graphicProxy.setRoot(this.activePanel);

            this.eventDispatcher.addProxy(this.graphicProxy);
        }

        return  this.eventDispatcher;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setEventDispatcher(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }
}
