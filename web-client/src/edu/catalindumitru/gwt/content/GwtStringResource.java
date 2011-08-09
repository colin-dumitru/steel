package edu.catalindumitru.gwt.content;

import com.google.gwt.core.client.JavaScriptObject;
import edu.catalindumitru.bee.content.StringResource;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/9/11
 * Time: 6:54 PM
 */
public class GwtStringResource extends JavaScriptObject implements StringResource {

    protected GwtStringResource() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Returns the string resource saved saved.
     *
     * @return the string resource.
     */
    @Override
    public final native String getString() /*-{
        return this.text;
    }-*/;
}
