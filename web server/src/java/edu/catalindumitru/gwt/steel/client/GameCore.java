/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.steel.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import edu.catalindumitru.concurent.client.EventThread;

/**
 * Main entry point.
 *
 * @author colin
 */
public class GameCore implements EntryPoint {

    /** 
     * Creates a new instance of MainEntryPoint
     */
    public GameCore() {
    }

    /** 
     * The entry point method, called automatically by loading a module
     * that declares an implementing class as an entry-point
     */
    public void onModuleLoad() {
        final Label label = new Label("Hello, GWT!!!");
        final Button button = new Button("Click me!");
        
        button.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                label.setVisible(!label.isVisible());
            }
            
            EventThread e;
        });
        
        RootPanel.get().add(button);
        RootPanel.get().add(label);
    }
}
