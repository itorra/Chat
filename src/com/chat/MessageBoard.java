package com.chat;

import java.util.*;


/**
 * Created by ido on 11/05/15.
 */
public class MessageBoard extends Thread implements StringConsumer, StringProducer {

//    private List<ClientDescriptor> connectedClients;
    private Queue<Message> msgQ;
    private Vector<StringConsumer> consumers;

    public MessageBoard() {

        msgQ = new LinkedList<Message>();
//        connectedClients = new LinkedList<ClientDescriptor>();
        consumers = new Vector<StringConsumer>();
    }

    @Override
    public void run() {
        while (true) {
            if (!msgQ.isEmpty()) {
                Message distribute = msgQ.remove();
                for (StringConsumer consumer: consumers) {
                    consumer.consume(distribute.getMsg());
                }
            }

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
