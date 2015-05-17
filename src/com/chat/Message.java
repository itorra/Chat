package com.chat;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ido on 13/05/15.
 */
public class Message {

    private String msg;

    public Message(String msg_src) {
        this.msg = msg_src;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        msg = sdf.format(new Date()) + "   " + msg;
    }

    public String getMsg() {
        return msg;
    }
}
