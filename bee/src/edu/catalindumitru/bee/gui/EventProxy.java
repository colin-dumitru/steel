package edu.catalindumitru.bee.gui;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/9/11
 * Time: 12:44 PM
 *
 * Class as an event handler layer. All events received to the {@link EventDispatcher} are passed through these proxies,
 * which may or may not handle the event.
 */
public interface EventProxy {
    /**
     * Priority is used to sort the proxies on the order in which they should be called to handle the event.
     * @return the priority of the event proxy.
     */
    public int priority();

    /**
     * Tells the proxy to handle the event.
     * @param event which event to be handled.
     * @return whether or not the event has been handled. If it returns true, then the remainder of proxies will not
     * be called.
     */
    public boolean handleEvent(UiEvent event);
}
