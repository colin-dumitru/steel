package edu.catalindumitru.bee.network.socket;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 6/29/11
 * Time: 5:34 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SocketObserver {
    public void onNetworkMessage(NetworkMessage message);
}
