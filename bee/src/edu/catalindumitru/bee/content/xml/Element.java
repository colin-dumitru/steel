package edu.catalindumitru.bee.content.xml;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/12/11
 * Time: 9:53 AM
 */

import java.util.Map;

/**
 * Node element used in parsing xml documents. The difference between this and a normal node is that elements have
 * attributes.
 */
public interface Element extends Node {

    /**
     * Function to test whether or not the element has the specified attribute.
     *
     * @param name which attribute to test for existence.
     * @return if the attribute exists in the element node.
     */
    public boolean hasAttribute(String name);

    /**
     * Returns the attributes value, if it exits.
     *
     * @param name the name of attribute.
     * @return the value of the attribute.
     */
    public String getAttribute(String name);

    /**
     * Returns the complete list of attributes which the element has.
     *
     * @return an array or strings which represent attributes stored in the element.
     */
    public Map<String, String> getAttributes();

    /**
     * Remove the attribute from element node.
     *
     * @param name the name of the attribute.
     */
    public void removeAttribute(String name);

    /**
     * Setter for an attribute.
     *
     * @param name  the name of the attribute. If the attribute does not exists, it will be created.
     * @param value the new value of the attribute.
     */
    public void setAttribute(String name, String value);

    /**
     * Returns an immutable list of child nodes nodes which have the specified tag name.
     *
     * @param name the tag name.
     * @return an immutable node list.
     */
    public NodeList getElementsByTagName(String name);

    /**
     * Returns the tag name of the node.
     *
     * @return the tag name.
     */
    public String getTagName();


}