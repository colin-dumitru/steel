package edu.catalindumitru.bee.xscript;

import edu.catalindumitru.bee.content.xml.Element;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/22/11
 * Time: 8:19 AM
 */
public interface XScriptCommand {
    /**
     * The name of the command it can handle
     *
     * @return the name of the command the command can handle.
     */

    public String commandName();

    /**
     * Handles the given command.
     *
     * @param root    the element root which contains the rest of the information needed to handle the command.
     * @param handler a script handler need for recursive or tree commands.
     */
    public void handleCommand(Element root, XScriptHandler handler);
}
