/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.steel.client;

import com.google.gwt.core.client.EntryPoint;
//import edu.catalindumitru.array.TypedArray;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import edu.catalindumitru.bee.network.socket.NetworkException;
import edu.catalindumitru.bee.network.socket.NetworkMessage;
import edu.catalindumitru.bee.network.socket.SocketObserver;
import edu.catalindumitru.gwt.socket.*;

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

        try {
            final JSSocket nativeSocket = new JSSocket();

            nativeSocket.addObserver(new SocketObserver() {
                public void onNetworkMessage(NetworkMessage message) {
                    GWT.log(message.get_message());

                    if (message.get_type() == NetworkMessage.EVENT_TYPE.T_OPEN)
                        try {
                            nativeSocket.send("Hello");
                        } catch (NetworkException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                }
            });

            nativeSocket.connect("ws://echo.websocket.org");

            Button b = new Button("Jump!", new ClickHandler() {
                public void onClick(ClickEvent event) {
                    Window.alert("How high?");
                }
            });

            // Add it to the root panel.
            RootPanel.get().add(b);

        } catch (NetworkException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


}
