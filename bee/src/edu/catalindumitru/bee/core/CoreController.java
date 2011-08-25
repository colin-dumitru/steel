package edu.catalindumitru.bee.core;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/22/11
 * Time: 10:25 AM
 */
public class CoreController extends Controller {
    /**
     * Tells the controllers to handle a command.
     *
     * @param action@return if the command has been successfully handled. If the command has not been handled then the next controllers
     *                      in line will receive the command.
     */
    @Override
    public boolean handleAction(Action action) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
