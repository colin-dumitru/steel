package edu.catalindumitru.bee.gui;

import edu.catalindumitru.bee.content.xml.Element;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/13/11
 * Time: 8:47 AM
 */
public interface WidgetBuilder {
    /**
     * Creates a new widget from the root element.
     *
     * @param root the root element from the xml we are building.
     * @return the built widget.
     */
    public Widget build(Element root);

    /**
     * Returns the root widget this builder can handle.
     *
     * @return the name of the root widget this builder can handle.
     */
    public String widgetLabel();
}
