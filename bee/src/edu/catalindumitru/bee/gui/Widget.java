package edu.catalindumitru.bee.gui;

import edu.catalindumitru.bee.content.ImageResource;
import edu.catalindumitru.bee.core.UniqueNameGenerator;
import edu.catalindumitru.bee.graphics.Color;
import edu.catalindumitru.bee.graphics.Render2DProvider;
import edu.catalindumitru.bee.graphics.Shape;
import edu.catalindumitru.bee.math.Rectangle;
import org.apache.coyote.http11.filters.VoidInputFilter;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/16/11
 * Time: 9:21 AM
 */
public abstract class Widget {
    /**
     * Comparator class used for widget sorting based on their zIndex.
     */
    public static final class ZIndexComparator implements Comparator<Widget> {
        @Override
        public int compare(Widget o1, Widget o2) {
            return o1.getzIndex() - o2.getzIndex();
        }
    };

    //------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------
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
    protected ImageResource icon;
    /*where to align the image resource*/
    protected Rectangle iconAlign;
    /*if the resource is opaque - used when redrawing the widget, if it needs a background redraw*/
    protected boolean opaque;

    /*used for sorting of the drawing process*/
    protected int zIndex;

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
    protected void initialise() {
        this.visible = false;
        this.bounds = new Rectangle(0, 0, 0, 0);
        this.hasPrecisionBounds = false;
        this.opaque = true;

        this.background = Color.BLACK;
        this.foreground = Color.WHITE;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Returns the id of this widget.
     * @return the unique id of the widget.
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

    /**
     * Tells the widget that it needs to be redrawn.
     */
    public void redraw() {
        /*send a new event to redraw the region contained by this widget*/
        UiManager.instance().getEventDispatcher().dispatchEvent
                    (new UiEvent(this.bounds, null, UiEvent.TYPE.REDRAW_REGION));

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
        if(this.visible != visible)
            this.redraw();

        this.visible = visible;
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

    /**
     * Returns the background color used for drawing.
     * @return the background color used for drawing.
     */
    public Color getBackground() {
        return background;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the background color used for drawing.
     * @param background the background color.
     */
    public void setBackground(Color background) {
        this.background = background;

        if(background.getAlpha() < 1.0f)
            this.setOpaque(false);
        else
            this.setOpaque(true);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the foreground color used for drawing.
     * @return the foreground color used for drawing.
     */
    public Color getForeground() {
        return foreground;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Sets the foreground color used for drawing.
     * @param foreground the background color.
     */
    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the image icon used by some widgets. If not image icon is set, then the method will return null.
     * @return the image icon.
     */
    public ImageResource getIcon() {
        return icon;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the image icon used by some widgets. Setting a non null image will trigger this widget to be transparent.
     * If the image icon is null and the background color is opaque, then the opaque param will revert back to full
     * opaque.
     * @param icon the image icon to be used.
     * @param align where to align the image from the top left corner of the widget;s bounds.
     */
    public void setIcon(ImageResource icon, Rectangle align) {
        if((this.icon = icon) == null) {
            this.setOpaque(false);
        } else {
            /*check if current color has is transparent*/
            if(this.background.getAlpha() < 1)
                this.setOpaque(false);
            else
                this.setOpaque(true);
        }

        this.iconAlign = align;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Sets the image icon used by some widgets. Setting a non null image will trigger this widget to be transparent.
     * If the image icon is null and the background color is opaque, then the opaque param will revert back to full
     * opaque. The image will be aligned to the top left corner of the widget.
     * @param icon the image icon to be used.
     */
    public void setIcon(ImageResource icon) {
        Rectangle align = new Rectangle(0, 0, icon.width(), icon.height());

        this.setIcon(icon, align);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Rectangle getIconAlign() {
        return this.iconAlign;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Widget getParent() {
        return parent;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setParent(Widget parent) {
        this.parent = parent;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public boolean isOpaque() {
        return opaque;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setOpaque(boolean opaque) {
        this.opaque = opaque;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public int getzIndex() {
        return zIndex;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------




}
