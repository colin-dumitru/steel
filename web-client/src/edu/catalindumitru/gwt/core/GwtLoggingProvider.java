package edu.catalindumitru.gwt.core;

import com.google.gwt.core.client.GWT;
import edu.catalindumitru.bee.core.Logger;
import edu.catalindumitru.bee.core.LoggingProvider;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/18/11
 * Time: 11:14 AM
 */
public class GwtLoggingProvider implements LoggingProvider {

    public void log(Logger.PRIORITY priority, String message) {
        switch (priority) {
            case CRITICAL:
                GWT.log("[CRITICAL]" + message);
                break;
            case ERROR:
                GWT.log("[ERROR]" + message);
                break;
            case WARNING:
                GWT.log("[WARNING]" + message);
                break;
            case INFORMATION:
                GWT.log("[INFORMATION]" + message);
                break;
        }
    }
}
