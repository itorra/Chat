package com.chat;

/**
 * Created by ido on 11/05/15.
 */
public class ClientDescriptor implements StringConsumer{

    private String nickName = null;
    private ConnectionProxy userProxy;
    private boolean isWelcomed = false;

    public ClientDescriptor() {

        userProxy = new ConnectionProxy(this);

    }

    @Override
    public void consume(String str) {
        // who do i send the data?
    }
}
