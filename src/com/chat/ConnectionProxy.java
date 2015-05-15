package com.chat;

import java.io.*;
import java.net.*;

/**
 * Created by ido on 10/05/15.
 */
public class ConnectionProxy extends Thread implements StringConsumer, StringProducer {

    public static String serverName = "http://localhost/";
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
    }

    public ConnectionProxy(StringConsumer in) {
        consumer = in;
        try {
            socket = new Socket(serverName,serverPort);
        } catch (IOException e) { e.printStackTrace(); }
        initStreams();

    }

    public void initStreams() {
        try  {
            is = socket.getInputStream();
            dis = new DataInputStream(is);
            os = socket.getOutputStream();
            dos = new DataOutputStream(os);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(is!=null)
            {
                try{is.close();}catch(IOException e){e.printStackTrace();}
            }
            if(os!=null)
            {
                try{os.close();}catch(IOException e){e.printStackTrace();}
            }
            if(dis!=null)
            {
                try{dis.close();}catch(IOException e){e.printStackTrace();}
            }
            if(dos!=null)
            {
                try{dos.close();}catch(IOException e){e.printStackTrace();}
            }
            if(socket!=null)
            {
                try{socket.close();}catch(IOException e){e.printStackTrace();}
            }
        } //finally
    }

    @Override
    public void run() {
        while (true){
            try {
                String msg = dis.readUTF();
                consumer.consume(msg);
            } catch (IOException e) {
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
