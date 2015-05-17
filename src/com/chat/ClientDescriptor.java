package com.chat;

/**
 * Created by ido on 11/05/15.
 */
public class ClientDescriptor implements StringConsumer, StringProducer{

    private String nickName = null;
    private StringConsumer invoker = null;
    private boolean justConnected = true;


    public ClientDescriptor() {
        System.out.println("New User Descriptor Created");
    }

    @Override
    public void consume(String str) {
        if (str == "#REMOVE") {
            MessageBoard mb = (MessageBoard) invoker;
            mb.removeConsumer(this);
            invoker.consume(nickName + " has left the chat");
        }

        invoker.consume( justConnected? newClientHandler(str):existingClientHandler(str) );
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        invoker=sc;
    }

    @Override
    public void removeConsumer(StringConsumer sc) {

    }

    public String existingClientHandler(String msg){

        return nickName + ":  " + msg ;
    }

    public String newClientHandler(String nn) {
        justConnected = false;
        nickName = nn;
        return nn + " has joined the Chat";
    }

}
