package edu.catalindumitru.gwt.content;

import edu.catalindumitru.bee.content.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/5/11
 * Time: 1:27 PM
 */
public interface ResourceConverterObserver {
    /**
     * Called when loading of a content has begun.
     *
     * @param resource which content has begun loading.
     */
    public void onStart(Resource resource);

    /**
     * Called when a content has failed to load.
     *
     * @param resource which content has failed to load.
     */
    public void onError(Resource resource, String error);

    /**
     * Called when a content has finished loading.
     *
     * @param resource which content has finished loading.
     */
    public void onComplete(Resource resource);

}
