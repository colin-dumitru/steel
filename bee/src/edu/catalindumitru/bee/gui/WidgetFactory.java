package edu.catalindumitru.bee.gui;

import edu.catalindumitru.bee.content.xml.Element;
import edu.catalindumitru.bee.graphics.Render2DProvider;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/13/11
 * Time: 8:46 AM
 */
public class WidgetFactory {
    /*registered widgets*/
    protected Map<String, WidgetBuilder> builders = new TreeMap<String, WidgetBuilder>();


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected WidgetFactory() {

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void addBuilder(WidgetBuilder builder) {
        this.builders.put(builder.widgetLabel(), builder);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removeBuilder(WidgetBuilder builder) {
        this.builders.remove(builder.widgetLabel());
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removeBuilder(String widgetLabel) {
        this.builders.remove(widgetLabel);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Widget create(Element root, Render2DProvider provider) {
        WidgetBuilder builder = this.builders.get(root.getTagName().toLowerCase());

        if (builder == null)
            return null;
        else
            return builder.build(root, provider, this);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

}
