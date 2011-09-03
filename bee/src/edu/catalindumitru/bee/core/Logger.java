package edu.catalindumitru.bee.core;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/7/11
 * Time: 10:58 AM
 */
public class Logger {
    public enum PRIORITY {CRITICAL, ERROR, WARNING, INFORMATION}

    protected static LoggingProvider provider;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static final class LoggerController implements ControllerProxy {
        public static final String ACTION = "log";
        public static final String P_MESSAGE = "text";
        public static final String P_PRIORITY = "priority";

        public static final String P_ERROR = "error";
        public static final String P_CRITICAL = "critical";
        public static final String P_WARNING = "warning";
        public static final String P_INFORMATION = "information";

        protected boolean isActive = true;

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        /**
         * Returns the priority of this controllers, to determine the order in which every controllers should be called.
         *
         * @return the priority of this controllers/
         */
        @Override
        public int priority() {
            return P_CORE;
        }
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        /**
         * Tells the controllers to handle a command.
         *
         * @param action@return if the command has been successfully handled. If the command has not been handled then
         *                      the next controllers in line will receive the command.
         */
        @Override
        public boolean handleAction(Action action) {
            if (action.getName().equals(ACTION)) {
                /*get message to be logged*/
                String message = action.getParams().get(P_MESSAGE);
                if (message == null)
                    message = "undefined error";

                /*get message priority*/
                PRIORITY priority = PRIORITY.INFORMATION;
                String ps = action.getParams().get(P_PRIORITY);

                if (ps == null) {
                    /*exit*/
                } else if (ps.equals(P_ERROR)) {
                    priority = PRIORITY.ERROR;
                } else if (ps.equals(P_CRITICAL)) {
                    priority = PRIORITY.CRITICAL;
                } else if (ps.equals(P_WARNING)) {
                    priority = PRIORITY.WARNING;
                } else if (ps.equals(P_INFORMATION)) {
                    priority = PRIORITY.INFORMATION;
                }

                Logger.log(priority, message);

                /*message handled*/
                return true;
            } else {
                /*message not handled*/
                return false;
            }
        }
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        /**
         * Sets whether or not the controller should handle actions.
         *
         * @param active whether or not the controller should handle events.
         */
        @Override
        public void setActive(boolean active) {
            this.isActive = active;
        }
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        /**
         * Returns whether or nor the controller is active (and handles events).
         *
         * @return if the controller is active.
         */
        @Override
        public boolean isActive() {
            return this.isActive;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static void initialise(LoggingProvider provider) {
        Logger.provider = provider;

        /*add the logger controller to the action dispatcher*/
        ActionDispatcher.instance().addController(new Logger.LoggerController());
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static void log(PRIORITY priority, String message) {
        if (provider == null)
            return;

        provider.log(priority, message);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
