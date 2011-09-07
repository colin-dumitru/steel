package edu.catalindumitru.bee.content;

import edu.catalindumitru.bee.xscript.XScriptHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/5/11
 * Time: 11:56 AM
 */
public class Resource {
    public static enum STATUS {IDLE, LOADING, COMPLETED, ERROR}

    /*content params*/
    protected STATUS status;
    protected NativeResource resource;
    protected String type;
    protected String name;
    /*it is set in case an error has occurred while loading the content*/
    protected String errorMessage;

    /*called when the resources state has changed*/
    protected List<ResourceObserver> observers;

    /*parent content handler*/
    protected static ResourceProvider provider;


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new link to a content (ImageResource, Sound etc). The content is initially set to null if the specified content
     * has not been loaded, and tells the content manager to load the content. If it has been previously loaded, then the
     * content is instantly set.
     *
     * @param type type of content (extension).
     * @param name full path of the content. This path must be unique.
     */
    public Resource(String type, String name) {
        this.type = type;
        this.name = name;

        this.status = STATUS.IDLE;
        /*content is initially null*/
        this.resource = null;

        this.observers = new LinkedList<ResourceObserver>();

        /*begin loading the resource*/
        this.preLoad();

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public STATUS getStatus() {
        return status;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setStatus(STATUS status) {
        if (status != this.status) {
            this.status = status;

            /*notify observer that the state has changed*/
            for (ResourceObserver observer : this.observers)
                observer.stateChanged(this);
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public NativeResource getResource() {
        return resource;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setResource(NativeResource resource) {
        this.resource = resource;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public String getType() {
        return type;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public String getName() {
        return name;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public static ResourceProvider getProvider() {
        return provider;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public static void initialise(ResourceProvider provider) {
        Resource.provider = provider;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * The resource is initially just a symbolic link, the actual resource is set to null and is not loaded. Only after
     * this method is called, the resource begins loading.
     */
    protected void preLoad() {
        if (Resource.provider == null)
            throw new NullPointerException("Resource <" + this.name +
                    "> cannot be loaded because no Resource Provider is set.");

        /*begin resource loading*/
        Resource.provider.loadResource(this, false);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public String getErrorMessage() {
        return errorMessage;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean equals(Object resource) {
        Resource other = null;

        try {
            other = (Resource) resource;
        } catch (Exception ex) {
            return false;
        }


        if (this.name == other.name)
            return true;

        return false;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void addResourceObserver(ResourceObserver observer) {
        this.observers.add(observer);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removeResourceObserver(ResourceObserver observer) {
        this.observers.remove(observer);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void copyFrom(Resource other) {
        this.status = other.status;
        this.resource = other.resource;
        this.errorMessage = other.errorMessage;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

}
