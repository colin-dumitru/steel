/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.concurent.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

/**
 * A JavaScriptObject object wrapper which supports error event serialization. The resulting serialized 
 * JSON object will be passe from and to an Event Thread in case of an error. It is a simpler
 * version of the Event object which only has a simple String message in stead of a list of params
 * @author catalin.dumitru
 */
public class ErrorEvent extends JSONObject{
    public final static String K_EVENT = "event";
    public final static String K_MESSAGE = "params";
    public final static String K_TAG = "tag";
    
    public final static int E_UNDEFINED = -1;
    
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * Creates an empty event.
     */
    public ErrorEvent() {
        this.setEventType(E_UNDEFINED);                
        this.setEventMessage("undefined");
        this.setEventTag("undefined");
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * Creates an empty ErrorEvent based on the params.
     * @param type The type of the Error.
     * @param message The message of the Error.
     * @param tag The tag of the Thread from which the error has been emitted.
     */
    public ErrorEvent(int type, String message, String tag) {
        this.setEventType(type);                
        this.setEventMessage(message);
        this.setEventTag(tag); 
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * Created an Error Event
     * @param object 
     */
    public ErrorEvent(JavaScriptObject object) {
        super(object);
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * @param event the code of the event - it's mandatory for better performance when parsing
     * the event by an event observer     
     */
    public void setEventType(int event) {
        this.put(K_EVENT, new JSONNumber(event));
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * return the event type, NullPointer exception is returned if not defined
     */
    public int getEventType() {        
        return (int)this.get(K_EVENT).isNumber().doubleValue();
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * @param params an array representing the rest of the params for the event (for example for a
     * entity movement, the params might represent the location where it moved);
     */
    public final void setEventMessage(String message) {
          this.put(K_MESSAGE, new JSONString(message));   
          
    };
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * The function return the error message sent from the thread.
     * @return  the error message
     */
    public String getEventMessage() {  
        return this.get(K_MESSAGE).isString().stringValue();                
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * The tag is used to differentiate the Worker-Subject from which the event was emitted, for
     * Worker-Observers which listen to multiple subjects
     * @param tag the tag of the subject from which the event was emitted
     */
    public final void setEventTag(String tag) {
          this.put(K_TAG, new JSONString(tag));   
          
    };
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * @param event returns the event type associated to this event
     */
    public String getEventString() {                
        return this.get(K_TAG).isString().stringValue();        
        
    }
    
    
}
