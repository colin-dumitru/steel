package edu.catalindumitru.gwt.socket;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 6/29/11
 * Time: 5:25 PM
  */

/**
 *  Container class for network events which pass beween the socket and every observer atached to the socket
 */
public class NetworkMessage {
    /**
     *  Can be of 5 types : on open (for when the connection has been established),
     * on close (when the connection has closed), on message (when a message has been received from the server),
     * on error (when a communication error has occured) and empty for when the message has not been initialized
     */
    public enum EVENT_TYPE {T_MESSAGE, T_ERROR, T_OPEN, T_CLOSE, T_EMPTY};

    protected String _message;
    protected EVENT_TYPE _type;
    protected Socket _from;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Constructor for the class to easelly initialze event params
     * @param _message body of the message
     * @param _type type of the message
     * @param from  the originator of the event. Useful for differentiating subject sockets for observers which listen
     * to multiple sockets
     */
    public NetworkMessage(String _message, EVENT_TYPE _type, Socket from) {
        this._message = _message;
        this._type = _type;
        this._from = from;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Default constructor for a message. The type is initialized to T_EMPTY:
     */
    public NetworkMessage() {
        this._type = NetworkMessage.EVENT_TYPE.T_EMPTY;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * return the full body of the message
     * @return the message body
     */
    public String get_message() {
        return _message;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------}

    /**
     * sets the full body of the message
     * @param _message message body
     */
    public void set_message(String _message) {
        this._message = _message;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * gets the type of the message. See {@link NetworkMessage.EVENT_TYPE}
     * @return the type of the message
     */
    public EVENT_TYPE get_type() {
        return _type;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * sets the type of the message. See {@link NetworkMessage.EVENT_TYPE}
     * @param _type
     */
    public void set_type(EVENT_TYPE _type) {
        this._type = _type;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * return the originator of the message
     * @return the socket from which the message has originated
     */
    public Socket get_from() {
        return _from;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void set_from(Socket _from) {
        this._from = _from;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
