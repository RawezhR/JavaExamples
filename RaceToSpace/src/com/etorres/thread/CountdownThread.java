package com.etorres.thread;

import com.etorres.ui.RacePanel;
/*
 * This thread displays a count down timer on the 
 * main screen.
 */
public class CountdownThread extends Thread {

    private RacePanel panel;

    public CountdownThread(RacePanel p) {
        super();
        panel = p;
    }

    public void run() {
        int sleepMs = 1000;
        for (int i=5; i >= 0; i--) {

            if (i != 0)
                panel.setNumber("     " + i +"    ");
            else {
                panel.setNumber("Blastoff!");
                panel.getThreadLeftRocket().start();
                panel.getThreadRightRocket().start();                
                sleepMs = 1000;
            }
            panel.repaint();
            try {
                Thread.sleep(sleepMs);
            } catch (InterruptedException e) {
                break;
            }
        }
        panel.setNumber("");
        panel.repaint();


    }

}
