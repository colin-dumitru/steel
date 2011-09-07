package edu.catalindumitru.bee.core;

import edu.catalindumitru.bee.concurent.ScheduleProvider;
import edu.catalindumitru.bee.content.Resource;
import edu.catalindumitru.bee.content.ResourceProvider;
import edu.catalindumitru.bee.gui.EventDispatcher;
import edu.catalindumitru.bee.gui.UiController;
import edu.catalindumitru.bee.gui.UiManager;
import edu.catalindumitru.bee.input.InputManager;
import edu.catalindumitru.bee.render.RenderManager;
import edu.catalindumitru.bee.xscript.XScriptHandler;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/15/11
 * Time: 10:49 AM
 */
public class Engine {
    /*Period for garbage collection and resource manager*/
    protected final static int P_RESOURCES = 50;
    protected final static int P_GARBAGE_COLLECTOR = 60000;

    protected final static int P_ACTION_DISPATCHER = 50;
    protected final static int P_XSCRIPT_HANDLER = 50;
    protected final static int P_RENDER_MANAGER = 1;
    protected final static int P_EVENT_DISPATCHER = 50;

    protected Environment environment;

    protected ActionDispatcher actionDispatcher;
    protected XScriptHandler xScriptHandler;

    protected InputManager inputManager;
    protected UiManager uiManager;
    protected RenderManager renderManager;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Engine(Environment environment) {
        this.environment = environment;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void setup(Environment environment) {
        /*create managers*/
        this.inputManager = new InputManager(environment.getInputProvider());
        this.actionDispatcher = new ActionDispatcher();
        this.xScriptHandler = new XScriptHandler();
        this.uiManager = new UiManager(environment.getRender2dProvider());
        this.renderManager = new RenderManager(environment.getRender3DProvider());


        /*setup logging service*/
        Logger.initialise(environment.getLoggingProvider());
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Schedules components in the environment which need periodic updated (content loader, graphics renderer etc.).
     */
    protected void scheduleComponents(Environment environment) {
        ScheduleProvider provider = environment.getScheduleProvider();

        final ResourceProvider resourceProvider = environment.getResourceProvider();
        final EventDispatcher eventDispatcher = this.uiManager.getEventDispatcher();

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

        /*action dispatcher*/
        provider.schedule(new Runnable() {
            @Override
            public void run() {
                actionDispatcher.update();
            }
        }, ScheduleProvider.TYPE.PERIODIC, P_ACTION_DISPATCHER);

        /*xscript handler*/
        provider.schedule(new Runnable() {
            @Override
            public void run() {
                xScriptHandler.update();
            }
        }, ScheduleProvider.TYPE.PERIODIC, P_XSCRIPT_HANDLER);

         /*xscript handler*/
        provider.schedule(new Runnable() {
            @Override
            public void run() {
                renderManager.update();
            }
        }, ScheduleProvider.TYPE.PERIODIC, P_RENDER_MANAGER);

        /*xscript handler*/
        provider.schedule(new Runnable() {
            @Override
            public void run() {
                eventDispatcher.update();
            }
        }, ScheduleProvider.TYPE.PERIODIC, P_EVENT_DISPATCHER);


    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public boolean initialize() {
        /*setup general managers which use the environment*/
        this.setup(environment);


        if (!environment.complete())
            Logger.log(Logger.PRIORITY.WARNING, "Environment has not been setup completely. Some services might not be available");

        /*setup resources*/
        Resource.initialise(environment.getResourceProvider());

        /*setup render manager*/
        this.renderManager.initialize();

        /*ui manager*/
        this.uiManager.setActionDispatcher(this.actionDispatcher);
        this.uiManager.initialize();

        /*action dispatcher*/
        this.actionDispatcher.initialize();

        /*controllers*/
        this.actionDispatcher.addController(new UiController(this.uiManager));
        this.actionDispatcher.addController(new Logger.LoggerController());

        this.xScriptHandler.addCommand(new ResourceProvider.ResourceXCommand());
        this.xScriptHandler.addCommand(new ActionDispatcher.ActionCommand(this.actionDispatcher));

        /*input manager observer*/
        this.inputManager.addObserver(this.uiManager);

        /*setup managers which need a periodic function call*/
        this.scheduleComponents(environment);

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

    public ActionDispatcher getActionDispatcher() {
        return actionDispatcher;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setActionDispatcher(ActionDispatcher actionDispatcher) {
        this.actionDispatcher = actionDispatcher;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public XScriptHandler getXScriptHandler() {
        return xScriptHandler;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setxScriptHandler(XScriptHandler xScriptHandler) {
        this.xScriptHandler = xScriptHandler;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public InputManager getInputManager() {
        return inputManager;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public UiManager getUiManager() {
        return uiManager;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setUiManager(UiManager uiManager) {
        this.uiManager = uiManager;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

}
