/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.concurent;

/**
 * Used to listen for events from an event thread.
 * @author catalin.dumitru
 */
public interface EventThreadObserver {
    public void onEvent(Event event);
    public void onError(Event event);
}
