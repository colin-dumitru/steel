package edu.catalindumitru.bee.content.xml;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/12/11
 * Time: 9:57 AM
 */

/**
 * An immutable collection of nodes.
 */
public interface NodeList {

    /**
     * Getter for the number of nodes.
     *
     * @return the number of nodes in the list.
     */
    int getLength();

    /**
     * Getter method for a node on the specified position.
     *
     * @param index the index of the node to retrieve.
     * @return the {@link Node} on the specified position.
     */
    Node item(int index);

}