package edu.catalindumitru.bee.gui;

import edu.catalindumitru.bee.content.Resource;
import edu.catalindumitru.bee.content.ResourceObserver;
import edu.catalindumitru.bee.content.XmlResource;
import edu.catalindumitru.bee.core.Action;
import edu.catalindumitru.bee.core.ControllerProxy;
import edu.catalindumitru.bee.core.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/22/11
 * Time: 11:09 AM
 */
public class UiController implements ControllerProxy {
    public static final String C_ACTIVATE_MENU = "activateMenu";
    public static final String C_ADD_MENU = "addMenu";

    public static final String P_ACTIVE_MENU = "menu";
    public static final String P_PANEL = "panel";
    public static final String P_RESOURCE = "resource";

    /*if we should receive actions*/
    protected boolean active = true;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the priority of this controllers, to determine the order in which every controllers should be called.
     *
     * @return the priority of this controllers/
     */
    @Override
    public int priority() {
        return ControllerProxy.P_GUI;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Tells the controllers to handle a command.
     *
     * @param action@return if the command has been successfully handled. If the command has not been handled then the next controllers
     *                      in line will receive the command.
     */
    @Override
    public boolean handleAction(Action action) {
        if (action.getName().equals(C_ACTIVATE_MENU))
            return this.activateMenu(action.getParams().get(P_PANEL));
        else if (action.getName().equals(C_ADD_MENU))
            return this.addMenu(new Resource("xml", action.getParams().get(P_RESOURCE)));

        return false;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets whether or not the controller should handle actions.
     *
     * @param active whether or not the controller should handle events.
     */
    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns whether or nor the controller is active (and handles events).
     *
     * @return if the controller is active.
     */
    @Override
    public boolean isActive() {
        return this.active;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected boolean addMenu(Resource resource) {
        /*Check whether or not the resource has been completed. If not, then we wait until is completed and
        * call this function again*/
        if (resource.getStatus() == Resource.STATUS.COMPLETED) {
            try {
                /*When the xml file has been completed, then create a new root widget and add it to the UiManager*/
                Widget res = WidgetFactory.instance().create(
                        ((XmlResource) resource.getResource()).getRootNode().castToElement());

                UiManager.instance().addPanel((Panel) res);
            } catch (Exception ex) {
                Logger.log(Logger.PRIORITY.ERROR, ex.toString());
            }

        } else {
            resource.addResourceObserver(new ResourceObserver() {
                @Override
                public void stateChanged(Resource from) {
                    if (from.getStatus() == Resource.STATUS.COMPLETED)
                        addMenu(from);
                }
            });
        }

        return true;

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected boolean activateMenu(String menuName) {
        UiManager.instance().switchActivePanel(menuName);

        return true;
    }
}
