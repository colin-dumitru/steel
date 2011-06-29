/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.socket;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author colin
 */
public class Socket extends JavaScriptObject{
    protected List<SocketObserver> _observers;
    
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected Socket() {
        /*nada*/
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native Socket connect(String url) /*-{
        return new WebSocket(url);
    -}*/;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native Socket connect(String url, String params) /*-{
        return new WebSocket(url, params);
    -}*/;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native Socket connect(String url, String[] params) /*-{
        return new WebSocket(url, params);
    -}*/;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected static native void initSocket(Socket socket)/*-{
             this.onmessage = @edu.catalindumitru.gwt.socket.Socket::omMessage;
             this.onopen = @edu.catalindumitru.gwt.socket.Socket::omOpen;
             this.onerror = @edu.catalindumitru.gwt.socket.Socket::omError;
             this.onclose = @edu.catalindumitru.gwt.socket.Socket::omClose ;
    }-*/;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public List<SocketObserver> observers() {
        /*Lazy initialisation*/
        if(this._observers == null) 
            return (this._observers = new LinkedList<SocketObserver>());
        
        return this._observers;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public void addObserver(SocketObserver observer) {
        this.observers().add(observer);
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public void removeObserver(SocketObserver observer) {
        this.observers().remove(observer);
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public native void send(String data) /*-{
             this.send(data);
    }-*/;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected void onOpen(String message) {
        for(SocketObserver observer : this.observers()) {
            observer.onOpen(message);
        }
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected void onMessage(SocketEvent event) {
        for(SocketObserver observer : this.observers()) {
            observer.onMessage(event);
        }
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected void onClose(SocketCloseEvent event) {
        for(SocketObserver observer : this.observers()) {
            observer.onClose(event);
        }
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected void onError(String message) {
        for(SocketObserver observer : this.observers()) {
            observer.onError(message);
        }
    }
    //----------------------------------------------------------------------------------------------
    
}
