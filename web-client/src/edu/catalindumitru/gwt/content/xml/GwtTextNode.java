package edu.catalindumitru.gwt.content.xml;

import edu.catalindumitru.bee.content.xml.TextNode;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/12/11
 * Time: 12:21 PM
 */
public class GwtTextNode extends GwtNode implements TextNode {

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected GwtTextNode() {

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static TextNode wrap(GwtTextNode node) {
        return node;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the text data stored in the text node.
     *
     * @return the text data.
     */
    @Override
    public final native String getData() /*-{
        return this.data;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the text data length stored in the text node.
     *
     * @return the text data length.
     */
    @Override
    public final native int getLength() /*-{
        return this.length;
    }-*/;
}
