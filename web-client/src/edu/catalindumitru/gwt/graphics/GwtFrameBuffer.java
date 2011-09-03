package edu.catalindumitru.gwt.graphics;

import com.google.gwt.core.client.JavaScriptObject;
import edu.catalindumitru.bee.graphics.FrameBuffer;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 9/2/11
 * Time: 10:39 AM
 */
public class GwtFrameBuffer extends JavaScriptObject implements FrameBuffer{

    protected GwtFrameBuffer() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static FrameBuffer wrap(GwtFrameBuffer frameBuffer){
        return frameBuffer;
    }
}
