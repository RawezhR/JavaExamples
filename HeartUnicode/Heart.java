import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/*
 *       Author: Edwin Torres
 *        email: CoachEd@gmail.com
 *  Description: This program shows how to display the unicode character for
 *               a heart in a Java Swing window. 
 */
public class Heart {

    public static final int WIDTH = 350;
    public static final int HEIGHT = 350;
    
    public static void main(String[] args) {
        /** Schedule a job for the event-dispatching thread: */
        /** create and show the UI */
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
                /** create the frame */
                JFrame f = new JFrame("");

                /** create a label of a heart. Use the unicode character for a heart */
                JLabel l = new JLabel("\u2665");
                l.setFont(new Font("Arial", Font.BOLD, 400));
                l.setForeground(Color.red);
                l.setVerticalAlignment(SwingConstants.CENTER);
                l.setHorizontalAlignment(SwingConstants.CENTER);
                f.add(l, BorderLayout.CENTER);
                
                /** other frame details */
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.pack();
                f.setSize(new Dimension(WIDTH,HEIGHT));
                f.setVisible(true);
                f.setLocation(100, 100);
            }
        });
        
        System.out.println("goodbye.");
    }

}
