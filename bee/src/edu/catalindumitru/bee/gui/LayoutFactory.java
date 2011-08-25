package edu.catalindumitru.bee.gui;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/16/11
 * Time: 8:56 AM
 */
public class LayoutFactory {

    /*layouts are sorted by their name*/
    protected Map<String, Layout.LayoutCreator> layouts = new TreeMap<String, Layout.LayoutCreator>();

    /*unique instance*/
    protected static LayoutFactory instance = new LayoutFactory();

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected LayoutFactory() {

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static LayoutFactory instance() {
        return instance;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void addLayout(Layout.LayoutCreator layout) {
        this.layouts.put(layout.getName().toLowerCase(), layout);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Layout createLayout(String name) {
        Layout.LayoutCreator ret = this.layouts.get(name.toLowerCase());

        if (ret == null)
            return null;
        else
            return ret.create();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

}
