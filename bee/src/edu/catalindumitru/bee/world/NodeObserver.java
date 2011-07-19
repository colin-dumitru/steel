package edu.catalindumitru.bee.world;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/12/11
 * Time: 2:57 PM
 */
public interface NodeObserver {
    /**
     * Send an event to the node observer. This event could be something like removed from the tree structure, a new
     * component has been added, removed etc.
     * @param event event id. (See @Node).
     * @param param additional abstract object which can be send as a param (Eg you can send which component has been
     * added / removed).
     * @param source which node has triggered the event.
     */
    public void onEvent(int event, Object param,  Node source);
}
