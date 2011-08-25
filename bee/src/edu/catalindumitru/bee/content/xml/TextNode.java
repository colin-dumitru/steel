package edu.catalindumitru.bee.content.xml;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/12/11
 * Time: 10:40 AM
 */
public interface TextNode {
    /**
     * Returns the text data stored in the text node.
     *
     * @return the text data.
     */
    public String getData();

    /**
     * Returns the text data length stored in the text node.
     *
     * @return the text data length.
     */
    public int getLength();
}
