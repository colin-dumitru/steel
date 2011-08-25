package edu.catalindumitru.gwt.content;

import edu.catalindumitru.bee.content.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/9/11
 * Time: 2:13 PM
 */
public class PngConverter implements ResourceConverter {
    protected ResourceConverterObserver observer;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns which type this converter supports.
     *
     * @return type of content which this converter can load (eg extension).
     */
    @Override
    public String typeHandled() {
        return "png";  //To change body of implemented methods use File | Settings | File Templates.
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Loads the specified content.
     *
     * @param resource which content to load.
     */
    @Override
    public native void loadResource(Resource resource) /*-{
        var request = new Image();
        //I have had some problems in a previous project where the function variables where not set correctly
        //for multiple function calls
        var cache = resource;
        var that = this;

        request.src = resource.@edu.catalindumitru.bee.content.Resource::getName()();
        request.onload = function() {

            //use this method to ensure that the object has defined the methods from GwtImage
            that.@edu.catalindumitru.gwt.content.PngConverter::setImageResource(Ledu/catalindumitru/gwt/content/GwtImageResource;Ledu/catalindumitru/bee/content/Resource;)(
                    request, resource
            )
            that.@edu.catalindumitru.gwt.content.PngConverter::updateStatus(Ledu/catalindumitru/bee/content/Resource;)(
                    cache
            );
            //notify the observer AFTER you 've set the resource dumbass
            cache.@edu.catalindumitru.bee.content.Resource::setStatus(Ledu/catalindumitru/bee/content/Resource$STATUS;)(
                    @edu.catalindumitru.bee.content.Resource.STATUS::COMPLETED
            );

        }

        request.onerror = function(from) {
            cache.@edu.catalindumitru.bee.content.Resource::setStatus(Ledu/catalindumitru/bee/content/Resource$STATUS;)(
                    @edu.catalindumitru.bee.content.Resource.STATUS::ERROR
            );
            cache.@edu.catalindumitru.bee.content.Resource::setErrorMessage(Ljava/lang/String;)("An error has occured" +
                    " while loading image : " + cache.@edu.catalindumitru.bee.content.Resource::getName()())
            that.@edu.catalindumitru.gwt.content.PngConverter::updateStatus(Ledu/catalindumitru/bee/content/Resource;)(
                    cache
            );

        }
    }-*/;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void setImageResource(GwtImageResource imageResource, Resource resource) {
        resource.setResource(imageResource);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the content converter observer which will be called whenever a content completes loading.
     *
     * @param observer the content converter observer
     */
    @Override
    public void setObserver(ResourceConverterObserver observer) {
        this.observer = observer;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void updateStatus(Resource resource) {
        if (this.observer != null) {
            switch (resource.getStatus()) {

                case IDLE:
                    /*nothing for idle*/
                    break;
                case LOADING:
                    this.observer.onStart(resource);
                    break;
                case COMPLETED:
                    this.observer.onComplete(resource);
                    break;
                case ERROR:
                    this.observer.onError(resource, resource.getErrorMessage());
                    break;
            }
        }
    }
}
