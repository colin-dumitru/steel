package edu.catalindumitru.bee.gui;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/9/11
 * Time: 9:48 AM
 */
public class EventDispatcher {
    protected Queue<EventProxy> proxies;
    protected Queue<UiEvent> queuedEvents;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public EventDispatcher() {
        /*create a new priority queue to sort the event proxies by their priority*/
        this.proxies = new PriorityQueue<EventProxy>(1, new Comparator<EventProxy>() {

            @Override
            public int compare(EventProxy o1, EventProxy o2) {
                return o1.priority() - o2.priority();
            }
        });

        this.queuedEvents = new LinkedList<UiEvent>();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void addProxy(EventProxy proxy) {
        this.proxies.add(proxy);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removeProxy(EventProxy proxy) {
        this.proxies.remove(proxy);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void dispatchEvent (UiEvent event) {
        this.queuedEvents.add(event);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void dispatchEventImpl(final UiEvent event) {
        /*iterate through all event proxies, in order or priority, and send the event to them*/
        for (EventProxy proxy : this.proxies) {
            /*If the event has been handled, exist method.*/
            if (proxy.handleEvent(event))
                return;
        }

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void update() {
        while(this.queuedEvents.size() > 0)
            this.dispatchEventImpl(this.queuedEvents.remove());

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

}
