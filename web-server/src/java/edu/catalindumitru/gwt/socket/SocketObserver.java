/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.socket;

/**
 *
 * @author colin
 */
public interface SocketObserver {
    void onOpen(String message);
    void onMessage(SocketEvent event);
    void onClose(SocketCloseEvent event);
    void onError(String error);
    
}
