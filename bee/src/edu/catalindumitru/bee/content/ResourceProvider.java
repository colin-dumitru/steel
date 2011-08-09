package edu.catalindumitru.bee.content;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/5/11
 * Time: 12:32 PM
 */
public interface ResourceProvider {

    /**
     * Loads the specified content. If the content has been previously loaded that it will be set immediately. Otherwise
     * the content will be loaded asynchronously. If immediate is set to true, then the content will begin loading
     * immediately and won't wait until the next update cycle.
     *
     * @param resource  which content to load.
     * @param immediate if the content should start loading right now
     */
    public void loadResource(Resource resource, boolean immediate);

    /**
     * Releases the content from memory. If immediate is set too true, then the content will be released immediately.
     * If not, then it will be added to a cache, and kept there until the maximum number of cached items are reached.
     *
     * @param resource  which content to release
     * @param immediate if the content should be released immediately. Use this if you know it will not be used anytime
     *                  soon.
     */
    public void releaseResource(Resource resource, boolean immediate);

    /**
     * Returns the number of resources which currently are loading.
     *
     * @return the number of resources which are currently loading.
     */
    public int resourcesLoading();

    /**
     * Updates the loading cycle.
     */
    public void update();

    /**
     * Updates the garbage collection, to remove old cached resources. This function should be called a lot
     */
    public void updateGC();

    /**
     * Sets the maximum elements which can be cached before garbace collector removes them.
     *
     * @param amount the number of cached elements.
     */
    public void setMaximumCachedElements(int amount);
}
