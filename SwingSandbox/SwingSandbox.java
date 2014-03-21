import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Author: Edwin Torres
 *  email: CoachEd@gmail.com
 *  
 *  Description: This program is a sandbox for trying out Java Swing components.
 *  It features a button and a label. When the button is pressed, the label 
 *  is updated. There is a listener to capture the window closing event, as well
 *  as the button press event.
 */
public class SwingSandbox implements ActionListener, WindowListener {

    private JButton button;
    private JLabel label;
    private int numPresses = 0;

    /** create your Swing UI */
    private void createAndShowGUI() {

        /** the main frame */
        JFrame frame = new JFrame("Main");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(225,125));
        frame.setLayout(new FlowLayout());

        /** here is the content pane of the main frame */
        Container container = frame.getContentPane();


        /** add your Swing components here */
        label = new JLabel("Number of presses: " + numPresses);
        container.add(label);

        button = new JButton("Press me");
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(90,30));
        container.add(button);


        /** show the frame to the user */
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        final SwingSandbox sandbox = new SwingSandbox();

        /** schedule for the event dispatcher */
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                sandbox.createAndShowGUI();
            }
        });
    }

    @Override
    public void windowClosing(WindowEvent e) {
        /** executed when the user closes the window.
         *  put cleanup code here.
         */

        System.out.println("Bye.");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            numPresses++;
            label.setText("Number of presses: " + numPresses);
        }

    }

}
