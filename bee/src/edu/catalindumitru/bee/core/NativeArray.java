package edu.catalindumitru.bee.core;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/29/11
 * Time: 6:12 PM
 */
public interface NativeArray<U extends Number> {
    /**
     * Retrieves the element on the given position.
     *
     * @param pos the position of the element to be retrieved.
     * @return the element on the specified position.
     */
    public U get(int pos);

    /**
     * Sets the element on the given position.
     *
     * @param pos  the position of the new element.
     * @param elem the new element to be set on the given position.
     */
    public void set(int pos, U elem);

    /**
     * Returns the length of the array.
     *
     * @return the length of the array.
     */
    public int length();
}
