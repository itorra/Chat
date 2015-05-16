package com.chat;

import java.io.*;
import java.net.*;

/**
 * Created by ido on 10/05/15.
 */
public class ConnectionProxy extends Thread implements StringConsumer, StringProducer {

    public static String serverName = "localhost";
    public static int serverPort = 3000;

    private Socket socket;

    private InputStream is = null;
    private DataInputStream dis = null;
    private OutputStream os = null;
    private DataOutputStream dos = null;

    private StringConsumer consumer;

    public ConnectionProxy(Socket s) {
        socket = s;
        initStreams();
        System.out.println("Connection Proxy Created");
    }

    public ConnectionProxy(StringConsumer in) {
        consumer = in;
        try {
            socket = new Socket(serverName,serverPort);
            initStreams();
            System.out.println("Connection Proxy Created");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void initStreams() {
        try  {
            is = socket.getInputStream();
            dis = new DataInputStream(is);
            os = socket.getOutputStream();
            dos = new DataOutputStream(os);
        }
        catch(IOException eio)
        {
            eio.printStackTrace();
            if(is!=null) {
                try{is.close();}catch(IOException e){e.printStackTrace();}
            }
            if(os!=null) {
                try{os.close();}catch(IOException e){e.printStackTrace();}
            }
            if(dis!=null) {
                try{dis.close();}catch(IOException e){e.printStackTrace();}
            }
            if(dos!=null) {
                try{dos.close();}catch(IOException e){e.printStackTrace();}
            }
            if(socket!=null) {
                try{socket.close();}catch(IOException e){e.printStackTrace();}
            }
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                String msg = dis.readUTF();
                consumer.consume(msg);
                System.out.println(msg);
            } catch (IOException e) {
                System.out.println("Error....");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void consume(String str) {
        try {
            dos.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        consumer = sc;
    }

    @Override
    public void removeConsumer(StringConsumer sc) {
    }
}
