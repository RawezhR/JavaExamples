package Datagram;

import Datagram.ui.DatagramUI;

/**
 *  This Java example uses the Datagram class to send data from one client to another. 
 *  The data is a Java String object. The program serializes the String object and sends it to a server. 
 *  The server deserializes the object and displays it in the window. Run the program on two different
 *  machines. Enter IP addresses for each machine and use the same port number. 
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
