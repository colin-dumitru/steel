package edu.catalindumitru.gwt.graphics;

import com.google.gwt.core.client.JavaScriptObject;
import edu.catalindumitru.bee.graphics.ShaderProgram;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 9/1/11
 * Time: 5:00 PM
 */
public class GwtShaderProgram extends JavaScriptObject implements ShaderProgram{

    protected GwtShaderProgram() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static ShaderProgram wrap(GwtShaderProgram program) {
        return program;
    }
}
