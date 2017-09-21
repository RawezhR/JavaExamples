package RaceToSpace.ui;

public class RaceMain {

	/**
	 *  Description:
	 *  
	 *  Welcome to Race to Space!
	 *  
	 *  This program displays two rockets racing into space (top of the window).
	 *  It renders the rockets using Java Graphics. There are separate threads
	 *  to manage the red rocket, blue rocket, and countdown timer.
	 */
	public static void main(String[] args) {
		/** Schedule a job for the event-dispatching thread: */
		/** creating and showing this application's GUI. */
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				RaceFrame frame = new RaceFrame();
				frame.pack();
				frame.setVisible(true);
			}
		}); 
	}

}
