package ir.aut.ceit.logic;

import ir.aut.ceit.logic.server_client_interaction.messageHandler.BaseMessage;

import java.net.Socket;
import java.net.SocketAddress;
import java.util.Queue;

/**
 * Created by Yasaman on 5/29/2017.
 */
public class NetworkHandler extends Thread {
    private TcpChannel mTcpChannel;
    private Queue<byte[]> mSendQueue;
    private Queue<byte[]> mReceivedQueue;
    private ReceivedMessageConsumer mConsumerThread;

    public NetworkHandler(SocketAddress socketAddress, INetworkHandlerCallback iNetworkHandlerCallback) {
    }

    public NetworkHandler(Socket socket, INetworkHandlerCallback iNetworkHandlerCallback) {
    }

    /**
     * Add serialized bytes of message to the sendQueue.
     * completed by me
     */
    public void sendMessage(BaseMessage baseMessage) {
        mSendQueue.add(baseMessage.getSerialized());
    }

    /**
     * While channel is connected and stop is not called:
     * * if sendQueue is not empty, then poll a message and send it *
     * else if readChannel() is returning bytes, then add it to receivedQueue.
     */
    @Override
    public void run() {
        while (!mTcpChannel.isConnected() && !stopSelf()) {
            if (!(mSendQueue.isEmpty())) {
                /**
                 * then poll a message and send it *
                 */

            } else if (readChannel() != null) {
                mReceivedQueue.add(readChannel());
            }
        }
    }

    /**
     * Kill the thread and close the channel.
     * completed by me
     */
    public boolean stopSelf() {
        Thread.interrupted();
        mTcpChannel.closeChannel();
        return true;
    }

    /**
     * Try to read some bytes from the channel.
     */
    private byte[] readChannel() {
        return new byte[0];
    }

    private class ReceivedMessageConsumer extends Thread {
        /**
         * While channel is connected and stop is not called:
         * * if there exists message in receivedQueue, then create a message object
         * *from appropriate class based on message type byte and pass it through onMessageReceived
         * * else if receivedQueue is empty, then sleep 100 ms.
         */
        @Override
        public void run() {
            while (!mTcpChannel.isConnected() && !stopSelf()) {
                if (!(mReceivedQueue.isEmpty())) {
                    /**
                     then create a message object
                     * *from appropriate class based on message type byte and pass it through onMessageReceived
                     */
                } else if (mReceivedQueue.isEmpty()) {
                    mReceivedQueue.add(readChannel());
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public interface INetworkHandlerCallback {
        void onMessageReceived(BaseMessage baseMessage);

        void onSocketClosed();
    }
}
