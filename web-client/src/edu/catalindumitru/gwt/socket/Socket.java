package edu.catalindumitru.gwt.socket;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 6/29/11
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */
 import java.util.List;
import java.util.LinkedList;

public class Socket implements NativeSocketObserver {
    protected NativeSocket _socket;
    protected List<SocketObserver> _observers;
    protected String _url;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates an empty socket, not connected to anything. Any subsequent operations which require a valid connection
     * will throw a NetworkException.
     */
    public Socket() {
        this._observers = new LinkedList<SocketObserver>();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new socket and connects to the specified host. If the host is invalid, a NetworkException will be
     * thrown.
     * @param url The url of the host. This url must contain the full url, protocol and port.
     * @throws NetworkException Will be thrown if the url is invalid.
     */
    public Socket(String url) throws NetworkException{
        this();

        try{
            this._socket = NativeSocket.connect(url);
            this._socket.init(this);
            this._url = url;
        } catch (Exception ex) {
            this._url = "";

            throw new NetworkException("an error occured while trying to connect to host " + url  + " :" +
                ex.toString());
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the native JavaScript object which can be used for low level communicating.
     * @return
     */
    public NativeSocket getNativeSocket() {
        return this._socket;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sends a new message to the server.
     * @param message the string message which to send.
     * @throws NetworkException
     */
    public void send(String message) throws NetworkException{
        if(this._socket == null)
            throw new NetworkException("not connected to any host");

        this._socket.send(message);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Connects to the specified url. If the connection cannot be established, amd exception is thrown. The url must
     * contain the full address of the server, the protocol and the port to use.
     * @param url  The full url of the host
     * @throws NetworkException
     */
    public void connect(String url) throws NetworkException {
        try{
            this._socket = NativeSocket.connect(url);
            this._socket.init(this);
            this._url = url;
        } catch (Exception ex) {
            this._url = "";

            throw new NetworkException("an error occured while trying to connect to host " + url  + " :" +
                ex.toString());
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Adds a new observer which will be called when a new network event will occur.
     * @param observer The observer which will be called.
     */
    public void addObserver(SocketObserver observer) {
        this._observers.add(observer);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Removes an observer from the observer list.
     * @param observer The observer to remove.
     */
    public void removeObserver(SocketObserver observer) {
        this._observers.remove(observer);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void onOpen() {
        NetworkMessage event = new NetworkMessage("we have connected to host " +this._url ,
                NetworkMessage.EVENT_TYPE.T_OPEN, this);

        for(SocketObserver observer : this._observers)
            observer.onNetworkMessage(event);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void onMessage(SocketEvent eventNative) {
        NetworkMessage event = new NetworkMessage(eventNative.data(), NetworkMessage.EVENT_TYPE.T_MESSAGE, this);

        for(SocketObserver observer : this._observers)
            observer.onNetworkMessage(event);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void onClose(CloseEvent eventNative) {
        NetworkMessage event = new NetworkMessage(eventNative.reason(), NetworkMessage.EVENT_TYPE.T_CLOSE, this);

        for(SocketObserver observer : this._observers)
            observer.onNetworkMessage(event);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void onError(String error) {
        NetworkMessage event = new NetworkMessage(error, NetworkMessage.EVENT_TYPE.T_OPEN, this);

        for(SocketObserver observer : this._observers)
            observer.onNetworkMessage(event);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the host url at which the socket is connected.
     * @return The host url.
     * @throws NetworkException
     */
    public String getHostUrl() throws NetworkException{
        if(this._socket == null)
            throw new NetworkException("not connected to any hosts");

        return this._url;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public String getHostExtensions() throws NetworkException{
        if(this._socket == null)
            throw new NetworkException("not connected to any hosts");

        return this._socket.extensions();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public int getBufferedAmmount() throws NetworkException{
        if(this._socket == null)
            throw new NetworkException("not connected to any hosts");

        return this._socket.bufferedAmmount();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public String getHostProtocol() throws NetworkException{
        if(this._socket == null)
            throw new NetworkException("not connected to any hosts");

        return this._socket.protocol();
    }
}

