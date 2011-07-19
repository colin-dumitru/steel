package edu.catalindumitru.bee.network.socket;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/12/11
 * Time: 8:51 AM
 */
public interface Socket {
    public void connect(String url) throws NetworkException;
    public void send(String message) throws NetworkException;

    public void addObserver(SocketObserver observer);
    public void removeObserver(SocketObserver observer);

    public String getHostUrl() throws NetworkException;
    public String getHostExtensions() throws NetworkException;
    public int getBufferedAmmount() throws NetworkException;
    public String getHostProtocol() throws NetworkException;
}
