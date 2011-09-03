package edu.catalindumitru.gwt.graphics;

import com.google.gwt.core.client.JavaScriptObject;
import edu.catalindumitru.bee.graphics.RenderBuffer;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 9/2/11
 * Time: 10:40 AM
 */
public class GwtRenderBuffer extends JavaScriptObject implements RenderBuffer{

    protected GwtRenderBuffer() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static RenderBuffer wrap(GwtRenderBuffer renderBuffer) {
        return  renderBuffer;
    }
}
