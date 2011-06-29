package edu.catalindumitru.gwt.socket;


/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 6/29/11
 * Time: 5:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class NetworkException extends Exception {
    public NetworkException() {
        super("A network exception has occured");
    }

    public NetworkException(String exception) {
        super("Network exception : " + exception);
    }

}
