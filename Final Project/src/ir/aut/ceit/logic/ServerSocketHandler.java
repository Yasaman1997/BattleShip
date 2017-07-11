package ir.aut.ceit.logic;

import ir.aut.ceit.logic.server_client_interaction.messageHandler.BaseMessage;

/**
 * Created by Yasaman  on 5/29/2017.
 */
public class ServerSocketHandler extends Thread implements NetworkHandler.INetworkHandlerCallback {

    int port;
    NetworkHandler.INetworkHandlerCallback iNetworkHandlerCallback;
    IServerSocketHandlerCallback iServerSocketHandlerCallback;
    TcpChannel tcpChannel;

    public ServerSocketHandler(int port, NetworkHandler.INetworkHandlerCallback iNetworkHandlerCallback, IServerSocketHandlerCallback iServerSocketHandlerCallback) {
        this.port = port;
        this.iNetworkHandlerCallback = iNetworkHandlerCallback;
        this.iServerSocketHandlerCallback = iServerSocketHandlerCallback;
    }

    /**
     * While server socket is connected and stop is not called: *
     * if a connection receives, then create a network handler and pass it through onNewConnectionReceived *
     * else sleep for 100 ms.
     */
    @Override
    public void run() {
        while (tcpChannel.isConnected() && !stopSelf()) {

            /** if a connection receives, then create a network handler
             *
             */
            NetworkHandler networkHandler = new NetworkHandler(tcpChannel.getmSocket(), iNetworkHandlerCallback);

            /**
             * and pass it through onNewConnectionReceived *
             */
            //  onNewConnectionReceived(networkHandler);
        }
        /** else
         *
         */
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Kill the thread and close the server socket.
     */
    public boolean stopSelf() {
        Thread.interrupted();
        /**
         *  close the server socket.
         */

        return true;
    }

    /**
     * completed by me , these methods should be overridden
     *
     * @param baseMessage
     */
    @Override
    public void onMessageReceived(BaseMessage baseMessage) {

    }

    /**
     * completed by me , these methods should be overridden
     */
    @Override
    public void onSocketClosed() {

    }

    public interface IServerSocketHandlerCallback {
        void onNewConnectionReceived(NetworkHandler networkHandler);
    }
}