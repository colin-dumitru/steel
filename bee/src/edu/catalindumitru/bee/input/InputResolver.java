package edu.catalindumitru.bee.input;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/18/11
 * Time: 10:44 AM
 */
public interface InputResolver {
    public void onEvent(InputManager.EVENTS event, float params[]);
}
