package edu.catalindumitru.bee.gui;

import edu.catalindumitru.bee.content.ImageResource;
import edu.catalindumitru.bee.content.xml.Element;
import edu.catalindumitru.bee.core.Action;
import edu.catalindumitru.bee.core.ActionDispatcher;
import edu.catalindumitru.bee.core.UniqueNameGenerator;
import edu.catalindumitru.bee.graphics.Color;
import edu.catalindumitru.bee.graphics.Render2DProvider;
import edu.catalindumitru.bee.graphics.Shape;
import edu.catalindumitru.bee.math.Rectangle;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/16/11
 * Time: 9:21 AM
 */
public abstract class Widget {
    public static final String A_WIDTH = "width";
    public static final String A_HEIGHT = "height";
    public static final String A_BGCOLOR = "bgcolor";
    public static final String A_FGCOLOR = "fgcolor";
    public static final String A_VISIBLE = "visible";
    public static final String A_ID = "id";
    public static final String A_VOFFSET = "voffset";
    public static final String A_HOFFSET = "hoffset";
    public static final String A_VALIGN = "valign";
    public static final String A_HALIGN = "halign";
    public static final String A_ZINDEX = "zIndex";
    public static final String A_RADIUS = "radius";
    public static final String A_HAS_BORDER = "border";
    public static final String A_BORDER_RADIUS = "borderWidth";
    public static final String A_BORDER_COLOR = "borderColor";
    public static final String A_ON_CLICK = "onClick";
    public static final String A_ON_HOVER_IN = "onHoverIn";
    public static final String A_ON_HOVER_OUT = "onHoverOut";
    public static final String A_ON_DOUBLE_CLICK = "onDoubleClick";

    public static final Integer E_CLICK = 1;
    public static final Integer E_MOUSE_HOVER_IN = 2;
    public static final Integer E_MOUSE_HOVER_OUT = 3;
    public static final Integer E_DOUBLE_CLICK = 4;

    /**
     * Comparator class used for widget sorting based on their zIndex.
     */
    public static final class ZIndexComparator implements Comparator<Widget> {
        @Override
        public int compare(Widget o1, Widget o2) {
            return o1.getzIndex() - o2.getzIndex();
        }
    }

    ;

    //------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------
    public static enum VERTICAL_ALIGN {
        TOP,
        CENTER,
        BOTTOM
    }

    public static enum HORIZONTAL_ALIGN {
        LEFT,
        CENTER,
        RIGHT
    }


    /*tag used for unique name generator*/
    protected final static String TAG = "widget";
    /*unique widget id*/
    protected String id;

    /*if the widget is visible or not*/
    protected boolean visible;
    /*if teh cursor is currently hovering over the widget*/
    protected boolean hovered;
    /*if the widget should listen for actions - default false to minimise call cycles to onEvent*/
    protected boolean enabled;
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
    /*corner radius*/
    protected int radius;

    /*has border*/
    protected boolean border;
    /*border width;*/
    protected int borderWidth;
    /*border color*/
    protected Color borderColor;

    /*used for sorting of the drawing process*/
    protected int zIndex;

    /*vertical anchor*/
    protected VERTICAL_ALIGN verticalAlign;
    /*horizontal anchor*/
    protected HORIZONTAL_ALIGN horizontalAlign;
    /*vertical offset from anchor*/
    protected int verticalOffset;
    /*horizontal offset from anchor*/
    protected int horizontalOffset;

    /*used to distribute ui events to a controller proxy*/
    protected Map<Integer, String> registeredActions;


