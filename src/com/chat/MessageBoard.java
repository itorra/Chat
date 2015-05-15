package com.chat;

import java.util.*;


/**
 * Created by ido on 11/05/15.
 */
public class MessageBoard extends Thread implements StringConsumer, StringProducer {

//    private List<ClientDescriptor> connectedClients;
    private Vector<Message> msgQ;
    private Vector<StringConsumer> consumers;

    public MessageBoard() {

        msgQ = new Vector<Message>();
//        connectedClients = new LinkedList<ClientDescriptor>();
    }

    @Override
    public void run() {
        while (true) {
            if (msgQ.)

        }
    }

    @Override
    public void consume(String str) {
        msgQ.add(new Message(str));
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        consumers.add(sc);
    }

    @Override
    public void removeConsumer(StringConsumer sc) {

    }
}
