/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.concurent.client;

import com.google.gwt.core.client.JavaScriptObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author catalin.dumitru
 * 
 * A basic implementation of Web Workers, allowing for cocurent programming. Because of the nature
 * of web worker, threads cannot share object and communicate only through String messages and
 * Json objects. The EventThread class communicates only through custom message clas, which provides
 * basic object serialisation through JSON.
 */
public abstract class EventThread extends JavaScriptObject{
    List<EventThreadObserver> _observers;
    String _tag;
    
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected EventThread() {
        /*private constructor for javascript objects*/
        this.initialize("undefined");                
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * Initializes the EventThread. All operations performed on the EventThread require a
     * initialization.
     * @param tag The unique tag given to the worker. This tag is then passed with the Event
     * to any event observers
     */
    public void initialize(String tag) {
        this._observers = new ArrayList<EventThreadObserver>();
        this._tag = tag;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected native void initializeHandlers() /*-{
          this.onmessage = function(event){
             @edu.catalindumitru.concurent.EventThread::onMessage(
             Ledu/catalindumitru/concurent/Event;)(event);
          }
          
          this.onerror = function(event){
             @edu.catalindumitru.concurent.EventThread::onError(
             Ledu/catalindumitru/concurent/Event;)(event);
          }
    -}*/;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * Adds an observer to listen for messages from the event thread.
     * @param observer the observer to be added to the list
     */
    public void addObserver(EventThreadObserver observer) {
        this._observers.add(observer);
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected void onEvent(JavaScriptObject message){
        Event event = new Event(message);
        
        event.setEventTag(_tag);
        
        for(EventThreadObserver observer : this._observers) {
            observer.onEvent(event);
        }
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected void onError(JavaScriptObject message) {
        Event event = new Event(message);
        
         event.setEventTag(_tag);
        
        for(EventThreadObserver observer : this._observers) {
            observer.onError(event);
        }
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public void sendEvent(Event event) {
        this.sendMessage(event.getJavaScriptObject());
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected native void sendMessage(JavaScriptObject message) /*-{
        this.postMessage(message);
    }-*/;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    
}
