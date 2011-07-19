package edu.catalindumitru.bee.core;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/18/11
 * Time: 11:14 AM
 */
public interface LoggingProvider {
    public void log(Logger.PRIORITY priority, String message);
}
