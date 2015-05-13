package com.chat;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ido on 11/05/15.
 */
public class MessageBoard {

    private List<ClientDescriptor> connectedClients;

    public MessageBoard() {

        connectedClients = new LinkedList<ClientDescriptor>();
    }
}
