/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.socket;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author colin
 */
public class NativeSocket extends JavaScriptObject {
    // ready state
    public final static short CONNECTING = 0;
    public final static short OPEN = 1;
    public final static short CLOSING = 2;
    public final static short CLOSED = 3;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected NativeSocket() {
        /*nada*/
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static native NativeSocket connect(String url) /*-{
        return new WebSocket(url);
    }-*/;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static native NativeSocket connect(String url, String params) /*-{
        return new WebSocket(url, params);
    }-*/;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static native NativeSocket connect(String url, String[] params) /*-{
        return new WebSocket(url, params);
    }-*/;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public final native void init(NativeSocketObserver observer)/*-{
        this.onopen = $entry(function() {
            observer.@edu.catalindumitru.gwt.socket.NativeSocketObserver::onOpen()();
        });

        this.onerror = $entry(function(event) {
            observer.@edu.catalindumitru.gwt.socket.NativeSocketObserver::onError(Ljava/lang/String;)(event);
        });

        this.onclose = $entry(function(event) {
            observer.@edu.catalindumitru.gwt.socket.NativeSocketObserver::onClose(Ledu/catalindumitru/gwt/socket/CloseEvent;)(event);
        });

        this.onmessage = $entry(function(event) {
            observer.@edu.catalindumitru.gwt.socket.NativeSocketObserver::onMessage(Ledu/catalindumitru/gwt/socket/SocketEvent;)(event);
        });

    }-*/;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public final native void send(String data) /*-{
        this.send(data);
    }-*/;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public final native short readyState() /*-{
        return this.readyState;
    }-*/;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public final native int bufferedAmmount() /*-{
        return this.bufferedAmount;
    }-*/;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public final native String extensions() /*-{
        return this.extensions;
    }-*/;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public final native String protocol() /*-{
        return this.protocol;
    }-*/;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public final native String url() /*-{
        return this.url;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

}
