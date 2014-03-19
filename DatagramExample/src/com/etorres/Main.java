package com.etorres;

import com.etorres.ui.DatagramUI;

/**
 * @author Edwin Torres
 *  email: CoachEd@gmail.com
 *  description:
 *  
 *  This program uses a Java Datagram messages to send data to another 
 *  instance of this program. It accepts String input from the user, serializes it to bytes,
 *  and sends the data to the server. This program also displays similar incoming Datagram 
 *  messages.
 */
public class Main {

    /**
     * The main driver for the Datagram Example
     */
    public static void main(String[] args) {

        final DatagramUI client = new DatagramUI();

        /** schedule a job to start the UI */
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                client.createUI();
            }
        });
    }

}
