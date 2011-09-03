package edu.catalindumitru.bee.graphics;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/30/11
 * Time: 5:34 PM
 */
public interface ActiveInfo {
    /**
     * Returns the size of the attribute value.
     *
     * @return the size of the attribute value.
     */
    public int size();

    /**
     * Returns the type of the attribute value. Can be one of the fallowing constants :
     * {@link Render3DProvider#FLOAT_VEC2}, {@link Render3DProvider#FLOAT_VEC3}, {@link Render3DProvider#FLOAT_VEC4}
     * {@link Render3DProvider#FLOAT_MAT2}, {@link Render3DProvider#FLOAT_MAT3}, {@link Render3DProvider#FLOAT_MAT4}
     *
     * @return The type of the active info.
     */
    public int type();

    /**
     * Returns the name of the attribute value.
     *
     * @return the name of the attribute value.
     */
    public String name();
}
