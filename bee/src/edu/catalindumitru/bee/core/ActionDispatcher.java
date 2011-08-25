package edu.catalindumitru.bee.core;

import edu.catalindumitru.bee.content.xml.Element;
import edu.catalindumitru.bee.content.xml.Node;
import edu.catalindumitru.bee.content.xml.NodeList;
import edu.catalindumitru.bee.xscript.XScriptCommand;
import edu.catalindumitru.bee.xscript.XScriptHandler;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/22/11
 * Time: 11:07 AM
 */
public class ActionDispatcher {
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public final static class ControllerComparator implements Comparator<ControllerProxy> {
        @Override
        public int compare(ControllerProxy o1, ControllerProxy o2) {
            return o1.priority() - o2.priority();
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public final static class ActionCommand implements XScriptCommand{
        public static final String COMMAND = "action";

        public static final String A_NAME = "name";
        public static final String A_PARAM_NAME = "name";
        public static final String A_PARAM_VAL = "val";
        public static final String N_PARAM = "param";

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        /**
         * The name of the command it can handle
         *
         * @return the name of the command the command can handle.
         */
        @Override
        public String commandName() {
            return COMMAND;
        }
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        /**
         * Handles the given command.
         *
         * @param root    the element root which contains the rest of the information needed to handle the command.
         * @param handler a script handler need for recursive or tree commands.
         */
        @Override
        public void handleCommand(Element root, XScriptHandler handler) {
            if(!root.getTagName().equals(COMMAND))
                return;

            String commandName = null;
            Map<String, String> params = null;

            if(root.hasAttribute(A_NAME)) {
                commandName = root.getAttribute(A_NAME);
            }   else {
                /*no valid action specified*/
                return;
            }

            /*check for additional params*/
            NodeList children = root.getChildNodes();

            if(children.getLength() > 0)
                params = new TreeMap<String, String>();

            for(int i = 0; i < children.getLength(); i++) {
                if(children.item(i).getNodeType() != Node.ELEMENT_NODE)
                    continue;

                Element child = children.item(i).castToElement();

                params.put(child.getAttribute(A_PARAM_NAME), child.getAttribute(A_PARAM_VAL));
            }

            /*send the new action*/
            ActionDispatcher.instance().dispatchAction(new Action(commandName, params));
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /*controllers sorted by their priority*/
    protected PriorityQueue<ControllerProxy> controllers = new PriorityQueue<ControllerProxy>(1,
            new ControllerComparator());
    /*queued actions to be dispatched on the next call to update*/
    protected Queue<Action> queuedActions = new LinkedList<Action>();
    /*unique instance*/
    protected static ActionDispatcher instance = new ActionDispatcher();

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected ActionDispatcher() {

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static ActionDispatcher instance() {
        return instance;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void initialise() {
        XScriptHandler.instance().addCommand(new ActionCommand());
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void addController(ControllerProxy controller) {
        this.controllers.add(controller);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removeController(ControllerProxy controller) {
        this.controllers.remove(controller);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Dispatches the given action to all the registered controller in order of their priority.
     *
     * @param action which action to dispatch.
     */
    public void dispatchAction(Action action) {
        this.queuedActions.add(action);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Dispatches the given action to all the registered controller in order of their priority.
     *
     * @param action    which action should be dispatched.
     * @param immediate whether or not the dispatching should happen asynchronous (on the next call to update)
     *                  or immediate.
     */
    public void dispatchAction(Action action, boolean immediate) {
        if (immediate)
            this.dispatchActionImm(action);
        else
            this.queuedActions.add(action);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void dispatchActionImm(Action action) {
        for (ControllerProxy controller : this.controllers) {
            /*send the action only if the controller is active*/
            if (controller.isActive()) {
                /*if the action has been handled, then we end the dispatch action*/
                if (controller.handleAction(action))
                    return;
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void update() {
        while (this.queuedActions.size() > 0) {
            Action top = this.queuedActions.poll();

            this.dispatchActionImm(top);
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

}
