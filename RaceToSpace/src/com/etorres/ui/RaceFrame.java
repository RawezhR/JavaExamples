package com.etorres.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

/**
 * Author: Edwin Torres
 *  email: CoachEd@gmail.com
 *  
 *  Description: This is the main window frame for the program.
 */
public class RaceFrame extends JFrame implements WindowListener {
    private static final long serialVersionUID = -2322155098234189963L;

    private SpringLayout containerLayout;
    private RacePanel panelRace;
    private Container container;    
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;

    public RaceFrame() {
        super();

        /** Create the frame */
        setTitle("Race to Space");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(this);        

        /** Create the container */
        container = getContentPane();
        containerLayout = new SpringLayout();
        container.setLayout(containerLayout);
        container.setPreferredSize(new Dimension(WIDTH,HEIGHT));          

        /** create the race panel */
        panelRace = new RacePanel();
        container.add(panelRace);

        /** lay out the widgets using a Spring Layout */

        containerLayout.putConstraint(SpringLayout.WEST, panelRace, 0, SpringLayout.WEST, container);
        containerLayout.putConstraint(SpringLayout.NORTH, panelRace, 0, SpringLayout.NORTH, container); 
    }

    /** placeholders */
    @Override
    public void windowClosing(WindowEvent e) {
        /** do clean up here when the window is closed */
        if (panelRace != null) {
            if (panelRace.getThreadCountdown() != null)
                panelRace.getThreadCountdown().interrupt();
            if (panelRace.getThreadLeftRocket() != null)
                panelRace.getThreadLeftRocket().interrupt();
            if (panelRace.getThreadRightRocket() != null)
                panelRace.getThreadRightRocket().interrupt();            
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }    

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
