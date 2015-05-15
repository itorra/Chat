package com.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*I am a test comment*/

/**
 * Created by Ido and Dassi on 08/05/15.
 */
public class ClientGui implements StringConsumer {

    @Override
    public void consume(String str) {
        chatOutput.append(str);
    }

    private JTextField nickName;
    private JTextField userInput;
    private JTextArea chatOutput;
    private JButton connect;
    private JButton send;

    private JLabel nickNameLabel;
    private JList userSelectorList;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel eastPanel;
    private JFrame mainFrame;



    private final static int nickNameLength = 10;
    private final static int userIOWidthLength = 50;
    private final static int userIOHeightLength = 100;
    private final static String welcomeMsg = "Connection Succeeded - Welcome ";
    private final static String connectLabel = "Connect";
    private final static String sendLabel = "Send!";
    private final static String windowLabel = "Chat Window";
    private final static String nickNameLabelStr = "Nickname:";


    private ConnectionProxy proxy;


    public ClientGui() {
        nickName = new JTextField(nickNameLength);
        userInput = new JTextField(userIOWidthLength);
        chatOutput = new JTextArea(welcomeMsg,userIOHeightLength,userIOWidthLength);
        connect = new JButton(connectLabel);
        send = new JButton(sendLabel);
        mainFrame = new JFrame(windowLabel);
        userSelectorList = new JList();

        nickNameLabel = new JLabel(nickNameLabelStr);

        eastPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();

        proxy = new ConnectionProxy();
        proxy.addConsumer(this);
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

        eastPanel.add(userSelectorList);
        chatOutput.setHighlighter(null);
        chatOutput.setEditable(false);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(BorderLayout.NORTH, northPanel);
        mainFrame.add(BorderLayout.SOUTH,southPanel);
        mainFrame.add(BorderLayout.CENTER,chatOutput);
        mainFrame.add(BorderLayout.WEST,userSelectorList);
        mainFrame.setSize(1000,800);
    }

    public void start() {
        mainFrame.setVisible(true);
        proxy.start();
    }

    public static void main(String[] args) {
        ClientGui gui = new ClientGui();
        gui.initGui();
        gui.start();
    }

    public class chatListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String msg;
            if (e.getSource() == connect)
                msg = nickName.getText();
            else
                msg =userInput.getText();
            proxy.consume(msg);
        }
    }

}

// TODO: make text area ScrollPage