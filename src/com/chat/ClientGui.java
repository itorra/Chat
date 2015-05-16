package com.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*I am a test comment*/

/**
 * Created by Ido and Dassi on 08/05/15.
 */
public class ClientGui implements StringConsumer, ActionListener{

    @Override
    public void consume(String str) {
        // I got a string from the proxy - now I need to show it on main chat window
    }

    private JTextField nickName;
    private JTextField userInput;
    private JTextArea chatOutput;
    private JButton connect;
    private JButton send;

    private JLabel nickNameLabel;
    //private JList userSelectorList;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel eastPanel;
    private JFrame mainFrame;


    private final int nickNameLength = 10;
    private final int userIOWidthLength = 50;
    private final int userIOHeightLength = 100;
    private final String welcomeMsg = "Connection Succeeded - Welcome ";
    private final String connectLabel = "Connect";
    private final String sendLabel = "Send!";
    private final String windowLabel = "Chat Window";
    private final String nickNameLabelStr = "Nickname:";


    private ConnectionProxy proxy;
    private boolean isConnected = false;


    public ClientGui() {
        nickName = new JTextField(nickNameLength);
        userInput = new JTextField(userIOWidthLength);
        chatOutput = new JTextArea(welcomeMsg,userIOHeightLength,userIOWidthLength);
        connect = new JButton(connectLabel);
        send = new JButton(sendLabel);
        mainFrame = new JFrame(windowLabel);
        //userSelectorList = new JList();

        nickNameLabel = new JLabel(nickNameLabelStr);

        eastPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();
    }

    public void initGui() {
        eastPanel.setLayout(new FlowLayout());
        northPanel.setLayout(new FlowLayout());
        southPanel.setLayout(new FlowLayout());
        userInput.setEditable(false);
        send.setEnabled(false);
        northPanel.add(nickNameLabel);
        northPanel.add(nickName);
        northPanel.add(connect);

        southPanel.add(userInput);
        southPanel.add(send);

        //eastPanel.add(userSelectorList);
        chatOutput.setHighlighter(null);
        chatOutput.setEditable(false);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(BorderLayout.NORTH, northPanel);
        mainFrame.add(BorderLayout.SOUTH,southPanel);
        mainFrame.add(BorderLayout.CENTER,chatOutput);
        //mainFrame.add(BorderLayout.WEST,userSelectorList);
        mainFrame.setSize(1000,800);
    }

    public void start() {
        initGui();
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == connect) {
            if(isConnected == false){
                String name = nickName.getText();
                nickName.setEditable(false);
                proxy = new ConnectionProxy(this);
                proxy.start();
                isConnected = true;
            }
            else if(isConnected == true){
                chatOutput.append("You are already connected...");
            }
        }
        else if(evt.getSource() == send){
            String msg = userInput.getText();
        }
    }

    public static void main(String[] args) {
        ClientGui gui = new ClientGui();
        gui.start();
    }
}

// TODO: make text area ScrollPage
// I want ot know When I connected If I'm welcomed or not
//so server will have a flag if I am aloowed to get data
// This is just the connection -first Freeze bottom layer and when welcomed I'll be able to chat