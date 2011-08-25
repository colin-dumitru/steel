package edu.catalindumitru.bee.core;

import edu.catalindumitru.bee.concurent.ScheduleProvider;
import edu.catalindumitru.bee.content.ResourceProvider;
import edu.catalindumitru.bee.graphics.Render2DProvider;
import edu.catalindumitru.bee.input.InputProvider;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/18/11
 * Time: 11:20 AM
 */
public class Environment {
    protected LoggingProvider loggingProvider;
    protected InputProvider inputProvider;
    protected Render2DProvider render2dProvider;
    protected ResourceProvider resourceProvider;
    protected ScheduleProvider scheduleProvider;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public boolean complete() {
        if (this.loggingProvider == null
                || this.inputProvider == null
                || this.render2dProvider == null
                || this.resourceProvider == null
                || this.scheduleProvider == null)
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

    public Render2DProvider getRender2dProvider() {
        return render2dProvider;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setRender2dProvider(Render2DProvider render2dProvider) {
        this.render2dProvider = render2dProvider;
    }


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public ResourceProvider getResourceProvider() {
        return resourceProvider;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setResourceProvider(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public ScheduleProvider getScheduleProvider() {
        return scheduleProvider;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setScheduleProvider(ScheduleProvider scheduleProvider) {
        this.scheduleProvider = scheduleProvider;
    }
}
