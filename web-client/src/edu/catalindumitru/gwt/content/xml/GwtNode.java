package edu.catalindumitru.gwt.content.xml;

import com.google.gwt.core.client.JavaScriptObject;
import edu.catalindumitru.bee.content.xml.Element;
import edu.catalindumitru.bee.content.xml.Node;
import edu.catalindumitru.bee.content.xml.NodeList;
import edu.catalindumitru.bee.content.xml.TextNode;


/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/12/11
 * Time: 11:45 AM
 */
public class GwtNode extends JavaScriptObject implements Node {
    protected GwtNode() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Used to wrap a Node from a native object to the one defined in gwt.
     *
     * @param node the JavaScript Object received from a JSNI method.
     * @return the gwt Node object
     */
    public static Node wrap(GwtNode node) {
        return node;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Appends the specified child to the node.
     *
     * @param child which child to apped
     * @return
     */
    @Override
    public final native Node appendChild(Node child) /*-{
        this.appendChild(child);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Clones the node. If <code>deep</code> is set to true, then all the attached child nodes are also clones.
     *
     * @param deep if the cloning should include the child nodes.
     * @return a new Node which is a clone of the original.
     */
    @Override
    public final native Node cloneNode(boolean deep) /*-{
        //return the cloned child, but wrapped to a gwt object.
        return @edu.catalindumitru.gwt.content.xml.GwtNode::wrap(Ledu/catalindumitru/gwt/content/xml/GwtNode;)(
                this.cloneNode(deep)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the attached node children.
     *
     * @return the child nodes.
     */
    @Override
    public final native NodeList getChildNodes() /*-{
        return @edu.catalindumitru.gwt.content.xml.GwtNodeList::wrap(Ledu/catalindumitru/gwt/content/xml/GwtNodeList;)(
                this.childNodes
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns whether or not the node has child nodes.
     *
     * @return if the node has child nodes.
     */
    @Override
    public final native boolean hasChildNodes() /*-{
        return this.hasChildNodes();
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Inserts the child before the specified node.
     *
     * @param newChild the child node to insert.
     * @param refChild before which node to insert.
     * @return the inserted child.
     */
    @Override
    public final native Node insertBefore(Node newChild, Node refChild) /*-{
        return @edu.catalindumitru.gwt.content.xml.GwtNode::wrap(Ledu/catalindumitru/gwt/content/xml/GwtNode;)(
                this.insertBefore(newChild, refChild)
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Removes the child from the child nodes list.
     *
     * @param oldChild which child to remove.
     * @return the removed node.
     */
    @Override
    public final native Node removeChild(Node oldChild) /*-{
        return @edu.catalindumitru.gwt.content.xml.GwtNode::wrap(Ledu/catalindumitru/gwt/content/xml/GwtNode;)(
                this.removeChild(oldChild)
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Removes the node (and optional children) from the parent's child list. If the children are nor to be removed,
     * then the nodes will be attached as child nodes to the current parent node.
     *
     * @param removeChildren whether or not the children should be removes as well.
     * @return the removed node.
     */
    @Override
    public final native Node removeNode(boolean removeChildren) /*-{
        return @edu.catalindumitru.gwt.content.xml.GwtNode::wrap(Ledu/catalindumitru/gwt/content/xml/GwtNode;)(
                this.removeNode(removeChildren)
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Replaces a child with a new node.
     *
     * @param newChild the child node to insert.
     * @param refChild the child to remove.
     * @return the inserted child.
     */
    @Override
    public final native Node replaceChild(Node newChild, Node refChild) /*-{
        return @edu.catalindumitru.gwt.content.xml.GwtNode::wrap(Ledu/catalindumitru/gwt/content/xml/GwtNode;)(
                this.replaceChild(newChild, refChild)
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the first child node attached to this node.
     *
     * @return the first child node.
     */
    @Override
    public final native Node getFirstChild() /*-{
        return @edu.catalindumitru.gwt.content.xml.GwtNode::wrap(Ledu/catalindumitru/gwt/content/xml/GwtNode;)(
                this.firstChild
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the last child attached to this node.
     *
     * @return the last child node.
     */
    @Override
    public final native Node getLastChild() /*-{
        return @edu.catalindumitru.gwt.content.xml.GwtNode::wrap(Ledu/catalindumitru/gwt/content/xml/GwtNode;)(
                this.lastChild
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the next sibling in the parent node.
     *
     * @return the next sibling nod.
     */
    @Override
    public final native Node getNextSibling() /*-{
        return @edu.catalindumitru.gwt.content.xml.GwtNode::wrap(Ledu/catalindumitru/gwt/content/xml/GwtNode;)(
                this.nextSibling
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the previous sibling in the parent node.
     *
     * @return the previous sibling nod.
     */
    @Override
    public final native Node getPreviousSibling() /*-{
        return @edu.catalindumitru.gwt.content.xml.GwtNode::wrap(Ledu/catalindumitru/gwt/content/xml/GwtNode;)(
                this.previousSibling
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Getter for the node name.
     *
     * @return the node name
     */
    @Override
    public final native String getNodeName() /*-{
        return this.nodeName;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Getter for node type. See {@link edu.catalindumitru.bee.content.xml.Node} for a complete list of node types.
     *
     * @return the node type.
     */
    @Override
    public final native short getNodeType() /*-{
        return this.nodeType;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Getter fo the node value.
     *
     * @return the node value.
     */
    @Override
    public final native String getNodeValue() /*-{
        if (this.nodeValue == null)
            return "";
        else
            return this.nodeValue;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Setter for the node value.
     *
     * @param nodeValue the new node value.
     */
    @Override
    public final native void setNodeValue(String nodeValue) /*-{
        this.nodeValue = nodeValue;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Getter for the parent node.
     *
     * @return the parent node of this node.
     */
    @Override
    public final native Node getParentNode() /*-{
        return @edu.catalindumitru.gwt.content.xml.GwtNode::wrap(Ledu/catalindumitru/gwt/content/xml/GwtNode;)(
                this.parentNode
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Casts the current node to an Element node.
     *
     * @return the same node but converted to an element.
     */
    @Override
    public final Element castToElement() {
        return (Element) this;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * casts the current node to a Text node.
     *
     * @return the same nde but converted to a text node.
     */
    @Override
    public final TextNode castToTextNode() {
        return (TextNode) this;
    }
}
