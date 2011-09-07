package edu.catalindumitru.gwt.input;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.event.dom.client.*;
import edu.catalindumitru.bee.input.InputObserver;
import edu.catalindumitru.bee.input.InputProvider;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/18/11
 * Time: 10:50 AM
 */
public class GwtInputProvider implements InputProvider {
    protected Canvas canvas;
    protected InputObserver observer;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public GwtInputProvider(Canvas canvas) {
        this.canvas = canvas;
        this.initImpl(canvas.getCanvasElement());
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected final native void initImpl(CanvasElement canvas) /*-{
        var that = this;

        canvas.addEventListener("mousemove", mouseMove, true );
        canvas.addEventListener("mousedown", mouseDown, true );
        canvas.addEventListener("mouseup",   mouseUp,   true );
        canvas.addEventListener("click",     click,     true );
        canvas.addEventListener("dblclick ", doubleClick, true );

        canvas.addEventListener("keyup",     keyUp, true );
        canvas.addEventListener("keydown",   keyDown, true );
        canvas.addEventListener("keypress",  keyPress, true );

        function mouseDown(event) {
            that.@edu.catalindumitru.gwt.input.GwtInputProvider::observer.@edu.catalindumitru.bee.input.InputObserver::onMouseDown(II)(
                    event.layerX,
                    event.layerY
            )
        }

        function mouseUp(event) {
            that.@edu.catalindumitru.gwt.input.GwtInputProvider::observer.@edu.catalindumitru.bee.input.InputObserver::onMouseUp(II)(
                    event.layerX,
                    event.layerY
            )
        }

        function click(event) {
            that.@edu.catalindumitru.gwt.input.GwtInputProvider::observer.@edu.catalindumitru.bee.input.InputObserver::onClick(II)(
                    event.layerX,
                    event.layerY
            )
        }

        function doubleClick(event) {
            that.@edu.catalindumitru.gwt.input.GwtInputProvider::observer.@edu.catalindumitru.bee.input.InputObserver::onDoubleClick(II)(
                    event.layerX,
                    event.layerY
            )
        }

        function mouseMove(event) {
            that.@edu.catalindumitru.gwt.input.GwtInputProvider::observer.@edu.catalindumitru.bee.input.InputObserver::onMouseMove(II)(
                    event.layerX,
                    event.layerY
            )
        }

        function keyUp(event) {
            that.@edu.catalindumitru.gwt.input.GwtInputProvider::observer.@edu.catalindumitru.bee.input.InputObserver::onKeyUp(I)(
                    event.keyCode
            )

        }

        function keyDown(event) {
            that.@edu.catalindumitru.gwt.input.GwtInputProvider::observer.@edu.catalindumitru.bee.input.InputObserver::onKeyDown(I)(
                    event.keyCode
            )
        }

        function keyPress(event) {
            that.@edu.catalindumitru.gwt.input.GwtInputProvider::observer.@edu.catalindumitru.bee.input.InputObserver::onKeyPress(I)(
                    event.keyCode
            )
        }

    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setInputObserver(InputObserver observer) {
        this.observer = observer;
    }


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------


}
