package edu.catalindumitru.bee.graphics;

import edu.catalindumitru.bee.math.Point2D;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/2/11
 * Time: 9:46 AM
 */
public class RectangleShape extends Shape {
    protected float x;
    protected float y;
    protected float width;
    protected float height;

    /*If the list of points needs a refresh after coordinates of the dimensions have changed*/
    protected boolean needsListRefresh;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public RectangleShape(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.needsListRefresh = true;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public RectangleShape(float width, float height) {
        this(0, 0, width, height);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public RectangleShape() {
        this(0, 0, 0, 0);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public float getX() {
        return x;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setX(float x) {
        this.x = x;

        this.needsListRefresh = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public float getY() {
        return y;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setY(float y) {
        this.y = y;

        this.needsListRefresh = true;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public float getWidth() {
        return width;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setWidth(float width) {
        this.width = width;

        this.needsListRefresh = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public float getHeight() {
        return height;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setHeight(float height) {
        this.height = height;

        this.needsListRefresh = true;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public List<Point2D> getPointList() {
         if(this.needsListRefresh) {
             this.needsListRefresh = false;
             /*we also need to update the point cache*/
             this.needsCacheRefresh = true;

             /*recalculate points for the shape*/
             this.point2DList.clear();

             this.point2DList.add(new Point2D(this.x, this.y));
             this.point2DList.add(new Point2D(this.x + this.width, this.y));
             this.point2DList.add(new Point2D(this.x + this.width, this.y + this.height));
             this.point2DList.add(new Point2D(this.x, this.y + this.height));

         }

        return this.point2DList;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public Point2D[] getPointArray() {
        /*Update list array*/
        this.getPointList();

        return super.getPointArray();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Overrides the super class to provide faster calculation for a simple point and reclangle.
     * @param what which point to calculate if whether it is conatined in the recnagle
     * @return true of false, depending whether or not the point is inside or on the edge of the rectangle
     */
    @Override
    public boolean containsPoint(Point2D what) {
        return ((what.getX() >= this.x) && (what.getY() >= this.y) &&(what.getX() <= this.x + this.width)
                && (what.getY() <= this.getY() + this.height));

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
