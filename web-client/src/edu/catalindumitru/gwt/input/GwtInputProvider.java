package edu.catalindumitru.gwt.input;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.*;
import edu.catalindumitru.bee.input.InputManager;
import edu.catalindumitru.bee.input.InputProvider;
import edu.catalindumitru.bee.input.InputResolver;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/18/11
 * Time: 10:50 AM
 */
public class GwtInputProvider implements InputProvider, ClickHandler, KeyDownHandler, KeyUpHandler, MouseUpHandler,
        MouseDownHandler, MouseMoveHandler, MouseWheelHandler, DoubleClickHandler {
    protected Canvas canvas;
    protected InputResolver resolver;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public GwtInputProvider(Canvas canvas) {
        /*cmouse events*/
        canvas.addClickHandler(this);
        canvas.addMouseDownHandler(this);
        canvas.addMouseUpHandler(this);
        canvas.addMouseMoveHandler(this);
        canvas.addMouseWheelHandler(this);

        /*key events*/
        canvas.addKeyDownHandler(this);
        canvas.addKeyUpHandler(this);
        canvas.addDoubleClickHandler(this);

        this.canvas = canvas;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Called when a native click event is fired.
     *
     * @param event the {@link com.google.gwt.event.dom.client.ClickEvent} that was fired
     */
    public void onClick(ClickEvent event) {
        if (this.resolver != null) {
            resolver.onEvent(InputManager.EVENTS.E_MOUSE_CLICK, new float[]{event.getX(), event.getY()});
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Called when MouseDown is fired.
     *
     * @param event the {@link com.google.gwt.event.dom.client.MouseDownEvent} that was fired
     */
    public void onMouseDown(MouseDownEvent event) {
        if (this.resolver != null) {
            resolver.onEvent(InputManager.EVENTS.E_MOUSE_DOWN, new float[]{event.getX(), event.getY()});
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Called when MouseUpEvent is fired.
     *
     * @param event the {@link com.google.gwt.event.dom.client.MouseUpEvent} that was fired
     */
    public void onMouseUp(MouseUpEvent event) {
        if (this.resolver != null) {
            resolver.onEvent(InputManager.EVENTS.E_MOUSE_UP, new float[]{event.getX(), event.getY()});
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Called when {@link com.google.gwt.event.dom.client.KeyDownEvent} is fired.
     *
     * @param event the {@link com.google.gwt.event.dom.client.KeyDownEvent} that was fired
     */
    public void onKeyDown(KeyDownEvent event) {
        if (this.resolver != null) {
            resolver.onEvent(InputManager.EVENTS.E_KEY_DOWN, new float[]{event.getNativeKeyCode()});
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Called when KeyUpEvent is fired.
     *
     * @param event the {@link com.google.gwt.event.dom.client.KeyUpEvent} that was fired
     */
    public void onKeyUp(KeyUpEvent event) {
        if (this.resolver != null) {
            resolver.onEvent(InputManager.EVENTS.E_KEY_UP, new float[]{event.getNativeKeyCode()});
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setInputResolver(InputResolver resolver) {
        this.resolver = resolver;
    }


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Called when MouseMoveEvent is fired.
     *
     * @param event the {@link com.google.gwt.event.dom.client.MouseMoveEvent} that was fired
     */
    @Override
    public void onMouseMove(MouseMoveEvent event) {
        if (this.resolver != null) {
            resolver.onEvent(InputManager.EVENTS.E_MOUSE_MOVE, new float[]{event.getX(), event.getY()});
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Called when MouseWheelEvent is fired.
     *
     * @param event the {@link com.google.gwt.event.dom.client.MouseWheelEvent} that was fired
     */
    @Override
    public void onMouseWheel(MouseWheelEvent event) {
        if (this.resolver != null) {
            resolver.onEvent(InputManager.EVENTS.E_MOUSE_WHEEL, new float[]{event.getDeltaY()});
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Called when a {@link com.google.gwt.event.dom.client.DoubleClickEvent} is fired.
     *
     * @param event the {@link com.google.gwt.event.dom.client.DoubleClickEvent} that was fired
     */
    @Override
    public void onDoubleClick(DoubleClickEvent event) {
        if (this.resolver != null) {
            resolver.onEvent(InputManager.EVENTS.E_MOUSE_DOUBLE_CLICK, new float[]{event.getX(), event.getY()});
        }
    }
}
