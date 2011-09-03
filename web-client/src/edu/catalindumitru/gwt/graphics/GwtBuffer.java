package edu.catalindumitru.gwt.graphics;

import com.google.gwt.core.client.JavaScriptObject;
import edu.catalindumitru.bee.graphics.Buffer;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 9/2/11
 * Time: 10:15 AM
 */
public class GwtBuffer extends JavaScriptObject implements Buffer{

    protected GwtBuffer() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static Buffer wrap(GwtBuffer buffer) {
        return buffer;
    }
}
