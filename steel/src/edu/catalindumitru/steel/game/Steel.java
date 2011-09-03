package edu.catalindumitru.steel.game;

import edu.catalindumitru.bee.core.ActionDispatcher;
import edu.catalindumitru.bee.core.Engine;
import edu.catalindumitru.bee.core.Game;
import edu.catalindumitru.bee.xscript.XScriptHandler;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/7/11
 * Time: 8:29 AM
 */
public class Steel extends Game {
    protected final static String INITIAL_XSCRIPT = "/resource/xscript/steel/initial.xml";


    protected enum STATE {
        LOADING,
        IDLE
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Steel(Engine engine) {
        super(engine);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected boolean startup() {
        /*register game controller*/
        ActionDispatcher.instance().addController(new GameController());

        /*TODO realocate starting script*/
        XScriptHandler.instance().handleScript(INITIAL_XSCRIPT);

        return false;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    protected void update(double dt) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void cleanup() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
