/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.socket;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 *
 * @author colin
 */
public class SocketEvent extends JavaScriptObject{
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected SocketEvent () {
    }    
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public native String data() /*-{
        return this.data;
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public native String origin() /*-{
        return this.origin;
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public native String lastEventId() /*-{
        return this.lastEventId;
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public native JsArray<MessagePort> ports() /*-{
             return this.ports || [];
    }-*/;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------


}