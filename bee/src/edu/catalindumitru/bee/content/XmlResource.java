package edu.catalindumitru.bee.content;

import edu.catalindumitru.bee.content.xml.Element;
import edu.catalindumitru.bee.content.xml.Node;
import edu.catalindumitru.bee.content.xml.NodeList;
import edu.catalindumitru.bee.content.xml.TextNode;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/12/11
 * Time: 9:35 AM
 */

/**
 * An xml document, which ca be used for tree manipulation. Currently the document only supports Element nodes
 * and text Nodes.
 */
public interface XmlResource extends NativeResource {

    /**
     * Creates a new empty element node, without any attributes or child nodes.
     *
     * @param tagName the tag name of the new element.
     * @return a new empty element node.
     */
    public Element createElement(String tagName);

    /**
     * Creates a new text node, which has the content given by the param <code>content</code>
     *
     * @param content the new content of the text node.
     * @return the newly created text node.
     */
    public TextNode createTextNode(String content);

    /**
     * Returns the root node of the document.
     *
     * @return the root node of the document.
     */
    public Node getRootNode();

    /**
     * Returns a Node list which have the given tag name.
     *
     * @param tagName the tag name to search.
     * @return an immutable Node list.
     */
    public NodeList getElementsByTagName(String tagName);

    /**
     * Searches for a Node with the given id. If no such node exists, then the method returns null. Otherwise, the
     * node which matches the id is returned.
     *
     * @param elementId the id of the element to be searched.
     * @return the element, if one is found matching the id, or null otherwise.
     */
    public Element getElementById(String elementId);
}
