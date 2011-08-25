package edu.catalindumitru.bee.core;

import edu.catalindumitru.bee.concurent.ScheduleProvider;
import edu.catalindumitru.bee.content.ResourceProvider;
import edu.catalindumitru.bee.graphics.Render2DProvider;
import edu.catalindumitru.bee.input.InputProvider;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/5/11
 * Time: 12:31 PM
 */
public abstract class Game {
    protected static final int P_UPDATE = 1;

    protected Engine engine;
    protected Environment environment;

    protected LoggingProvider loggingProvider;
    protected InputProvider inputProvider;
    protected Render2DProvider uiProvider;
    protected ResourceProvider resourceProvider;
    protected ScheduleProvider scheduleProvider;

    private double lastTime;
    private double currentTime;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Game(Engine engine) {
        this.engine = engine;
        this.environment = engine.getEnvironment();

        this.loggingProvider = this.environment.getLoggingProvider();
        this.inputProvider = this.environment.getInputProvider();
        this.uiProvider = this.environment.getRender2dProvider();
        this.resourceProvider = this.environment.getResourceProvider();
        this.scheduleProvider = this.environment.getScheduleProvider();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public boolean initialise() {
        if (this.engine == null)
            return false;

        /*create update cycle*/
        this.scheduleProvider.schedule(new Runnable() {
            @Override
            public void run() {
                update();
            }
        }, ScheduleProvider.TYPE.PERIODIC, P_UPDATE);


        /*initialise the actual game*/
        this.startup();

        /*init update cycle*/
        this.lastTime = this.scheduleProvider.currentTime();

        return true;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected abstract boolean startup();

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private void update() {
        /*calculate delta time and update logic cycle*/
        this.currentTime = this.scheduleProvider.currentTime();
        this.update(this.currentTime - this.lastTime);
        this.currentTime = this.lastTime;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected abstract void update(double dt);

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected abstract void cleanup();


}
