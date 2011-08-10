package edu.catalindumitru.bee.gui;

import edu.catalindumitru.bee.graphics.Render2DProvider;
import edu.catalindumitru.bee.math.Rectangle;
import org.apache.coyote.http11.filters.VoidInputFilter;

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
    protected Queue<Widget> children;

    protected Rectangle dimensions;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Panel() {
        super();

        this.initialise();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Panel(String id) {
        super(id);

        this.initialise();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void initialise() {
        super.initialise();

        this.children = new PriorityQueue<Widget> (1, new Widget.ZIndexComparator());
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
        /*first fill teh background with the background color*/
        provider.setFillStyle(Render2DProvider.STYLE.COLOR);
        provider.setFillColor(this.background);
        provider.fillRectangle(0, 0, (int)this.dimensions.getWidth(), (int)this.dimensions.getHeight());

        if(this.icon != null) {
            /*if we have a icon image, we draw that one on top of the image*/
            provider.drawImage(this.icon, this.iconAlign.getX(), this.iconAlign.getY(),
                    this.iconAlign.getWidth(), this.iconAlign.getHeight());
        }

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Draws the children inside this widget. The children will have their drawing space clipped to their bounds,
     * and the center for future drawing of the children will be relative to the current bounds.
     * @param provider which provider to use when drawing.
     * @param region what region should be redrawn.
     */
    protected void drawChildren(Render2DProvider provider, Rectangle region) {
        /*translate drawing center to ensure that the widgets are world aligned*/
        provider.translate(this.bounds.getX(), this.bounds.getY());
        /*translate the region affected to ensure that it is world aligned*/
        region.translate(this.bounds.getX(), this.bounds.getY());

        /*Iterate through all the children an verify if the region affected by the redraw event intersects
        * the child's bound. If so then it should be redrawn.*/
        for(Widget child : this.children) {
            if(child.bounds.intersects(region)){
                /*ensure that the child does not draw outside it's bounds*/
                Rectangle bounds = child.getBounds();
                provider.setClip((int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int)bounds.getHeight());

                /*call the draw method of the child*/
                child.draw(provider, region);
            }
        }

        /*translate the origin back to it's original state*/
        provider.translate(-this.bounds.getX(), -this.bounds.getY());
        /*translate back the region*/
        region.translate(-this.bounds.getX(), -this.bounds.getY());
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Rectangle getDimensions() {
        return dimensions;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setDimensions(Rectangle dimensions) {
        this.dimensions = dimensions;
        this.bounds.setAll(dimensions.getX(), dimensions.getY(), dimensions.getWidth(), dimensions.getHeight());
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setDimensions(int x, int y, int width, int height) {
        this.dimensions.setAll(x, y, width,  height);
        this.bounds.setAll(x, y, width, height);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Adds the child to the panels sub widgets. The children will be sorted and drawn in order or their zIndex.
     * @param child which child add.
     */
    public void addChild(Widget child) {
        this.children.add(child);
        /*set ourselves as the widget's parent */
        child.setParent(this);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Removes a child from this panel's sub widgets.
     * @param child which child to remove.
     */
    public void removeChild(Widget child) {
        this.children.remove(child);
        /*remove the widget's parent*/
        child.setParent(null);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
