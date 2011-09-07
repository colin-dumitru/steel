package edu.catalindumitru.bee.gui;

import edu.catalindumitru.bee.content.xml.Element;
import edu.catalindumitru.bee.content.xml.Node;
import edu.catalindumitru.bee.content.xml.NodeList;
import edu.catalindumitru.bee.core.ActionDispatcher;
import edu.catalindumitru.bee.core.Logger;
import edu.catalindumitru.bee.graphics.Color;
import edu.catalindumitru.bee.graphics.Render2DProvider;
import edu.catalindumitru.bee.math.Rectangle;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/16/11
 * Time: 9:21 AM
 */
public class Panel extends Widget {
    public static final String LABEL = "panel";

    protected static final String A_LAYOUT = "layout";

    protected static final Color C_DEFAULT_BG = Color.TRANSPARENT;


    public static class Builder implements WidgetBuilder {
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        /**
         * Creates a new widget from the root element.
         *
         *
         * @param root the root element from the xml we are building.
         * @param factory
         * @return the built widget.
         */
        @Override
        public Widget build(Element root, Render2DProvider provider, WidgetFactory factory) {
            if (!root.getTagName().toLowerCase().equals(LABEL))
                return null;

            /*build panel*/
            Panel ret = new Panel();

            Builder.buildProxy(root, ret, provider);

            NodeList children = root.getChildNodes();

            /*create widget children*/
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);

                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    try {
                        Widget subWidget = factory.create(child.castToElement(), provider);

                        /*if the widget is a supported one*/
                        if (subWidget != null) {
                            ret.addChild(subWidget);
                        }
                    } catch (Exception ex) {
                        /*exception building child*/
                        Logger.log(Logger.PRIORITY.ERROR, "Error building child " + child.getNodeName() + " : " +
                                ex.toString());
                    }
                }
            }

            return ret;

        }

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        public static final void buildProxy(Element root, Panel widget, Render2DProvider provider) {
            /*pass the widget through the builder proxy provided by the Widget class to get the common attributes*/
            Widget.builderProxy(widget, root, provider);

            /*create layout*/
            if (root.hasAttribute(A_LAYOUT))
                widget.setLayout(LayoutFactory.instance().createLayout(root.getAttribute(A_LAYOUT)));

            widget.setEnabled(true);
        }
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        /**
         * Returns the root widget this builder can handle.
         *
         * @return the name of the root widget this builder can handle.
         */
        @Override
        public String widgetLabel() {
            return LABEL;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /*Sub widget sorted by drawing priority*/
    protected Queue<Widget> childrenQueue;
    /*Sub widgets sorted by the order they were added*/
    protected List<Widget> childrenList;
    /*Panel dimensions*/
    protected Rectangle dimensions;
    /*layout for packing children*/
    protected Layout layout;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Panel() {
        super();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Panel(String id) {
        super(id);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void initialise() {
        super.initialise();

        this.background = C_DEFAULT_BG;

        this.childrenQueue = new PriorityQueue<Widget>(10, new Widget.ZIndexComparator());
        this.childrenList = new LinkedList<Widget>();
        this.dimensions = new Rectangle(0, 0);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Function is called when a widget needs to be redrawn.
     *
     * @param provider which provider to use to draw the widget.
     * @param region   what region needs to be redrawn. This is useful for composite widgets which might not need to redraw
     *                 all widgets it encloses.
     */
    @Override
    public void draw(Render2DProvider provider, Rectangle region) {
        /*if we are not visible, we should return immediate*/
        if (!this.visible)
            return;

        /*first fill teh background with the background color*/
        provider.setFillStyle(Render2DProvider.STYLE.COLOR);
        provider.setFillColor(this.background);

        /*if the radius is smaller than 0, then we draw a simple recngle*/
        if (this.radius <= 0) {
            provider.fillRectangle((int) this.dimensions.x, (int) this.dimensions.y,
                    (int) this.dimensions.width, (int) this.dimensions.height);
        } else {
            /*otherwise we draw a round rectangle*/
            provider.fillRoundRectangle((int) this.dimensions.x, (int) this.dimensions.y,
                    (int) this.dimensions.width, (int) this.dimensions.height, radius);
        }

        /*draw a border if requested*/
        if (border) {
            /*first fill teh background with the background color*/
            provider.setStrokeStyle(Render2DProvider.STYLE.COLOR);
            provider.setStrokeColor(this.borderColor);
            provider.setLineWidth(this.borderWidth);

            /*if the radius is smaller than 0, then we draw a simple recngle*/
            if (this.radius <= 0) {
                provider.strokeRectangle((int) this.dimensions.x, (int) this.dimensions.y,
                        (int) this.dimensions.width, (int) this.dimensions.height);
            } else {
                /*otherwise we draw a round rectangle*/
                provider.strokeRoundRectangle((int) this.dimensions.x, (int) this.dimensions.y,
                        (int) this.dimensions.width, (int) this.dimensions.height, radius);
            }

        }

        if (this.icon != null) {
            /*if we have a icon image, we draw that one on top of the image*/
            provider.drawImage(this.icon, this.iconAlign.getX(), this.iconAlign.getY(),
                    this.iconAlign.getWidth(), this.iconAlign.getHeight());
        }

        /*also draw the children*/
        this.drawChildren(provider, region);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * This method is called after widgets bounds have been modified.
     */
    @Override
    protected void boundsChanged() {
        this.dimensions.setAll(this.bounds);

        /*pack children to match new dimensions*/
        this.pack();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Given the current layout, we update the bounds of all our children.
     */
    public void pack() {
        if (this.layout != null)
            this.layout.pack();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Layout getLayout() {
        return layout;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setLayout(Layout layout) {
        this.layout = layout;

        this.layout.setChildren(this.childrenList);
        this.layout.setRoot(this);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Draws the children inside this widget. The children will have their drawing space clipped to their bounds,
     * and the center for future drawing of the children will be relative to the current bounds.
     *
     * @param provider which provider to use when drawing.
     * @param region   what region should be redrawn.
     */
    protected void drawChildren(Render2DProvider provider, Rectangle region) {

        /*Iterate through all the children an verify if the region affected by the redraw event intersects
        * the child's bound. If so then it should be redrawn.*/
        for (Widget child : this.childrenQueue) {
            if (child.isVisible() && child.bounds.intersects(region)) {
                provider.pushState();

                /*ensure that the child does not draw outside it's bounds*/
                Rectangle bounds = child.getBounds();
                provider.setRectangleClip((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height);

                /*call the draw method of the child*/
                child.draw(provider, region);

                provider.popState();
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Rectangle getDimensions() {
        return dimensions;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Adds the child to the panels sub widgets. The children will be sorted and drawn in order or their zIndex.
     *
     * @param child which child add.
     */
    public void addChild(Widget child) {
        this.childrenQueue.add(child);
        this.childrenList.add(child);

        /*set ourselves as the widget's parent */
        child.setParent(this);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Removes a child from this panel's sub widgets.
     *
     * @param child which child to remove.
     */
    public void removeChild(Widget child) {
        this.childrenQueue.remove(child);
        this.childrenList.remove(child);

        /*remove the widget's parent*/
        child.setParent(null);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onEvent(UiEvent event) {
        super.onEvent(event);

        for (Widget child : this.childrenList) {
            if(child.isEnabled()) {
                /*dispatch the event only when the region affected of the effect intersects the region of the child widget*/
                if (event.regionAffected == null || child.getBounds().intersects(event.regionAffected))
                    child.onEvent(event);
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void setActionDispatcher(ActionDispatcher actionDispatcher) {
        this.actionDispatcher = actionDispatcher;

        for(Widget child : this.childrenList)
            child.setActionDispatcher(actionDispatcher);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void setEventDispatcher(EventDispatcher eventDispatcher){
        this.eventDispatcher = eventDispatcher;

        for(Widget child : this.childrenList)
            child.setEventDispatcher(eventDispatcher);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
