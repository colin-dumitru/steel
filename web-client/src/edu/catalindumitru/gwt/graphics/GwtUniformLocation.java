package edu.catalindumitru.gwt.graphics;

import com.google.gwt.core.client.JavaScriptObject;
import edu.catalindumitru.bee.graphics.UniformLocation;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 9/2/11
 * Time: 9:30 AM
 */
public class GwtUniformLocation extends JavaScriptObject implements UniformLocation{

    protected GwtUniformLocation() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static UniformLocation wrap(GwtUniformLocation location) {
        return location;
    }
}
