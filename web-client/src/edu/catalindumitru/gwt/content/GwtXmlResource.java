package edu.catalindumitru.gwt.content;

import com.google.gwt.core.client.JavaScriptObject;
import edu.catalindumitru.bee.content.XmlResource;
import edu.catalindumitru.bee.content.xml.Element;
import edu.catalindumitru.bee.content.xml.Node;
import edu.catalindumitru.bee.content.xml.NodeList;
import edu.catalindumitru.bee.content.xml.TextNode;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/12/11
 * Time: 9:38 AM
 */
public class GwtXmlResource extends JavaScriptObject implements XmlResource {

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected GwtXmlResource() {

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static XmlResource wrap(GwtXmlResource resource) {
        return resource;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new empty element node, without any attributes or child nodes.
     *
     * @param tagName the tag name of the new element.
     * @return a new empty element node.
     */
    @Override
    public final native Element createElement(String tagName) /*-{
        return @edu.catalindumitru.gwt.content.xml.GwtElement::wrap(Ledu/catalindumitru/gwt/content/xml/GwtElement;)(
                this.createElement(tagName)
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new text node, which has the content given by the param <code>content</code>
     *
     * @param content the new content of the text node.
     * @return the newly created text node.
     */
    @Override
    public final native TextNode createTextNode(String content) /*-{
        return @edu.catalindumitru.gwt.content.xml.GwtTextNode::wrap(Ledu/catalindumitru/gwt/content/xml/GwtTextNode;)(
                this.createTextNode(content)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the root node of the document.
     *
     * @return the root node of the document.
     */
    @Override
    public final native Node getRootNode() /*-{
        return @edu.catalindumitru.gwt.content.xml.GwtNode::wrap(Ledu/catalindumitru/gwt/content/xml/GwtNode;)(
                this.documentElement
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns a Node list which have the given tag name.
     *
     * @param name the tag name to search.
     * @return an immutable Node list.
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
     * Searches for a Node with the given id. If no such node exists, then the method returns null. Otherwise, the
     * node which matches the id is returned.
     *
     * @param elementId the id of the element to be searched.
     * @return the element, if one is found matching the id, or null otherwise.
     */
    @Override
    public final native Element getElementById(String elementId) /*-{
        return @edu.catalindumitru.gwt.content.xml.GwtElement::wrap(Ledu/catalindumitru/gwt/content/xml/GwtElement;)(
                this.getElementById(elementId)
        )
    }-*/;
}
