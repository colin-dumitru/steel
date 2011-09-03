package edu.catalindumitru.gwt.core;

import com.google.gwt.core.client.JavaScriptObject;
import edu.catalindumitru.bee.core.NativeArray;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/30/11
 * Time: 11:00 AM
 */
public class GwtNativeArray<U extends Number> extends JavaScriptObject implements NativeArray {
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected GwtNativeArray() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves the element on the given position.
     *
     * @param pos the position of the element to be retrieved.
     * @return the element on the specified position.
     */
    @Override
    public final native Number get(int pos) /*-{
        return this.get(pos);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the element on the given position.
     *
     * @param pos  the position of the new element.
     * @param elem the new element to be set on the given position.
     */
    @Override
    public final native void set(int pos, Number elem) /*-{
        this.set(pos, elem);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the length of the array.
     *
     * @return the length of the array.
     */
    @Override
    public final native int length() /*-{
        return this.length;
    }-*/;
}
