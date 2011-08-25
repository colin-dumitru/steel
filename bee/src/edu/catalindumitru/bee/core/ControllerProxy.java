package edu.catalindumitru.bee.core;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/22/11
 * Time: 10:07 AM
 */
public interface ControllerProxy {
    public static final int P_CORE = 0;

    public static final int P_GUI = 50;

    public static final int P_GAME = 100;

    /**
     * Returns the priority of this controllers, to determine the order in which every controllers should be called.
     *
     * @return the priority of this controllers/
     */
    public int priority();

    /**
     * Tells the controllers to handle a command.
     *
     * @param action@return if the command has been successfully handled. If the command has not been handled then the next controllers
     *                      in line will receive the command.
     */
    public boolean handleAction(Action action);

    /**
     * Sets whether or not the controller should handle actions.
     *
     * @param active whether or not the controller should handle events.
     */
    public void setActive(boolean active);

    /**
     * Returns whether or nor the controller is active (and handles events).
     *
     * @return if the controller is active.
     */
    public boolean isActive();
}
