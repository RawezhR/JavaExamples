import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Author: Edwin Torres
 *  email: CoachEd@gmail.com
 *  
 *  Description: This program shows how to use the Graphics object
 *  to write Hello World! in a JPanel.
 */
public class PanelGraphics extends JPanel {

	private static final long serialVersionUID = 6615291965888806768L;

	public void paintComponent(Graphics g) {

		/** for example */
		g.setColor(Color.blue);
		Font font = new Font("Serif", Font.BOLD, 48);
		g.setFont(font);
		g.drawString("Hello World!", 20, 50);

	}

	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new PanelGraphics());
		frame.setSize(400, 250);
		frame.setVisible(true);
	}
}