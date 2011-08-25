package edu.catalindumitru.bee.content.xml;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/12/11
 * Time: 9:57 AM
 */
public interface Node {
    /*---------------------------element types------------------------------*/
    public static final int ELEMENT_NODE = 1;

    public static final int ATTRIBUTE_NODE = 2;

    public static final int TEXT_NODE = 3;

    public static final int CDATA_SECTION_NODE = 4;

    public static final int ENTITY_REFERENCE_NODE = 5;

    public static final int ENTITY_NODE = 6;

    public static final int PROCESSING_INSTRUCTION_NODE = 7;

    public static final int COMMENT_NODE = 8;

    public static final int DOCUMENT_NODE = 9;

    public static final int DOCUMENT_TYPE_NODE = 10;

    public static final int DOCUMENT_FRAGMENT_NODE = 11;

    public static final int NOTATION_NODE = 12;

    /**
     * Appends the specified child to the node.
     *
     * @param child which child to apped
     * @return
     */
    public Node appendChild(Node child);

    /**
     * Clones the node. If <code>deep</code> is set to true, then all the attached child nodes are also clones.
     *
     * @param deep if the cloning should include the child nodes.
     * @return a new Node which is a clone of the original.
     */
    public Node cloneNode(boolean deep);

    /**
     * Returns the attached node children.
     *
     * @return the child nodes.
     */
    public NodeList getChildNodes();

    /**
     * Returns whether or not the node has child nodes.
     *
     * @return if the node has child nodes.
     */
    public boolean hasChildNodes();

    /**
     * Inserts the child before the specified node.
     *
     * @param newChild the child node to insert.
     * @param refChild before which node to insert.
     * @return the inserted child.
     */
    public Node insertBefore(Node newChild, Node refChild);

    /**
     * Removes the child from the child nodes list.
     *
     * @param oldChild which child to remove.
     * @return the removed node.
     */
    public Node removeChild(Node oldChild);

    /**
     * Removes the node (and optional children) from the parent's child list. If the children are nor to be removed,
     * then the nodes will be attached as child nodes to the current parent node.
     *
     * @param removeChildren whether or not the children should be removes as well.
     * @return the removed node.
     */
    public Node removeNode(boolean removeChildren);

    /**
     * Replaces a child with a new node.
     *
     * @param newChild the child node to insert.
     * @param refChild the child to remove.
     * @return the inserted child.
     */
    public Node replaceChild(Node newChild, Node refChild);


    /**
     * Returns the first child node attached to this node.
     *
     * @return the first child node.
     */
    public Node getFirstChild();

    /**
     * Returns the last child attached to this node.
     *
     * @return the last child node.
     */
    public Node getLastChild();

    /**
     * Returns the next sibling in the parent node.
     *
     * @return the next sibling nod.
     */
    public Node getNextSibling();

    /**
     * Returns the previous sibling in the parent node.
     *
     * @return the previous sibling nod.
     */
    public Node getPreviousSibling();

    /**
     * Getter for the node name.
     *
     * @return the node name
     */
    public String getNodeName();

    /**
     * Getter for node type. See {@link Node} for a complete list of node types.
     *
     * @return the node type.
     */
    public short getNodeType();

    /**
     * Getter fo the node value.
     *
     * @return the node value.
     */
    public String getNodeValue();

    /**
     * Setter for the node value.
     *
     * @param nodeValue the new node value.
     */
    public void setNodeValue(String nodeValue);

    /**
     * Getter for the parent node.
     *
     * @return the parent node of this node.
     */
    public Node getParentNode();

    /*------------------------Node conversion ----------------------------*/

    /**
     * Casts the current node to an Element node.
     *
     * @return the same node but converted to an element.
     */
    public Element castToElement();

    /**
     * casts the current node to a Text node.
     *
     * @return the same nde but converted to a text node.
     */
    public TextNode castToTextNode();

}
