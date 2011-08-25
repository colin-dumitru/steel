package edu.catalindumitru.gwt.content.xml;

import com.google.gwt.core.client.JavaScriptObject;
import edu.catalindumitru.bee.content.xml.Node;
import edu.catalindumitru.bee.content.xml.NodeList;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/12/11
 * Time: 11:52 AM
 */
public class GwtNodeList extends JavaScriptObject implements NodeList {
    protected GwtNodeList() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Used to wrap a Node List from a native object to the one defined in gwt.
     *
     * @param nodeList the JavaScript Object received from a JSNI method.
     * @return the gwt Node List object
     */
    public static NodeList wrap(GwtNodeList nodeList) {
        return nodeList;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Getter for the number of nodes.
     *
     * @return the number of nodes in the list.
     */
    @Override
    public final native int getLength() /*-{
        return this.length;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Getter method for a node on the specified position.
     *
     * @param index the index of the node to retrieve.
     * @return the {@link edu.catalindumitru.bee.content.xml.Node} on the specified position.
     */
    @Override
    public final native Node item(int index) /*-{
        return @edu.catalindumitru.gwt.content.xml.GwtNodeList::wrap(Ledu/catalindumitru/gwt/content/xml/GwtNodeList;)(
                this.item(index)
        )
    }-*/;
}
