package ir.aut.ceit.logic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

public class TcpChannel {

    private Socket mSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;

    public TcpChannel(SocketAddress socketAddress, int timeout){

        mSocket = new Socket();
        try {
            mSocket.connect(socketAddress, timeout);
            mOutputStream = mSocket.getOutputStream();
            mInputStream = mSocket.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public TcpChannel(Socket socket, int timeout){
        mSocket = socket;
        try {
            mSocket.setSoTimeout(timeout);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            mOutputStream = mSocket.getOutputStream();
            mInputStream = mSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Try to read specific count from input stream.
     */
    public byte[] read(final int count){

        byte[] data = new byte[count];
        try {
            mInputStream.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Write bytes on output stream.
     */
    public void write(byte[] data){

        try {
            mOutputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check socket’s connectivity.
     */
    public boolean isConnected(){

        return mSocket.isConnected();
    }

    /**
     * Try to close socket and input-output streams.
     */
    public void closeChannel(){
        try {
            mSocket.close();
            mInputStream.close();
            mOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Socket getmSocket() {
        return mSocket;
    }
}