package Datagram.threads;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import Datagram.ui.DatagramUI;

public class DatagramServer extends Thread {

	private DatagramSocket socket = null;
	private DatagramUI ui;

	public DatagramServer(int port, DatagramUI d) throws IOException {
		this("DatagramServerThread", d);
	}

	public DatagramServer(String name, DatagramUI d) throws IOException {
		super(name);
		ui = d;
		try {
			socket = new DatagramSocket(ui.getPort());
		} catch(BindException e) {
			socket = null;
			ui.getLabelMessages().setText("Unable to create socket.");
		}
	}

	public void run() {

		boolean bContinue = true;

		while (bContinue) {
			try {
				byte[] buf = new byte[256];

				DatagramPacket packet = new DatagramPacket(buf, buf.length);

				/** wait here for incoming data */
				socket.receive(packet);

				/** received data */
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				byte[] data = packet.getData();

				/** deserialize the data into a String object (what we expect) */
				ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data));
				String sData = (String)in.readObject();
				in.close();

				String sText = ui.getTextIncoming().getText();
				sText += address.getHostAddress() + ":" + port + "  " + sData + "\n";
				ui.getTextIncoming().setText(sText);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

	public DatagramSocket getSocket() {
		return socket;
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}

}
