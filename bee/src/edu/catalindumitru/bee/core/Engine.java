package edu.catalindumitru.bee.core;

import edu.catalindumitru.bee.concurent.ScheduleProvider;
import edu.catalindumitru.bee.content.Resource;
import edu.catalindumitru.bee.content.ResourceProvider;
import edu.catalindumitru.bee.gui.UiManager;
import edu.catalindumitru.bee.input.InputManager;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/15/11
 * Time: 10:49 AM
 */
public class Engine {
    /*Period for garbage collection and resource manager*/
    protected final static int P_RESOURCES = 1;
    protected final static int P_GARBAGE_COLLECTOR = 60000;

    protected Environment environment;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Engine(Environment environment) {
        this.environment = environment;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void setup(Environment environment) {
        /*setup logging service*/
        Logger.setLoggingProvider(environment.getLoggingProvider());

        /*setup input service*/
        InputManager.instance().setInputProvider(environment.getInputProvider());

        /*setup ui*/
        UiManager.instance().setRenderProvider(environment.getRender2dProvider());

        /*resource manager*/
        Resource.setProvider(environment.getResourceProvider());
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Schedules components in the environment which need periodic updated (content loader, graphics renderer etc.).
     */
    protected void scheduleComponents(Environment environment) {
        ScheduleProvider provider = environment.getScheduleProvider();

        /*schedule content loader update and garbage collector*/
        final ResourceProvider resourceProvider = environment.getResourceProvider();

        /*content manager*/
        provider.schedule(new Runnable() {
            @Override
            public void run() {
                resourceProvider.update();
            }
        }, ScheduleProvider.TYPE.PERIODIC, P_RESOURCES);

        /*garbage collection and cache refresh*/
        provider.schedule(new Runnable() {
            @Override
            public void run() {
                resourceProvider.updateGC();
            }
        }, ScheduleProvider.TYPE.PERIODIC, P_GARBAGE_COLLECTOR);

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public boolean initialize() {
        /*setup general managers which use the environment*/
        this.setup(environment);
        /*setup managers which need a periodic funcion call*/
        this.scheduleComponents(environment);

        if (!environment.complete())
            Logger.log(Logger.PRIORITY.WARNING, "Environment has not been setup completely. Some services might not be available");

        return true;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void cleanup() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Environment getEnvironment() {
        return environment;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

}
