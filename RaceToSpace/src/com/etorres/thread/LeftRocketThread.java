package com.etorres.thread;

import com.etorres.ui.RacePanel;

/*
 * This thread moves the LEFT rocket and varies the booster rocket
 * display.
 * 
 * This is practically identical to the RightRocketThread. There is a more
 * elegant way to do this, but this is just a sample program. :-P
 */
public class LeftRocketThread extends Thread {

    private RacePanel panel;
    private boolean bContinue;
    private static final int SLEEP_MS = 100; //default
    private int step = 1;

    public LeftRocketThread(RacePanel p) {
        super();
        panel = p;
        bContinue = true;
    }

    public void run() {
        int i=0;
        int sleepMs = SLEEP_MS;
        while(bContinue) {
            i += step;
            panel.setLeftRocketHeight(i++);
            panel.repaint();
            try {
                Thread.sleep(sleepMs);
            } catch (InterruptedException e) {
                bContinue = false;
            }
            sleepMs = (int)Math.round((Math.random() * SLEEP_MS) + 1);
            boolean bBooster = (int)Math.round(Math.random()) == 1 ? true : false;
            panel.setbShowLeftBooster(bBooster);
        }
        panel.setbShowLeftBooster(true);
        panel.repaint();
    }

}
