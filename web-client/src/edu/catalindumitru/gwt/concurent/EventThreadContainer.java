/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.concurent;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author colin
 */
public class EventThreadContainer extends JavaScriptObject {
    //protected EventThreadEntryPoint _entryPoint;

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected EventThreadContainer() {
        /*cannot be directly instantiated*/
    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public final native void initialize(EventThreadEntryPoint entryPoint) /*-{
        //this.@edu.catalindumitru.gwt.concurent.EventThreadContainer::setEventThread(Ledu/catalindumitru/gwt/concurent/EventThreadEntryPoint;)(entryPoint);

        this.onmessage = function(event) {
            //this.@edu.catalindumitru.gwt.concurent.EventThreadContainer::onMessage(Lcom/google/gwt/core/client/JavaScriptObject;)(event);
        }

        this.onerror = function(event) {
            //this.@edu.catalindumitru.gwt.concurent.EventThreadContainer::onError(Lcom/google/gwt/core/client/JavaScriptObject;)(event);
        }
    }-*/;

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected final void setEventThread(EventThreadEntryPoint entryPoint) {
        //this._entryPoint = entryPoint;
    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected final void onMessage(JavaScriptObject message) {
        /*if(this._entryPoint != null)
            this._entryPoint.onMessage(new Event(message));*/
    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected final void onError(JavaScriptObject message) {
        /*if(this._entryPoint != null)
            this._entryPoint.onError(new ErrorEvent(message));*/
    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public final static native EventThreadContainer instance()/*-{
        return self;
    }-*/;

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @SuppressWarnings("unused")
    private final native void postMessage(String message) /*-{
        postMessage(message);
    }-*/;

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @SuppressWarnings("unused")
    private final native void postMessage(JavaScriptObject message) /*-{
        postMessage(message);
    }-*/;

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public final void sendEvent(Event event) {
        /*TODO test if we can send actual object and not just String*/
        this.postMessage(event.getJavaScriptObject());
    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public final native void terminate() /*-{
        this.terminate();
    }-*/;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    /**
     * The external script to be loaded into the current Event Thread
     *
     * @param module The js file to be loaded into this module
     */
    public final native void loadModule(String module) /*-{
        importScript(module);
    }-*/;

}
