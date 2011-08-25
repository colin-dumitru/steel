package edu.catalindumitru.bee.core;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/22/11
 * Time: 10:23 AM
 */
public abstract class Controller implements ControllerProxy {
    protected boolean isActive = true;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the priority of this controllers, to determine the order in which every controllers should be called.
     *
     * @return the priority of this controllers/
     */
    @Override
    public int priority() {
        /*default priority for controllers which handle ui events*/
        return P_GUI;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets whether or not the controller should handle actions.
     *
     * @param active whether or not the controller should handle events.
     */
    public void setActive(boolean active) {
        this.isActive = active;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns whether or nor the controller is active (and handles events).
     *
     * @return if the controller is active.
     */
    public boolean isActive() {
        return this.isActive;

    }

}
