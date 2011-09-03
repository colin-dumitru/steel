package edu.catalindumitru.steel.game;

import com.google.gwt.core.client.GWT;
import edu.catalindumitru.bee.core.Action;
import edu.catalindumitru.bee.core.Controller;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/7/11
 * Time: 8:49 AM
 */
public class GameController extends Controller {
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
        if(action.getName().equals("TestAction")) {
            return true;
        }

        return false;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
