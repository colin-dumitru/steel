package edu.catalindumitru.gwt.content;

import edu.catalindumitru.bee.content.Resource;
import edu.catalindumitru.bee.content.ResourceProvider;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/5/11
 * Time: 12:56 PM
 */
public class GwtResourceProvider implements ResourceProvider, ResourceConverterObserver {
    protected static final int D_MAX_CACHE = 100;

    /*active resources which have been loaded*/
    protected Map<String, Resource> activeResources;

    /*queue representing cached content in the order in which they were released*/
    protected Queue<Resource> cachedQueue;
    /*map representing resourced which have been released sorted by their name*/
    protected Map<String, Resource> cachedMap;
    /*the maximum number of cached elements*/
    protected int maxCachedElements;

    /*objects which are currently loading*/
    protected Map<String, Resource> loadingResources;
    /*objects which on the next update cycle will be loaded*/
    protected Queue<Resource> queuedResources;

    /*different converters used to load resources, sorted by the type they can load*/
    protected Map<String, ResourceConverter> converters;


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public GwtResourceProvider() {
        this.activeResources = new TreeMap<String, Resource>();
        this.cachedQueue = new LinkedList<Resource>();
        this.cachedMap = new TreeMap<String, Resource>();
        this.loadingResources = new TreeMap<String, Resource>();
        this.converters = new TreeMap<String, ResourceConverter>();
        this.queuedResources = new LinkedList<Resource>();

        this.maxCachedElements = D_MAX_CACHE;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Loads the specified content. If the content has been previously loaded that it will be set immediately. Otherwise
     * the content will be loaded asynchronously.
     *
     * @param resource which content to load.
     */
    @Override
    public void loadResource(Resource resource, boolean immediate) {
        /*search for the content inside the already loaded resources*/
        Resource tmp = null;

        if ((tmp = this.activeResources.get(resource.getName())) != null) {
            resource.setResource(tmp.getResource());
            return; /*content found*/
        }

        /*search for the content inside previously released resources*/
        if ((tmp = this.cachedMap.get(resource.getName())) != null) {
            resource.setResource(tmp.getResource());
            /*remove the content from the cached queue*/
            this.cachedQueue.remove(tmp);
            return; /*content found*/
        }

        /*if the content has not been previously loaded, we load it now*/
        if (immediate)
            this.loadNewResource(resource);
        else
            this.queueResource(resource);

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Queues the content to be loaded on the next update cycle.
     *
     * @param resource which content to load.
     */
    protected void queueResource(Resource resource) {
        this.queuedResources.add(resource);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void loadNewResource(Resource resource) {
        /*search for a converter which can handle this type of content*/
        ResourceConverter converter;

        if ((converter = this.converters.get(resource.getType().toLowerCase())) == null) {
            resource.setStatus(Resource.STATUS.ERROR);
            resource.setErrorMessage("No suitable content loader has been found for this type.");
            return;
        }

        converter.loadResource(resource);

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Releases the content from memory. If immediate is set too true, then the content will be released immediately.
     * If not, then it will be added to a cache, and kept there until the maximum number of cached items are reached.
     *
     * @param resource  which content to release
     * @param immediate if the content should be released immediately. Use this if you know it will not be used anytime
     *                  soon.
     */
    @Override
    public void releaseResource(Resource resource, boolean immediate) {
        if (immediate) {
            /*remove the content from the active resources*/
            this.activeResources.remove(resource.getName());
            /*we also remove the content from the loading map in case the content has not finished loading*/
            this.loadingResources.remove(resource);
            /*also remove the content from the cache*/
            if (this.cachedMap.remove(resource.getName()) != null)
                this.cachedQueue.remove(resource);
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the number of resources which currently are loading.
     *
     * @return the number of resources which are currently loading.
     */
    @Override
    public int resourcesLoading() {
        return this.loadingResources.size();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Updates the loading cycle.
     */
    @Override
    public void update() {
        /*load current queued resources*/
        while (this.queuedResources.size() > 0) {
            this.loadNewResource(this.queuedResources.remove());
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Updates the garbage collection, to remove old cached resources. This function should be called a lot
     */
    @Override
    public void updateGC() {
        /*remove cached resources which overflow the current queue*/
        while (this.cachedQueue.size() > this.maxCachedElements) {
            this.cachedMap.remove(this.cachedQueue.poll());
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the maximum elements which can be cached before garbace collector removes them.
     *
     * @param amount the number of cached elements.
     */
    @Override
    public void setMaximumCachedElements(int amount) {
        this.maxCachedElements = amount;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Called when loading of a content has begun.
     *
     * @param resource which content has begun loading.
     */
    @Override
    public void onStart(Resource resource) {
        resource.setStatus(Resource.STATUS.LOADING);
        this.loadingResources.put(resource.getName(), resource);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Called when a content has failed to load.
     *
     * @param resource which content has failed to load.
     */
    @Override
    public void onError(Resource resource, String error) {
        resource.setStatus(Resource.STATUS.ERROR);
        this.loadingResources.remove(resource.getName());
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Called when a content has finished loading.
     *
     * @param resource which content has finished loading.
     */
    @Override
    public void onComplete(Resource resource) {
        resource.setStatus(Resource.STATUS.COMPLETED);

        /*We only add the content into active map if it exists into the currently loading resources. This prevents
        * errors when a content has been released, but it is still loading.*/
        if (this.loadingResources.remove(resource.getName()) != null)
            this.activeResources.put(resource.getName(), resource);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void addResourceConverter(ResourceConverter converter) {
        this.converters.put(converter.typeHandled().toLowerCase(), converter);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removeResourceConverter(ResourceConverter converter) {
        this.converters.remove(converter.typeHandled().toLowerCase());
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removeResourceConverter(String typeHandled) {
        this.converters.remove(typeHandled.toLowerCase());
    }

}
