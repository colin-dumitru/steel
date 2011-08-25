package edu.catalindumitru.gwt.content.xml;

import edu.catalindumitru.bee.content.xml.Element;
import edu.catalindumitru.bee.content.xml.NodeList;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/12/11
 * Time: 12:15 PM
 */
public class GwtElement extends GwtNode implements Element {

    protected GwtElement() {

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static Element wrap(GwtElement element) {
        return element;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Function to test whether or not the element has the specified attribute.
     *
     * @param name which attribute to test for existence.
     * @return if the attribute exists in the element node.
     */
    @Override
    public final native boolean hasAttribute(String name) /*-{
        return this.hasAttribute(name);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the attributes value, if it exits.
     *
     * @param name the name of attribute.
     * @return the value of the attribute.
     */
    @Override
    public final native String getAttribute(String name) /*-{
        return this.getAttribute(name);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the complete list of attributes which the element has.
     *
     * @return an array or strings which represent attributes stored in the element.
     */
    @Override
    public final native Map<String, String> getAttributes() /*-{
        var ret = @java.util.TreeMap::new()();

        for (a = 0; a < this.attributes.length; a++) {
            if (this.attributes[a].name != null)
                ret.@java.util.Map::put(Ljava/lang/Object;Ljava/lang/Object;)(this.attributes[a].name,
                        this.attributes[a].value);

            console.log(this.attributes[a].name);
        }

        return ret;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Remove the attribute from element node.
     *
     * @param name the name of the attribute.
     */
    @Override
    public final native void removeAttribute(String name) /*-{
        this.removeAttribute(name);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Setter for an attribute.
     *
     * @param name  the name of the attribute. If the attribute does not exists, it will be created.
     * @param value the new value of the attribute.
     */
    @Override
    public final native void setAttribute(String name, String value) /*-{
        this.setAttribute(name, value);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns an immutable list of child nodes nodes which have the specified tag name.
     *
     * @param name the tag name.
     * @return an immutable node list.
     */
    @Override
    public final native NodeList getElementsByTagName(String name) /*-{
        return @edu.catalindumitru.gwt.content.xml.GwtNodeList::wrap(Ledu/catalindumitru/gwt/content/xml/GwtNodeList;)(
                this.getElementsByTagName(name)
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the tag name of the node.
     *
     * @return the tag name.
     */
    @Override
    public final native String getTagName() /*-{
        return this.tagName;
    }-*/;
}
