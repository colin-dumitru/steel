/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.concurent.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;


/**
 *
 * @author catalin.dumitru
 * 
 * The event which is passed to and from an EventThread. It supports basic serialization of
 * JavaScript objects.
 */
public class Event extends JSONObject {
    public final static String K_EVENT = "event";
    public final static String K_PARAMS = "params";
    public final static String K_TAG = "tag";
    
    public final static int E_UNDEFINED = -1;
    
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * Creates an empty event.
     */
    public Event() {
        this.setEventType(E_UNDEFINED);                
        this.setEventParams((JsArray<JavaScriptObject>)JsArray.createArray());
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * Creates an event based on the parameters of the function.
     */
    public Event(int type, JsArray<JavaScriptObject> params, String tag) {
        this.setEventType(type);                
        this.setEventParams(params);
        this.setEventTag(tag); 
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public Event(JavaScriptObject object) {
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
    public final void setEventParams(JsArray<JavaScriptObject> params) {
          this.put(K_PARAMS, new JSONArray(params));   
          
    };
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * @param event returns the event type associated to this event
     */
    public JsArray<JavaScriptObject> getEventParams() {                
        return this.get(K_PARAMS).isArray().getJavaScriptObject().cast();        
        
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
