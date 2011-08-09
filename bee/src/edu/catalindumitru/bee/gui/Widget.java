package edu.catalindumitru.bee.gui;

import edu.catalindumitru.bee.core.UniqueNameGenerator;
import edu.catalindumitru.bee.graphics.Color;
import edu.catalindumitru.bee.graphics.Render2DProvider;
import edu.catalindumitru.bee.graphics.Shape;
import edu.catalindumitru.bee.math.Rectangle;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/16/11
 * Time: 9:21 AM
 */
public abstract class Widget {
    /*tag used for unique name generator*/
    protected final static String TAG = "widget";
    /*unique widget id*/
    protected String id;

    /*if the widget is visible or not*/
    protected boolean visible;
    /*widget's parent*/
    protected Widget parent;

    /*bounds of this widget*/
    protected Rectangle bounds;
    /*precision bounds - for complex shapes*/
    protected Shape precisionBounds;
    /*has precision bounds*/
    protected boolean hasPrecisionBounds;

    /*backgrounds color*/
    protected Color background;
    /*foreground color*/
    protected Color foreground;
    /*image resource if any*/

    //------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new widget.
     * @param id the id of the widget. The id needs to be unique to be able to find widgets on demand.
     */
    public Widget(String id) {
        this.id = id;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new widget where the unique id is randomly generated. This is useful when the retrieving of widgets
     * is not important
     */
    public Widget() {
        /*generate new id*/
        this.id = UniqueNameGenerator.instance().next(TAG);

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Returns the id of this widget.
     */
    public String getId() {
        return id;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the unique id associated with this widget, used for search and identification functions.
     * @param id the unique id of this widget.
     */
    public void setId(String id) {
        this.id = id;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Function is called when a widget needs to be redrawn.
     * @param provider which provider to use to draw the widget.
     * @param region what region needs to be redrawn. This is useful for composite widgets which might not need to redraw
     * all widgets it encloses. If the region is null then the entire widget should be redrawn.
     */
    public abstract void draw(Render2DProvider provider, Rectangle region);
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void invalidate() {
        /*send a new event to redraw the region contained by this widget*/

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets whether or not the widget should be drawn or not.
     * @param visible if the widget should ne drawn.
     */
    public void setVisible(boolean visible) {
        /*check to see if the visible param has changed. If we previously were visible and now we have become hidden,
        * we tell the UiManager to redraw the region previously hidden by this widget*/
        if(!visible && this.visible) {
            UiManager.instance().getEventDispatcher().dispatchEvent
                    (new UiEvent(this.bounds, null, UiEvent.TYPE.REDRAW_REGION));
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the bounds of this widget. If the widget is a complex shape, then this is the smallest rectangle
     * which contains all the points defined by the complex shape of the widget.
     * @return the bounds of the widget
     */
    public Rectangle getBounds() {
        return this.bounds;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns whether or not the shape defined by this widget is a complex shape, and the bounds returned by
     * {@link Widget#getBounds()} isn't the true bounds.
     * @return whether or nor the widget has a complex shape.
     */
    public boolean hasPrecisionBounds() {
        return this.hasPrecisionBounds;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * If {@link edu.catalindumitru.bee.gui.Widget#hasPrecisionBounds()} returns true then this method returns
     * a non null shape which describes the shape of this widget. This is useful for precision event dispatching like
     * mouse click.
     * @return the complex shape of this widget, if it exists.
     */
    public Shape getPrecisionBounds() {
        return this.precisionBounds;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Called when an event effecting this widget has occurred.
     * @param event what event to be handled.
     */
    public void onEvent(UiEvent event){

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------




}
