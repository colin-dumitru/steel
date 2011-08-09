package edu.catalindumitru.bee.content;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/9/11
 * Time: 4:59 PM
 */
public interface ResourceObserver {
    /**
     * This method is called when a resource's state has changed.
     * @param from what resource has had it's state changed.
     */
    public void stateChanged(Resource from);
}
