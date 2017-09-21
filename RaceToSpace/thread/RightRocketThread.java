package RaceToSpace.thread;

import RaceToSpace.ui.RacePanel;

/*
 * This thread moves the RIGHT rocket and varies the booster rocket
 * display.
 * 
 * This is practically identical to the LeftRocketThread. There is a more
 * elegant way to do this, but this is just a sample program. :-P
 */
public class RightRocketThread extends Thread {

	private RacePanel panel;
	private boolean bContinue;
	private static final int SLEEP_MS = 100;
	private int step = 1;

	public RightRocketThread(RacePanel p) {
		super();
		panel = p;
		bContinue = true;
	}

	public void run() {
		int i=0;
		int sleepMs = SLEEP_MS;
		while(bContinue) {
			i += step;
			panel.setRightRocketHeight(i++);
			panel.repaint();
			try {
				Thread.sleep(sleepMs);
			} catch (InterruptedException e) {
				bContinue = false;
			}
			sleepMs = (int)Math.round((Math.random() * SLEEP_MS) + 1);
			boolean bBooster = (int)Math.round(Math.random()) == 1 ? true : false;
			panel.setbShowRightBooster(bBooster);
		}
		panel.setbShowRightBooster(true);
		panel.repaint();
	}

}
