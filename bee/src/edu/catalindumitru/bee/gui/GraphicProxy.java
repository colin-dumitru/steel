package edu.catalindumitru.bee.gui;

import edu.catalindumitru.bee.graphics.Render2DProvider;
import edu.catalindumitru.bee.math.Rectangle;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/9/11
 * Time: 1:00 PM
 */

/**
 * Event proxy which handles the redrawing and painting of a delegated widget. All events of type {@link UiEvent.TYPE#REDRAW_REGION}
 * will be checked if the region affected intersects the widget bounds, and then delegated to that widget.
 */
public class GraphicProxy implements EventProxy{
    public static final int PRIORIY = 10;

    /*graphic used to redraw the widgets*/
    protected Render2DProvider provider;
    /*root widget to be drawn*/
    protected Widget root;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Priority is used to sort the proxies on the order in which they should be called to handle the event.
     *
     * @return the priority of the event proxy.
     */
    @Override
    public int priority() {
        return PRIORIY;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Tells the proxy to handle the event.
     *
     * @param event which event to be handled.
     * @return whether or not the event has been handled. If it returns true, then the remainder of proxies will not
     *         be called.
     */
    @Override
    public boolean handleEvent(UiEvent event) {
        if(this.provider == null || this.root == null)
            return false;

        switch(event.getType()){
            /*a region of the root widget needs to be redrawn*/
            case REDRAW_REGION:
                return this.redrawRootWidget(event.getRegionAffected());
            case REDRAW_ALL:
                return this.redrawRootWidget();
        }

        return false;

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Tests whether the region affected intersects width the root widget bounds, and if so, redraws the widget.
     * @param region which region should be redrawn.
     * @return if the widget has been redrawn.
     */
    private boolean redrawRootWidget(Rectangle region) {
        if(this.root == null || this.provider == null)
            return false;

        if(region.intersects(this.root.bounds))
            this.root.draw(this.provider, region);

        return false;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private boolean redrawRootWidget() {
        if(this.root == null || this.provider == null)
            return false;

        this.root.draw(this.provider, null);

        return true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Render2DProvider getProvider() {
        return provider;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setProvider(Render2DProvider provider) {
        this.provider = provider;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Widget getRoot() {
        return root;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setRoot(Widget root) {
        this.root = root;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
