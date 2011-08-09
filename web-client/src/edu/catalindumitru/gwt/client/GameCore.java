/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import edu.catalindumitru.bee.content.ImageResource;
import edu.catalindumitru.bee.content.Resource;
import edu.catalindumitru.bee.content.ResourceObserver;
import edu.catalindumitru.bee.content.StringResource;
import edu.catalindumitru.bee.core.Engine;
import edu.catalindumitru.bee.core.Environment;
import edu.catalindumitru.gwt.concurent.GwtScheduleProvider;
import edu.catalindumitru.gwt.content.PngConverter;
import edu.catalindumitru.gwt.core.GwtLoggingProvider;
import edu.catalindumitru.gwt.graphics.GwtRender2DProvider;
import edu.catalindumitru.gwt.input.GwtInputProvider;
import edu.catalindumitru.gwt.content.GwtResourceProvider;
import edu.catalindumitru.gwt.content.XmlConverter;



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
        this.initialise();

        final Resource img = new Resource("png", "/resource/images/test/Blob.PNG");

        img.addResourceObserver(new ResourceObserver() {
            @Override
            public void stateChanged(Resource from) {
                engine.getEnvironment().getRender2dProvider().drawImage((ImageResource) img.getResource(), 0, 0);
            }
        });

        final Resource str = new Resource("xml", "/resource/layout/test.xml");

        str.addResourceObserver(new ResourceObserver() {
            @Override
            public void stateChanged(Resource from) {
                if(from.getStatus() == Resource.STATUS.COMPLETED)
                    GWT.log(((StringResource)from.getResource()).getString());
            }
        });
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

        /*Create 2d render provider*/
        ret.setRender2dProvider(new GwtRender2DProvider(this.canvas2d.getContext2d()));

        /*setup schedule provider*/
        ret.setScheduleProvider(new GwtScheduleProvider());

        /*setup content loader*/
        GwtResourceProvider resourceProvider = new GwtResourceProvider();
        resourceProvider.addResourceConverter(new XmlConverter());
        resourceProvider.addResourceConverter(new PngConverter());
        ret.setResourceProvider(resourceProvider);

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
