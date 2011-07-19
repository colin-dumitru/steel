package edu.catalindumitru.bee.core;

import edu.catalindumitru.bee.input.InputProvider;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/18/11
 * Time: 11:20 AM
 */
public class Environment {
    protected LoggingProvider loggingProvider;
    protected InputProvider inputProvider;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public boolean complete() {
        if(this.loggingProvider == null ||
                this.inputProvider == null)
            return false;

        return true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public LoggingProvider getLoggingProvider() {
        return loggingProvider;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setLoggingProvider(LoggingProvider loggingProvider) {
        this.loggingProvider = loggingProvider;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public InputProvider getInputProvider() {
        return inputProvider;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setInputProvider(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
