/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.concurent;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;

/**
 *
 * @author catalin.dumitru
 */
public abstract class EventThreadEntryPoint extends JavaScriptObject implements EntryPoint{
    EventThreadContainer _container;
    
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    /**
     * Entry point for the application. It is called automatically when the Thread has finished
     * loading. Should not be called manually.
     */
    public void onModuleLoad() {
        this._container = EventThreadContainer.instance();
        
        this.onLoad();
    }    
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * The function is called when the Thread is finished loading. This is where you do 
     * initialization stuff.
     */
    public abstract void onLoad();
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * Call to end the thread. Subsequent calls to send Event won't have any effect.
     */
    public void terminate() {
        this._container.terminate();
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * Function is called when an event from the main thread is send to this thread.
     * @param event The event which has been sent.
     */
    public abstract void onMessage(Event event);
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * Function is called when an error has occurred in the main Thread.
     * @param event The error which has occurred.
     */
    public abstract void onError(ErrorEvent event);
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public void sendEvent(Event event) {
        if(this._container != null)
            this._container.sendEvent(event);
    }
    
    
}
