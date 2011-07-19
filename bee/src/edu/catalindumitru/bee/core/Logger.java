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
    public static void setLoggingProvider(LoggingProvider provider) {
        Logger.provider = provider;
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
