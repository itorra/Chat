package com.chat;

import java.io.IOException;
import java.net.*;
import java.util.Vector;

/**
 * Created by ido on 13/05/15.
 */
public class ServerApp extends Thread {

    private MessageBoard mb;
    private Vector<StringConsumer> consumers;

    private ServerSocket server = null;
    private Socket socket = null;
    private ConnectionProxy newClientProxy = null;
    private ClientDescriptor newClient = null;

    public ServerApp() {

        mb = new MessageBoard();
        try {
            server = new ServerSocket(3000,0);
        } catch (IOException e) {
            System.out.println("Sever Failed");
            e.printStackTrace(); }


    }

    @Override
    public void run() {
        mb.start();
        while (true) {
            try {
                socket = server.accept();
                newClientProxy = new ConnectionProxy(socket);
                newClient = new ClientDescriptor();

                mb.addConsumer(newClientProxy);
                /* Connect between client descriptor to Proxy */
                newClientProxy.addConsumer(newClient);
                newClient.addConsumer(mb);

                newClientProxy.start();

            } catch (IOException e) {
                System.out.println("Main Error");
                e.printStackTrace(); }
        }
    }

    public static void main(String[] args) {
        ServerApp app = new ServerApp();
        app.start();
    }

}
