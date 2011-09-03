package edu.catalindumitru.gwt.graphics;

import com.google.gwt.core.client.JavaScriptObject;
import edu.catalindumitru.bee.graphics.ActiveInfo;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 9/2/11
 * Time: 9:16 AM
 */
public class GwtActiveInfo extends JavaScriptObject implements ActiveInfo {

    protected GwtActiveInfo() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static ActiveInfo wrap(GwtActiveInfo info) {
        return info;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Returns the size of the attribute value.
     *
     * @return the size of the attribute value.
     */
    @Override
    public final native int size() /*-{
        return this.size;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Returns the type of the attribute value. Can be one of the fallowing constants :
     * {@link edu.catalindumitru.bee.graphics.Render3DProvider#FLOAT_VEC2},
     * {@link edu.catalindumitru.bee.graphics.Render3DProvider#FLOAT_VEC3},
     * {@link edu.catalindumitru.bee.graphics.Render3DProvider#FLOAT_VEC4}
     * {@link edu.catalindumitru.bee.graphics.Render3DProvider#FLOAT_MAT2},
     * {@link edu.catalindumitru.bee.graphics.Render3DProvider#FLOAT_MAT3},
     * {@link edu.catalindumitru.bee.graphics.Render3DProvider#FLOAT_MAT4}
     *
     * @return The type of the active info.
     */
    @Override
    public final native int type() /*-{
        return this.type;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Returns the name of the attribute value.
     *
     * @return the name of the attribute value.
     */
    @Override
    public final native String name() /*-{
        return this.name;
    }-*/;
}
