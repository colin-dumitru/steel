/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.FillStrokeStyle;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import edu.catalindumitru.bee.core.Engine;
import edu.catalindumitru.bee.core.Environment;
import edu.catalindumitru.bee.graphics.*;
import edu.catalindumitru.bee.math.Point2D;
import edu.catalindumitru.gwt.core.GwtLoggingProvider;
import edu.catalindumitru.gwt.graphics.GwtRender2DProvider;
import edu.catalindumitru.gwt.input.GwtInputProvider;

//import edu.catalindumitru.array.TypedArray;


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


        GwtRender2DProvider prov = new GwtRender2DProvider(this.canvas2d.getContext2d());

        Shape shape = new Shape();
        shape.addPoint(new Point2D(75,75));
        shape.addPoint(new Point2D(175,55));
        shape.addPoint(new Point2D(145,201));
        shape.addPoint(new Point2D(30,44));

        prov.setFillColor(Color.BLUE);
        prov.setStrokeColor(Color.RED);

        prov.setFillGradient(Gradient.BABY_BLUE);
        prov.setStrokeGradient(Gradient.BABY_BLUE);

        prov.setAlpha(0.2f);
        prov.setLineWidth(5);
        prov.setDrawingCap(Render2DProvider.CAP.ROUND);
        prov.setDrawingJoin(Render2DProvider.JOIN.ROUND);

        prov.setFillStyle(Render2DProvider.STYLE.GRADIENT);
        prov.setStrokeStyle(Render2DProvider.STYLE.COLOR);



        //prov.fillRectangle(100, 20, 200, 200);
        //prov.strokeRectangle(300, 300, 100, 100);
        //prov.fillArc(200, 200, 100, 0, (float) (Math.PI * 2));
        //prov.strokeArc(50, 50, 10, 0, (float) (Math.PI * 2));


        prov.setTextFont(new Font("Arial", 2.0f, 2.0f,  Font.STYLE.NORMAL, Font.WEIGHT.NORMAL));
        prov.beginDrawing();
        prov.fillString(100, 50, "mama");
        prov.endDrawing();

        prov.setTextFont(new Font("Arial", 2.0f, 2.0f, Font.STYLE.ITALIC, Font.WEIGHT.NORMAL));
        prov.setTextBaseline(Render2DProvider.TEXT_BASELINE.BOTTOM);
        prov.beginDrawing();
        prov.fillString(200, 50, "mama");
        prov.fillRectangle(200, 50, 100, 5);
        prov.endDrawing();

        prov.setTextFont(new Font("Arial", 2.0f, 2.0f, Font.STYLE.NORMAL, Font.WEIGHT.BOLD));
        prov.setTextBaseline(Render2DProvider.TEXT_BASELINE.TOP) ;
        prov.beginDrawing();
        prov.fillString(300, 50, "mama");
        //prov.strokeString(100, 50, "mama face mere");

        //prov.fillShape(shape);
        //prov.strokeShape(shape);

        //Gradient shadow = new Gradient(Gradient.TYPE.RADIAL);
        //shadow.addColorStop(0.6, new Color(0.0f, 0.0f, 0.0f, 0.0f));
        //shadow.addColorStop(0.8, new Color(0.0f, 0.0f, 0.0f, 0.1f));
        //shadow.addColorStop(1.0, new Color(0.0f, 0.0f, 0.0f, 0.4f));

        //prov.setFillGradient(shadow);
        //prov.fillArc(200, 200, 100, 0, (float) (Math.PI * 2));



        prov.endDrawing();

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

        /*Create 2d render provider*/
        ret.setUiProvider(new GwtRender2DProvider(this.canvas2d.getContext2d()));

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
