package edu.catalindumitru.gwt.content;

import com.google.gwt.core.client.JavaScriptObject;
import edu.catalindumitru.bee.content.ImageResource;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/9/11
 * Time: 2:09 PM
 */
public class GwtImageResource extends JavaScriptObject implements ImageResource {

    protected GwtImageResource() {
        /*protected constructor as dictated by the JavaScriptObject standard*/
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Returns the height of the unmodified image.
     *
     * @return height of the image
     */
    @Override
    final public native int height() /*-{
        return this.height;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the width of the unmodified image.
     *
     * @return width of the image.
     */
    @Override
    final public native int width() /*-{
        return this.width;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Returns whether or not the image has been fully loaded and converted.
     *
     * @return if the image is complete and ready to use.
     */
    @Override
    final public native boolean complete() /*-{
        return this.complete;
    }-*/;
}
