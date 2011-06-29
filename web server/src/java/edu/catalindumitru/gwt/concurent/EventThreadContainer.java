/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.concurent.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 *
 * @author colin
 */
public class EventThreadContainer extends JavaScriptObject{
    protected EventThreadEntryPoint _entryPoint;
    
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected EventThreadContainer() {
        /*cannot be directly instantiated*/
    }    
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public native void initialize(EventThreadEntryPoint entryPoint) /*-{
        this.@edu.catalindumitru.concurent.EventThreadContainer::setEventThread
            (Ledu.catalindumitru.concurent.EventThreadEntryPoint;)(entryPoint);
        
        this.onmessage = function(event) {
          this.@edu.catalindumitru.concurent.EventThreadContainer::onMessage(
             Ledu.catalindumitru.concurent.Event;)(event);
        }
        
        this.onerror = function(event) {
          entryPoint.@edu.catalindumitru.concurent.EventThreadContainer::onError(
             Ledu.catalindumitru.concurent.ErrorEvent;)(event);
        }
    }-*/;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected void setEventThread(EventThreadEntryPoint entryPoint) {
        this._entryPoint = entryPoint;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected void onMessage(JavaScriptObject message) {
        if(this._entryPoint != null)
            this._entryPoint.onMessage(new Event(message));
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected void onError(JavaScriptObject message) {
        if(this._entryPoint != null)
            this._entryPoint.onError(new ErrorEvent(message));
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native EventThreadContainer instance()/*-{
            return $self;
    }-*/;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @SuppressWarnings("unused")
    private native void postMessage(String message) /*-{
            postMessage(message);
    }-*/;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @SuppressWarnings("unused")
    private native void postMessage(JavaScriptObject message) /*-{
            postMessage(message);
    }-*/;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public void sendEvent(Event event) {
        /*TODO test if we can send actual object and not just String*/
        this.postMessage(event.getJavaScriptObject());
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public native void terminate() /*-{
        this.terminate();
    }-*/;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * The external script to be loaded into the current Event Thread
     * @param module The js file to be loaded into this module
     */
    public native void loadModule(String module) /*-{
             importScript(module);
     }-*/ ;
    
}
