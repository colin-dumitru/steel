package edu.catalindumitru.gwt.graphics;

import com.google.gwt.core.client.JavaScriptObject;
import edu.catalindumitru.bee.graphics.Texture;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 9/1/11
 * Time: 4:45 PM
 */
public class GwtTexture extends JavaScriptObject implements Texture{
    protected GwtTexture() {

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public static Texture wrap(GwtTexture texture) {
        return texture;
    }
}
