package com.chat;

import java.util.*;


/**
 * Created by ido on 11/05/15.
 */
public class MessageBoard extends Thread implements StringConsumer, StringProducer {

    private Queue<Message> msgQ;
    private Vector<StringConsumer> consumers;

    public MessageBoard() {
        consumers = new Vector<StringConsumer>();
        msgQ = new LinkedList<Message>();
    }

    @Override
    public void run() {
        System.out.println("msg board started");
        while (true) {
//            System.out.println("Checking Q");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            if (msgQ.size() > 0) {
                System.out.println("MsgQQ isnot Empty");
                Message distribute = msgQ.remove();
                for (StringConsumer consumer: consumers) {
                    consumer.consume(distribute.getMsg());
                    System.out.println("Board distributing");
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
