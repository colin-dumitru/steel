package edu.catalindumitru.bee.input;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/18/11
 * Time: 10:32 AM
 */
public interface InputObserver {
    public void onEvent(InputManager.EVENTS event, float params[]);
}
