/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.socket;

/**
 * @author colin
 */
public interface NativeSocketObserver {
    void onOpen();

    void onMessage(SocketEvent event);

    void onClose(CloseEvent eventNative);

    void onError(String error);

}
