package edu.catalindumitru.gwt.content;

import edu.catalindumitru.bee.content.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/5/11
 * Time: 1:25 PM
 */
public interface ResourceConverter {
    /**
     * Returns which type this converter supports.
     *
     * @return type of content which this converter can load (eg extension).
     */
    public String typeHandled();

    /**
     * Loads the specified content.
     *
     * @param resource which content to load.
     */
    public void loadResource(Resource resource);

    /**
     * Sets the content converter observer which will be called whenever a content completes loading.
     *
     * @param observer the content converter observer
     */
    public void setObserver(ResourceConverterObserver observer);
}
