/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.steel.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.jjs.impl.GwtAstBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
//import edu.catalindumitru.array.TypedArray;
import edu.catalindumitru.gwt.array.TypedArray;
import edu.catalindumitru.gwt.socket.Socket;
import edu.catalindumitru.gwt.socket.SocketCloseEvent;
import edu.catalindumitru.gwt.socket.SocketEvent;
import edu.catalindumitru.gwt.socket.SocketObserver;
import edu.catalindumitru.gwt.type.UByte;
import edu.catalindumitru.gwt.type.UInteger;

/**
 * Main entry point.
 *
 * @author colin
 */
public class GameCore implements EntryPoint, SocketObserver {

    /** 
     * Creates a new instance of MainEntryPoint
     */
    public GameCore() {
       
    }

    /** 
     * The entry point method, called automatically by loading a module
     * that declares an implementing class as an entry-point
     */
    @Override
    public void onModuleLoad() {
        int aaa = 0;
        
        Socket socket = Socket.connect("http://www.google.com");
        
        socket.addObserver(this);
        
        TypedArray<Integer> a = TypedArray.createIntArray(0);
        TypedArray<Integer> b = TypedArray.create(Integer.class.getName(), 0);
    }

    @Override
    public void onOpen(String message) {
        GWT.log(message);
    }

    @Override
    public void onMessage(SocketEvent event) {
        GWT.log(event.data());
    }

    @Override
    public void onClose(SocketCloseEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onError(String error) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
