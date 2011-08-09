package edu.catalindumitru.bee.graphics;

import edu.catalindumitru.bee.math.Point2D;
import edu.catalindumitru.bee.math.Rectangle;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/1/11
 * Time: 10:31 AM
 */
public class Shape {
    protected List<Point2D> point2DList;

    /*a cached array representing the list of points as a simple array. This is useful for fast searching / manipulating
    * modifying this array does not affect the point2DList list*/
    protected Point2D[] cachedPoints;
    /*If the cached array need a refresh*/
    protected boolean needsCacheRefresh;
    /*If the shape should be filled */
    protected boolean filled;
    /*If the shape should be stroked*/
    protected boolean stroked;
    /*the smallest rectangle which encompasses all of the points*/
    protected Rectangle bounds;
    /*bounds need to be updated*/
    protected boolean needsBoundsUpdate;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Shape() {
        this.point2DList = new LinkedList<Point2D>();
        this.bounds = new Rectangle();

        this.needsCacheRefresh = true;
        this.needsBoundsUpdate = true;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Adds the specified point to the back of the list.
     *
     * @param what which point to add
     */
    public void addPoint(Point2D what) {
        this.point2DList.add(what);

        this.needsCacheRefresh = true;
        this.needsBoundsUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Adds the specified point to the position given by "what".
     *
     * @param what  which point to add
     * @param where where to add the specified point
     */
    public void addPoint(Point2D what, int where) {
        this.point2DList.add(where, what);

        this.needsCacheRefresh = true;
        this.needsBoundsUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Removes the point from the specified position.
     *
     * @param where from where the element should be removed.
     */
    public void removePoint(int where) {
        this.point2DList.remove(where);

        this.needsCacheRefresh = true;
        this.needsBoundsUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Remove the specified point (points if they have the same coordinates) from the list.
     *
     * @param what which point to remove from the list. Given the nature of floating point variables, this method might
     *             not detect correctly equal points.
     */
    public void removePoint(Point2D what) {
        this.point2DList.remove(what);

        this.needsCacheRefresh = true;
        this.needsBoundsUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the point with the position specified by the parameter "what".
     *
     * @param where where the point is located
     * @return the point from the position specified, or null if the position is invalid.
     */
    public Point2D getPoint(int where) {
        return this.point2DList.get(where);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Adds all the points from the specified list. The old points are removed.
     *
     * @param pointList which point list should replace the current one.
     */
    public void setPointList(List<Point2D> pointList) {
        this.point2DList.clear();
        this.point2DList.addAll(pointList);

        this.needsCacheRefresh = true;
        this.needsBoundsUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Adds the given point list to the current list. The old list preserved.
     *
     * @param pointList which points to add to the current list.
     */
    public void addPointList(List<Point2D> pointList) {
        this.point2DList.addAll(pointList);

        this.needsCacheRefresh = true;
        this.needsBoundsUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Removes all points ftom the list.
     */
    public void clearPointList() {
        this.point2DList.clear();

        this.needsCacheRefresh = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the full point list.
     *
     * @return list of points in the order which represents the shape.
     */
    public List<Point2D> getPointList() {
        return this.point2DList;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Point2D[] getPointArray() {
        /*Update cache if needed*/
        if (this.needsCacheRefresh || this.cachedPoints == null) {
            this.cachedPoints = (Point2D[]) this.point2DList.toArray();
            this.needsCacheRefresh = false;
        }

        return this.cachedPoints;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Checks whether the point is contained in the shape.
     *
     * @param point what point to test.
     * @return true or false, depending whether or not the point is int the shape.
     */
    public boolean containsPoint(Point2D point) {

        boolean ret = false;
        /*Lazy initialisation*/
        Point2D points[] = this.getPointArray();

        /*TODO found this on the internet. Hope it works.*/

        for (int i = 0, j = points.length - 1; i < points.length; j = i++) {
            if (((points[i].getY() > point.getY()) != (points[j].getY() > point.getY())) &&
                    (point.getX() < (points[j].getX() - points[i].getX())
                            * (point.getY() - points[i].getY()) / (points[j].getY() - points[i].getY()) + points[i].getX()))

                ret = !ret;
        }
        return ret;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public boolean isFilled() {
        return filled;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public boolean isStroked() {
        return stroked;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setStroked(boolean stroked) {
        this.stroked = stroked;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void updateBounds() {
        /*maximum and minimum baounds for all the points*/
        float minX = Float.MAX_VALUE - 1;
        float minY = Float.MAX_VALUE - 1;
        float maxX = Float.MIN_VALUE + 1;
        float maxY = Float.MIN_VALUE + 1;

        /*parse through all the points and find the minimum and maximum values for the baounds*/
        for (Point2D point : this.point2DList) {
            if (point.getX() < minX)
                minX = point.getX();
            if (point.getY() < minY)
                minY = point.getY();
            if (point.getX() > maxX)
                maxX = point.getX();
            if (point.getY() > maxY)
                maxY = point.getY();

            this.bounds.setAll(minX, minY, maxX - minX, maxY - minY);
        }

        this.needsBoundsUpdate = false;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Rectangle getBounds() {
        if (this.needsBoundsUpdate)
            this.updateBounds();


        return this.bounds;
    }
}
