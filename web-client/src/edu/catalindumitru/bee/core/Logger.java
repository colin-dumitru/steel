package edu.catalindumitru.bee.core;

import com.google.gwt.core.client.GWT;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/7/11
 * Time: 10:58 AM
 */
public class Logger {
    public enum PRIORITY{CRITICAL, ERROR, WARNING, INFORMATION };


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static void log(PRIORITY priority, String message){
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

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
