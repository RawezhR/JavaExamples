package com.etorres.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.etorres.threads.DatagramServer;

public class DatagramUI implements ActionListener, WindowListener {

    /** widgets */ 
    private JTextArea textIncoming; 
    private JTextField textData, textPort, textServerIp;  
    private JScrollPane textScrollPane;
    private JLabel labelData, labelIncoming, labelPort, labelMessages, labelServerIp;
    private JButton buttonSend;  
    private int port;
    private String serverIp;
    private DatagramServer server = null; //my class
    private DatagramSocket socket = null; //outgoing socket
    private Boolean firstTimeSend = true;

    private static final int WIDTH = 450;
    private static final int HEIGHT = 575;

    public void createUI() {

        /** create the main frame for the UI */

        JFrame frame = new JFrame("Datagram Example");
        frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(this);

        SpringLayout layout = new SpringLayout();
        frame.getContentPane().setLayout(layout);

        /** add widgets to the frame */
        Container container = frame.getContentPane();

        labelData = new JLabel("Data:");
        container.add(labelData);

        labelIncoming = new JLabel("Incoming:");
        container.add(labelIncoming);

        labelPort = new JLabel("Port:");
        container.add(labelPort);

        labelServerIp = new JLabel("Server IP:");
        container.add(labelServerIp);  

        textServerIp = new JTextField("192.168.117.1",12);
        container.add(textServerIp); 

        labelMessages = new JLabel("");
        labelMessages.setForeground(Color.blue);
        container.add(labelMessages);

        textPort = new JTextField("4321",4);
        container.add(textPort);        

        textData = new JTextField("Happy Christmas", 21);
        container.add(textData);

        buttonSend = new JButton("Connect");
        buttonSend.addActionListener(this);
        container.add(buttonSend);

        textIncoming = new JTextArea(19, 34);
        textScrollPane = new JScrollPane(textIncoming); 
        textIncoming.setEditable(false);
        container.add(textScrollPane);

        /** lay out the components in the frame */
        layout.putConstraint(SpringLayout.WEST, labelData, 10, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, labelData, 10, SpringLayout.NORTH, container); 

        layout.putConstraint(SpringLayout.NORTH, buttonSend, -3, SpringLayout.NORTH, textData);
        layout.putConstraint(SpringLayout.WEST, buttonSend, 19, SpringLayout.EAST, textData);

        layout.putConstraint(SpringLayout.WEST, textData, 10, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, textData, 10, SpringLayout.SOUTH, labelData);

        layout.putConstraint(SpringLayout.WEST, labelServerIp, 10, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, labelServerIp, 10, SpringLayout.SOUTH, textData);

        layout.putConstraint(SpringLayout.WEST, textServerIp, 0, SpringLayout.WEST, labelServerIp);
        layout.putConstraint(SpringLayout.NORTH, textServerIp, 10, SpringLayout.SOUTH, labelServerIp);

        layout.putConstraint(SpringLayout.WEST, labelPort, 10, SpringLayout.EAST, textServerIp);
        layout.putConstraint(SpringLayout.NORTH, labelPort, 10, SpringLayout.SOUTH, textData);  

        layout.putConstraint(SpringLayout.WEST, textPort, 0, SpringLayout.WEST, labelPort);
        layout.putConstraint(SpringLayout.NORTH, textPort, 10, SpringLayout.SOUTH, labelPort);         

        layout.putConstraint(SpringLayout.WEST, labelMessages, 10, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, labelMessages, 15, SpringLayout.SOUTH, textServerIp);

        layout.putConstraint(SpringLayout.WEST, labelIncoming, 10, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, labelIncoming, 40, SpringLayout.SOUTH, labelMessages);        

        layout.putConstraint(SpringLayout.WEST, textScrollPane, 10, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, textScrollPane, 10, SpringLayout.SOUTH, labelIncoming);    


        /** show the window */
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonSend) {
            String text = textData.getText();
            String sPort = textPort.getText();
            serverIp = textServerIp.getText();

            labelMessages.setText("");

            /** validate the port */
            if (sPort == null) sPort = "";
            sPort = sPort.trim();
            if (sPort.length() == 0) {
                /** no port */
                labelMessages.setText("Please enter a UDP port.");
                return;                
            } else {
                try {
                    port = Integer.parseInt(sPort);
                } catch (NumberFormatException nfe) {
                    /** non-numeric port */
                    labelMessages.setText("Please enter a numeric UDP port.");
                    return;                     
                }
            }

            /** validate server IP */
            if (serverIp == null) serverIp = "";
            serverIp = serverIp.trim();
            if (serverIp.length() == 0) {
                /** server IP is blank */
                labelMessages.setText("Please enter a server IP.");
                return;                
            }

            /** if we make it here, input data is valid; send it */
            textPort.setEnabled(false);
            textServerIp.setEnabled(false);

            /** create the server first time */
            if (server == null) {
                try {
                    server = new DatagramServer("Server", this);
                    server.start();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }                
            }

            /** create the outgoing socket first time */
            if (socket == null) {
                try {
                    socket = new DatagramSocket();
                } catch (SocketException e1) {
                    e1.printStackTrace();
                } 
            }

            if (firstTimeSend) {
                firstTimeSend = false;
                buttonSend.setText("Send");
                return;
            }


            /** validate the data to send */
            if (text == null) text = "";
            text = text.trim();
            if (text.length() == 0) {
                /** no data to send */
                labelMessages.setText("Please enter data to send.");
                return;
            }             

            /** send the data */
            if (socket != null) {
                // send request
                byte[] buf;
                try {
                    InetAddress address = InetAddress.getByName(serverIp);

                    //Serialize the data (String object)
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutput oo = null;
                    oo = new ObjectOutputStream(bos);   
                    oo.writeObject(text);
                    buf = bos.toByteArray();

                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
                    socket.send(packet);  
                } catch (UnknownHostException uhe) {
                    uhe.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

            }

        }

    }

    public JTextArea getTextIncoming() {
        return textIncoming;
    }

    public void setTextIncoming(JTextArea textIncoming) {
        this.textIncoming = textIncoming;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosing(WindowEvent e) {

        /* do some cleanup */

        if (server != null) {
            server.interrupt();
            server = null;
        }

        if (socket != null) {
            socket.close();
            socket = null;
        }
        System.out.println("Bye.");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }    

}
