package edu.catalindumitru.bee.gui;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/26/11
 * Time: 9:18 AM
 */
public class WidgetProxy implements EventProxy {
    public static final int PRIORIY = 20;

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
        if (this.root == null)
            return false;

        this.root.onEvent(event);

        return false;

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
