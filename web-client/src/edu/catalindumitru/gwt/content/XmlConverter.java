package edu.catalindumitru.gwt.content;

import edu.catalindumitru.bee.content.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/5/11
 * Time: 2:46 PM
 */
public class XmlConverter implements ResourceConverter {
    protected ResourceConverterObserver observer;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public XmlConverter() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns which type this converter supports.
     *
     * @return type of content which this converter can load (eg extension).
     */
    @Override
    public String typeHandled() {
        return "xml";  //To change body of implemented methods use File | Settings | File Templates.
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
        var request = new XMLHttpRequest();
        //I have had some problems in a previous project where the function variables where not set correctly
        //for multiple function calls
        var cache = resource;
        var that = this;

        request.open("GET", resource.@edu.catalindumitru.bee.content.Resource::getName()(), true);
        request.onreadystatechange = function() {

            if (request.readyState == 3) { //loading
                cache.@edu.catalindumitru.bee.content.Resource::setStatus(Ledu/catalindumitru/bee/content/Resource$STATUS;)(
                        @edu.catalindumitru.bee.content.Resource.STATUS::LOADING
                );

            } else if (request.readyState == 4 && request.status == 200) {//complete

                cache.@edu.catalindumitru.bee.content.Resource::setResource(Ledu/catalindumitru/bee/content/NativeResource;)(
                        @edu.catalindumitru.gwt.content.GwtXmlResource::wrap(Ledu/catalindumitru/gwt/content/GwtXmlResource;)(
                                request.responseXML
                        )
                )

                cache.@edu.catalindumitru.bee.content.Resource::setStatus(Ledu/catalindumitru/bee/content/Resource$STATUS;)(
                        @edu.catalindumitru.bee.content.Resource.STATUS::COMPLETED
                );

            } else if (request.readyState == 4 && request.status != 200) {//complete but with error
                cache.@edu.catalindumitru.bee.content.Resource::setErrorMessage(Ljava/lang/String;)(
                        "error " + request.status + " - unable to load resource " +
                                cache.@edu.catalindumitru.bee.content.Resource::getName()()
                );

                cache.@edu.catalindumitru.bee.content.Resource::setStatus(Ledu/catalindumitru/bee/content/Resource$STATUS;)(
                        @edu.catalindumitru.bee.content.Resource.STATUS::ERROR
                );
            }

            that.@edu.catalindumitru.gwt.content.XmlConverter::updateStatus(Ledu/catalindumitru/bee/content/Resource;)(
                    cache
            );
        }

        request.send();
    }-*/;

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
}
