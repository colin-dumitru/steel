/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.client;

import com.gargoylesoftware.htmlunit.javascript.host.canvas.CanvasRenderingContext2D;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.EntryPoint;
//import edu.catalindumitru.array.TypedArray;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import edu.catalindumitru.bee.core.Engine;
import edu.catalindumitru.bee.core.Environment;
import edu.catalindumitru.bee.core.Logger;
import edu.catalindumitru.bee.input.InputManager;
import edu.catalindumitru.bee.input.InputObserver;
import edu.catalindumitru.bee.network.socket.NetworkException;
import edu.catalindumitru.bee.network.socket.NetworkMessage;
import edu.catalindumitru.bee.network.socket.SocketObserver;
import edu.catalindumitru.gwt.core.GwtLoggingProvider;
import edu.catalindumitru.gwt.input.GwtInputProvider;
import edu.catalindumitru.gwt.socket.*;



/**
 * Main entry point.
 *
 * @author colin
 */
public class GameCore implements EntryPoint {
    protected static final String ROOT_ELEMENT = "gameRoot";

    protected Canvas canvas2d;
    protected Canvas canvas3d;

    protected Engine engine;


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
        /*setup window*/
        this.setupWindow();
        /*setup game engine*/
        this.engine = this.setupEngine(this.createEnvironment());

        /*first time game initialisation*/
        this.resetGame();

        InputManager.instance().addObserver(new InputObserver() {
            public void onEvent(InputManager.EVENTS event, float[] params) {
                Logger.log(Logger.PRIORITY.INFORMATION, event.toString() + "(" + params[0] + (params.length > 1 ? params[1] : "") + ")");
            }
        });


    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void resetGame() {
        this.engine.initialize();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void setupWindow() {
        /*used to setup different elements*/
        Element tmp = null;

        /*create and setup canvas for 2d rendering and input*/
        this.canvas2d = Canvas.createIfSupported();
        tmp = this.canvas2d.getCanvasElement();
        tmp.setId("canvas2d");

        this.canvas2d.setWidth("800px");
        this.canvas2d.setHeight("600px");

        this.canvas2d.setCoordinateSpaceWidth(800);
        this.canvas2d.setCoordinateSpaceHeight(600);

        /*add elements to window*/
        //DOM.getElementById(ROOT_ELEMENT).appendChild(canvas2d.getElement());
        RootPanel.get("root").add(canvas2d);

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
