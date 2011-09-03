package edu.catalindumitru.gwt.graphics;

import com.google.gwt.core.client.JavaScriptObject;
import edu.catalindumitru.bee.graphics.Shader;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 9/1/11
 * Time: 5:05 PM
 */
public class GwtShader extends JavaScriptObject implements Shader{

    protected GwtShader() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static Shader wrap (GwtShader shader) {
        return shader;
    }
}