    //------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new widget.
     *
     * @param id the id of the widget. The id needs to be unique to be able to find widgets on demand.
     */
    public Widget(String id) {
        this.id = id;

        this.initialise();
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

        this.initialise();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Used by widgets builders which can pass the root element of the widget to be built, so that common attributes
     * amongst all widgets (like background colors, id's etc) can be build here.
     *
     * @param widget the already build, but empty widget.
     * @param root   the root element of the widget to be built.
     */
    public static void builderProxy(Widget widget, Element root) {
        /*unique id*/
        if (root.hasAttribute(A_ID))
            widget.setId(root.getAttribute(A_ID));

        /*visibility*/
        if (root.hasAttribute(A_VISIBLE))
            widget.setVisible(Boolean.parseBoolean(root.getAttribute(A_VISIBLE)));
        else
            /*default value*/
            widget.setVisible(true);

        /*update dimensions*/
        widget.setBounds(
                0,
                0,
                root.hasAttribute(A_WIDTH) ? UiManager.instance().getRenderProvider().convertDimension(root.getAttribute(A_WIDTH)) : 0,
                root.hasAttribute(A_HEIGHT) ? UiManager.instance().getRenderProvider().convertDimension(root.getAttribute(A_HEIGHT)) : 0,
                false);

        /*Zindex*/
        if (root.hasAttribute(A_ZINDEX))
            widget.setzIndex(Integer.parseInt(root.getAttribute(A_ZINDEX)));

        /*Zindex*/
        if (root.hasAttribute(A_RADIUS))
            widget.setRadius(Integer.parseInt(root.getAttribute(A_RADIUS)));

        /*foregroung and background colors*/
        if (root.hasAttribute(A_FGCOLOR))
            widget.setForeground(UiManager.instance().getRenderProvider().convertColor(root.getAttribute(A_FGCOLOR)));

        if (root.hasAttribute(A_BGCOLOR))
            widget.setBackground(UiManager.instance().getRenderProvider().convertColor(root.getAttribute(A_BGCOLOR)));

        /*offset and align*/
        if (root.hasAttribute(A_VOFFSET))
            widget.setVerticalOffset(UiManager.instance().getRenderProvider().convertDimension(root.getAttribute(A_VOFFSET)));

        if (root.hasAttribute(A_HOFFSET))
            widget.setHorizontalOffset(UiManager.instance().getRenderProvider().convertDimension(root.getAttribute(A_HOFFSET)));

        if (root.hasAttribute(A_HALIGN)) {
            String align = root.getAttribute(A_HALIGN);

            if (align.equals("left"))
                widget.setHorizontalAlign(HORIZONTAL_ALIGN.LEFT);
            else if (align.equals("center"))
                widget.setHorizontalAlign(HORIZONTAL_ALIGN.CENTER);
            else if (align.equals("right"))
                widget.setHorizontalAlign(HORIZONTAL_ALIGN.RIGHT);
        }

        /*border related attribuites*/
        if (root.hasAttribute(A_HAS_BORDER))
            widget.setBorder(Boolean.parseBoolean(root.getAttribute(A_HAS_BORDER)));

        if (root.hasAttribute(A_BORDER_RADIUS))
            widget.setBorderWidth(Integer.parseInt(root.getAttribute(A_BORDER_RADIUS)));

        if (root.hasAttribute(A_BORDER_COLOR))
            widget.setBorderColor(UiManager.instance().getRenderProvider().convertColor(root.getAttribute(A_BORDER_COLOR)));

        /*actions*/
        if (root.hasAttribute(A_ON_CLICK))
            widget.registerAction(E_CLICK, root.getAttribute(A_ON_CLICK));

        if (root.hasAttribute(A_ON_DOUBLE_CLICK))
            widget.registerAction(E_DOUBLE_CLICK, root.getAttribute(A_ON_DOUBLE_CLICK));

        if (root.hasAttribute(A_ON_HOVER_IN))
            widget.registerAction(E_MOUSE_HOVER_IN, root.getAttribute(A_ON_HOVER_IN));

        if (root.hasAttribute(A_ON_HOVER_OUT))
            widget.registerAction(E_MOUSE_HOVER_OUT, root.getAttribute(A_ON_HOVER_OUT));


        if (root.hasAttribute(A_VALIGN)) {
            String align = root.getAttribute(A_VALIGN);

            if (align.equals("top"))
                widget.setVerticalAlign(VERTICAL_ALIGN.TOP);
            else if (align.equals("center"))
                widget.setVerticalAlign(VERTICAL_ALIGN.CENTER);
            else if (align.equals("bottom"))
                widget.setVerticalAlign(VERTICAL_ALIGN.BOTTOM);
        }

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void initialise() {
        this.visible = true;
        this.bounds = new Rectangle(0, 0, 0, 0);
        this.hasPrecisionBounds = false;
        this.opaque = true;

        this.background = Color.BLACK;
        this.foreground = Color.WHITE;

        this.horizontalAlign = HORIZONTAL_ALIGN.CENTER;
        this.verticalAlign = VERTICAL_ALIGN.TOP;
        this.horizontalOffset = 0;
        this.verticalOffset = 0;

        this.borderColor = Color.WHITE;
        this.borderWidth = 1;
        this.border = false;

        this.registeredActions = new TreeMap<Integer, String>();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the id of this widget.
     *
     * @return the unique id of the widget.
     */
    public String getId() {
        return id;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the unique id associated with this widget, used for search and identification functions.
     *
     * @param id the unique id of this widget.
     */
    public void setId(String id) {
        this.id = id;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Function is called when a widget needs to be redrawn.
     *
     * @param provider which provider to use to draw the widget.
     * @param region   what region needs to be redrawn. This is useful for composite widgets which might not need to redraw
     *                 all widgets it encloses. If the region is null then the entire widget should be redrawn.
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
     *
     * @param visible if the widget should ne drawn.
     */
    public void setVisible(boolean visible) {
        /*check to see if the visible param has changed. If we previously were visible and now we have become hidden,
        * we tell the UiManager to redraw the region previously hidden by this widget*/
        if (this.visible != visible)
            this.redraw();

        this.visible = visible;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns whether or not the widget is visible and should ne drawn.
     *
     * @return if the widget is visible.
     */
    public boolean isVisible() {
        return this.visible;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the bounds of this widget. If the widget is a complex shape, then this is the smallest rectangle
     * which contains all the points defined by the complex shape of the widget.
     *
     * @return the bounds of the widget
     */
    public Rectangle getBounds() {
        return this.bounds;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the bounds of the widgets.
     *
     * @param bounds the new bounds of the widget.
     */
    public void setBounds(Rectangle bounds) {
        this.bounds.setAll(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());

        this.boundsChanged();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the bounds of the widgets.
     *
     * @param bounds the new bounds of the widget.
     * @param update if the update call should be made for bounds changed. This should be set to false for when building
     *               an ui, and a pack call should be made only after the ui has finished building.
     */
    public void setBounds(Rectangle bounds, boolean update) {
        this.bounds.setAll(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());

        if (update)
            this.boundsChanged();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the bounds of the widget.
     *
     * @param x      the new x coordinate of the widget.
     * @param y      the new y coordinate of the widget.
     * @param width  the new width of the widget.
     * @param height the new height of the widget.
     */
    public void setBounds(int x, int y, int width, int height) {
        this.bounds.setAll(x, y, width, height);

        this.boundsChanged();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the bounds of the widget.
     *
     * @param x      the new x coordinate of the widget.
     * @param y      the new y coordinate of the widget.
     * @param width  the new width of the widget.
     * @param height the new height of the widget.
     * @param update if the update call should be made for bounds changed. This should be set to false for when building
     *               an ui, and a pack call should be made only after the ui has finished building.
     */
    public void setBounds(int x, int y, int width, int height, boolean update) {
        this.bounds.setAll(x, y, width, height);

        if (update)
            this.boundsChanged();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets just the x an y coordinates of the bounds. The width and height remain unmodified.
     *
     * @param x the new x coordinate of the widget.
     * @param y the new y coordinate of the widget.
     */
    public void setBounds(int x, int y) {
        this.bounds.setX(x);
        this.bounds.setY(y);

        this.boundsChanged();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets just the x an y coordinates of the bounds. The width and height remain unmodified.
     *
     * @param x      the new x coordinate of the widget.
     * @param y      the new y coordinate of the widget.
     * @param update if the update call should be made for bounds changed. This should be set to false for when building
     *               an ui, and a pack call should be made only after the ui has finished building.
     */
    public void setBounds(int x, int y, boolean update) {
        this.bounds.setX(x);
        this.bounds.setY(y);

        if (update)
            this.boundsChanged();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * This method is called after widgets bounds have been modified.
     */
    protected void boundsChanged() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns whether or not the shape defined by this widget is a complex shape, and the bounds returned by
     * {@link Widget#getBounds()} isn't the true bounds.
     *
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
     *
     * @return the complex shape of this widget, if it exists.
     */
    public Shape getPrecisionBounds() {
        return this.precisionBounds;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public boolean hasActions() {
        return this.registeredActions.size() > 0;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Called when an event effecting this widget has occurred.
     *
     * @param event what event to be handled.
     */
    public void onEvent(UiEvent event) {
        if (!this.enabled)
            return;

        /*caching*/
        float params[] = event.getParams();
        String action = null;

        switch (event.getType()) {

            case MOUSE_CLICK:
                /*check if the click event intersects us and then dispatch the action*/
                if (this.bounds.contains(params[0], params[1])) {

                    action = this.registeredActions.get(E_CLICK);
                    if (action != null)
                        this.dispatchAction(action);

                }
                break;

            case MOUSE_DOUBLE_CLICK:
                /*check if the click event intersects us and then dispatch the action*/
                if (this.bounds.contains(params[0], params[1])) {

                    action = this.registeredActions.get(E_DOUBLE_CLICK);
                    if (action != null)
                        this.dispatchAction(action);

                }
                break;

            case MOUSE_MOVE:
                /*if previously was hovering and now it is out of out bounds, then we send a hover out event*/
                if (this.hovered) {
                    if (!this.bounds.contains(params[0], params[1])) {
                        this.hovered = false;


                        action = this.registeredActions.get(E_MOUSE_HOVER_OUT);
                        if (action != null)
                            this.dispatchAction(action);

                    }

                } else {
                    /*if previously we were not under the cursor, but now we are, then we send a mouse hover in event.*/
                    if (this.bounds.contains(params[0], params[1])) {
                        this.hovered = true;


                        action = this.registeredActions.get(E_MOUSE_HOVER_IN);
                        if (action != null)
                            this.dispatchAction(action);

                    }

                }
                break;

        }


    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the background color used for drawing.
     *
     * @return the background color used for drawing.
     */
    public Color getBackground() {
        return background;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the background color used for drawing.
     *
     * @param background the background color.
     */
    public void setBackground(Color background) {
        this.background = background;

        if (background.getAlpha() < 1.0f)
            this.setOpaque(false);
        else
            this.setOpaque(true);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the foreground color used for drawing.
     *
     * @return the foreground color used for drawing.
     */
    public Color getForeground() {
        return foreground;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the foreground color used for drawing.
     *
     * @param foreground the background color.
     */
    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the image icon used by some widgets. If not image icon is set, then the method will return null.
     *
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
     *
     * @param icon  the image icon to be used.
     * @param align where to align the image from the top left corner of the widget;s bounds.
     */
    public void setIcon(ImageResource icon, Rectangle align) {
        if ((this.icon = icon) == null) {
            this.setOpaque(false);
        } else {
            /*check if current color has is transparent*/
            if (this.background.getAlpha() < 1)
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
     *
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

    public VERTICAL_ALIGN getVerticalAlign() {
        return verticalAlign;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setVerticalAlign(VERTICAL_ALIGN verticalAlign) {
        this.verticalAlign = verticalAlign;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public HORIZONTAL_ALIGN getHorizontalAlign() {
        return horizontalAlign;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setHorizontalAlign(HORIZONTAL_ALIGN horizontalAlign) {
        this.horizontalAlign = horizontalAlign;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public int getVerticalOffset() {
        return verticalOffset;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setVerticalOffset(int verticalOffset) {
        this.verticalOffset = verticalOffset;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public int getHorizontalOffset() {
        return horizontalOffset;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setHorizontalOffset(int horizontalOffset) {
        this.horizontalOffset = horizontalOffset;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public int getRadius() {
        return radius;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setRadius(int radius) {
        this.radius = radius;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public boolean isBorder() {
        return border;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setBorder(boolean border) {
        this.border = border;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public int getBorderWidth() {
        return borderWidth;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Color getBorderColor() {
        return borderColor;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void registerAction(Integer actionType, String actionName) {
        this.registeredActions.put(actionType, actionName);

        this.enabled = true;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void unregisterAction(Integer actionType) {
        this.registeredActions.remove(actionType);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void dispatchAction(String actionName) {
        ActionDispatcher.instance().dispatchAction(new Action(actionName, null));
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public boolean isEnabled() {
        return enabled;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------


}
