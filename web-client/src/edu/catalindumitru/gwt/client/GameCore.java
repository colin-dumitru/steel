/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.user.client.ui.RootPanel;
import edu.catalindumitru.bee.core.Engine;
import edu.catalindumitru.bee.core.Environment;
import edu.catalindumitru.bee.core.Game;
import edu.catalindumitru.gwt.concurent.GwtScheduleProvider;
import edu.catalindumitru.gwt.content.GwtResourceProvider;
import edu.catalindumitru.gwt.content.PngConverter;
import edu.catalindumitru.gwt.content.TextConverter;
import edu.catalindumitru.gwt.content.XmlConverter;
import edu.catalindumitru.gwt.core.GwtLoggingProvider;
import edu.catalindumitru.gwt.graphics.GwtRender2DProvider;
import edu.catalindumitru.gwt.graphics.GwtRender3DProvider;
import edu.catalindumitru.gwt.input.GwtInputProvider;
import edu.catalindumitru.steel.game.Steel;


/**
 * Main entry point.
 *
 * @author colin
 */
public class GameCore implements EntryPoint {
    protected static final String ROOT_ELEMENT = "gameRoot";

    protected static final Integer D_SCREEN_MARGIN = 10;
    protected static final Integer D_SCREEN_WIDTH = 1024;
    protected static final Integer D_SCREEN_HEIGHT = 640;


    protected Canvas canvas2d;
    protected Canvas canvas3d;

    protected Engine engine;
    protected Game game;

    int tmp = 0;


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance of MainEntryPoint
     */
    public GameCore() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * The entry point method, called automatically by loading a module
     * that declares an implementing class as an entry-point
     */
    public void onModuleLoad() {
        this.initialise();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void initialise() {
        /*setup window*/
        this.setupWindow();
        /*setup game engine*/
        this.engine = this.setupEngine(this.createEnvironment());

        /*first time game initialisation*/
        this.resetGame();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void resetGame() {
        this.engine.initialize();

        /*setup actual game*/
        this.game = new Steel(this.engine);
        this.game.initialise();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void setupWindow() {
        /*used to setup different elements*/
        CanvasElement tmp = null;

        /*create and setup canvas for 2d rendering and input*/
        this.canvas2d = Canvas.createIfSupported();
        tmp = this.canvas2d.getCanvasElement();
        tmp.setId("canvas2d");

        this.canvas2d.setWidth(D_SCREEN_WIDTH + "px");
        this.canvas2d.setHeight(D_SCREEN_HEIGHT + "px");

        this.canvas2d.setCoordinateSpaceWidth(D_SCREEN_WIDTH);
        this.canvas2d.setCoordinateSpaceHeight(D_SCREEN_HEIGHT);

        /*create and setup canvas for 3d rendering and input*/
        this.canvas3d = Canvas.createIfSupported();
        tmp = this.canvas3d.getCanvasElement();
        tmp.setId("canvas3d");

        this.canvas3d.setWidth(D_SCREEN_WIDTH + "px");
        this.canvas3d.setHeight(D_SCREEN_HEIGHT + "px");

        this.canvas3d.setCoordinateSpaceWidth(D_SCREEN_WIDTH);
        this.canvas3d.setCoordinateSpaceHeight(D_SCREEN_HEIGHT);

        /*add elements to window*/
        RootPanel root;

        root = RootPanel.get("canvas2d");
        root.add(this.canvas2d);

        root = RootPanel.get("canvas3d");
        root.add(this.canvas3d);

        RootPanel.get(ROOT_ELEMENT).setHeight(((Integer)(D_SCREEN_HEIGHT + D_SCREEN_MARGIN * 2)).toString() + "px");
        RootPanel.get(ROOT_ELEMENT).setWidth(D_SCREEN_WIDTH + "px");



    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected Environment createEnvironment() {
        Environment ret = new Environment();

        /*create logging provider*/
        ret.setLoggingProvider(new GwtLoggingProvider());

        /*create and setup input provider*/
        GwtInputProvider inputProvider = new GwtInputProvider(this.canvas2d);
        ret.setInputProvider(inputProvider);

        /*Create 2d render provider*/
        ret.setRender2dProvider(new GwtRender2DProvider(this.canvas2d.getContext2d()));
        ret.setRender3DProvider(new GwtRender3DProvider(this.canvas3d.getContext("experimental-webgl")));

        /*setup schedule provider*/
        ret.setScheduleProvider(new GwtScheduleProvider());

        /*setup content loader*/
        GwtResourceProvider resourceProvider = new GwtResourceProvider();
        resourceProvider.addResourceConverter(new TextConverter());
        resourceProvider.addResourceConverter(new PngConverter());
        resourceProvider.addResourceConverter(new XmlConverter());
        ret.setResourceProvider(resourceProvider);

        /*setup parameters - TODO replace this with information provided by a service provider*/
        ret.setParam(Environment.PARAMS.SCREEN_WIDTH, D_SCREEN_WIDTH.toString());
        ret.setParam(Environment.PARAMS.SCREEN_HEIGHT, D_SCREEN_HEIGHT.toString());


        return ret;

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected Engine setupEngine(Environment environment) {
        Engine ret = new Engine(environment);

        return ret;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------


}
