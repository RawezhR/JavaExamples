package RaceToSpace.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import RaceToSpace.thread.CountdownThread;
import RaceToSpace.thread.LeftRocketThread;
import RaceToSpace.thread.RightRocketThread;

/**
 * @author Edwin Torres
 *  email: CoachEd@gmail.com
 *  
 *  description: This is the main graphics panel that shows the rockets racing.
 *  
 */
public class RacePanel extends JPanel {
	private static final long serialVersionUID = 4384624673617030146L;

	private Image offscreenBuffer; //for double-buffering (reduce flicker)

	private int rightRocketHeight = 0;
	private int leftRocketHeight = 0;
	private boolean bShowLeftBooster = false;
	private boolean bShowRightBooster = false;
	private boolean bLeftWon = false; 
	private String number = "";

	private LeftRocketThread threadLeftRocket;
	private RightRocketThread threadRightRocket;
	private CountdownThread threadCountdown;

	private static final int WIDTH = 400;
	private static final int HEIGHT = 600;   

	private boolean[][] arrStars = new boolean[WIDTH][HEIGHT];

	/** Constructors */
	public RacePanel() {
		super();
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		threadCountdown = new CountdownThread(this);
		threadLeftRocket = new LeftRocketThread(this);
		threadRightRocket = new RightRocketThread(this);

		//fill stars array
		for (int i=0; i < WIDTH; i++) {
			for (int j=0; j < HEIGHT; j++) {
				double num = Math.random();
				if (num < 0.0006) {
					arrStars[i][j] = true;
				} else {
					arrStars[i][j] = false;
				}
			}
		}        

		init();
	}

	/** Methods */

	public void init() {
		setBackground(Color.black);
		threadCountdown.start(); //start the countdown
	}

	public void paint(Graphics g) {
		Dimension d = getSize();
		Graphics2D g2 = (Graphics2D) g; 
		g2.setBackground(Color.black);

		/** clear the screen (double-buffering) */
		g2.clearRect(0, 0,  (int)d.getWidth(), (int)d.getHeight());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		/** draw the stars */
		drawStars(g);

		/** countdown timer? */
		if (number.length() > 0) {
			g2.setFont(new Font(null, Font.BOLD, 48));
			g2.setPaint(Color.white);
			g.drawString(number, 110, 300);            
		}

		/** draw the two rockets */
		int leftOffset = 475 - leftRocketHeight;
		int rightOffset = 475 - rightRocketHeight;
		if ((leftOffset+50) <= 0 || (rightOffset+50) <= 0) {
			/** we have a winner */
			if (leftOffset < rightOffset) {
				bLeftWon = true;
			} else {
				bLeftWon = false;
			}
			threadLeftRocket.interrupt();
			threadRightRocket.interrupt();

			g2.setFont(new Font(null, Font.BOLD, 48));
			g2.setPaint(Color.yellow);
			if (bLeftWon)
				g.drawString("Red wins!", 80, 300);
			else
				g.drawString("Blue wins!", 75, 300);
		}
		drawRocket(g, 55, leftOffset, Color.red, bShowLeftBooster);
		drawRocket(g, 230,rightOffset, Color.blue, bShowRightBooster);
	}

	public void drawRocket(Graphics g, int xoffset, int yoffset, Color coneColor, boolean bShowBooster) {
		Graphics2D g2 = (Graphics2D) g;

		//draw main body
		g2.setPaint(Color.white);
		Rectangle2D rect = new Rectangle2D.Double(40+xoffset, 76+yoffset, 20,50);
		g2.fill(rect);

		//draw nose
		g2.setPaint(coneColor);
		int x1 = 50, y1 = 50;
		int x2 = 75, y2 = 75;
		int x3 = 25, y3 = 75;
		x3 += 14; x2 -= 14;
		int xpoints[] = {x1+xoffset, x2+xoffset, x3+xoffset};
		int ypoints[] = {y1+yoffset, y2+yoffset, y3+yoffset};
		g2.fillPolygon(xpoints,ypoints,xpoints.length);        

		//draw left tail
		g2.setPaint(Color.gray);
		x1 = 50; y1 = 50;
		x2 = x1; y2 = 70;
		x3 = 35; y3 = y2;
		x1 -= 10; x2 -= 10; x3 -= 10;
		y1 += 53; y2 += 53; y3 += 53;
		xpoints = new int[]{x1+xoffset, x2+xoffset, x3+xoffset};
		ypoints = new int[]{y1+yoffset, y2+yoffset, y3+yoffset};
		g2.fillPolygon(xpoints,ypoints,xpoints.length);         

		//draw right tail
		g2.setPaint(Color.gray);
		x1 = 50; y1 = 50;
		x2 = 65; y2 = 70;
		x3 = 50; y3 = y2;
		x1 += 10; x2 += 10; x3 += 10;
		y1 += 53; y2 += 53; y3 += 53;
		xpoints = new int[]{x1+xoffset, x2+xoffset, x3+xoffset};
		ypoints = new int[]{y1+yoffset, y2+yoffset, y3+yoffset};
		g2.fillPolygon(xpoints,ypoints,xpoints.length);           

		//draw exhaust pipe
		g2.setPaint(Color.gray);
		rect = new Rectangle2D.Double(42+xoffset, 122+yoffset, 15,8);
		g2.fill(rect);

		//draw booster
		if (bShowBooster) {
			g2.setPaint(Color.orange);
			rect = new Rectangle2D.Double(44+xoffset, 124+yoffset, 11,25);
			g2.fill(rect);  
		}
	}

	public void drawStars(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		for (int i=0; i < WIDTH; i++) {
			for (int j=0; j < HEIGHT; j++) {
				if (arrStars[i][j]) {
					double pct = Math.random();
					/** vary the colors of the stars (twinkle) */
					if (pct > 0.5)
						g2.setPaint(Color.WHITE);
					else if (pct > 0.2)
						g2.setPaint(Color.gray);
					else if (pct > 0.1)
						g2.setPaint(Color.green);
					else
						g2.setPaint(Color.blue);

					g2.fill (new Ellipse2D.Double(i, j, 2, 2)); 
				}
			}
		}
	}    

	/** override this method to reduce flicker (double-buffering) */
	public void update(Graphics g)
	{
		Graphics gr; 
		if (offscreenBuffer == null ||
				(!(offscreenBuffer.getWidth(this) == getWidth()
				&& offscreenBuffer.getHeight(this) == getHeight())))
		{
			offscreenBuffer = this.createImage(getWidth(), getHeight());
		}
		gr = offscreenBuffer.getGraphics();
		paint(gr); 
		g.drawImage(offscreenBuffer, 0, 0, this);     
	}         

	/** getters and setters */

	public int getRightRocketHeight() {
		return rightRocketHeight;
	}

	public void setRightRocketHeight(int rightRocketHeight) {
		this.rightRocketHeight = rightRocketHeight;
	}

	public int getLeftRocketHeight() {
		return leftRocketHeight;
	}

	public void setLeftRocketHeight(int leftRocketHeight) {
		this.leftRocketHeight = leftRocketHeight;
	}    
	public boolean isbShowLeftBooster() {
		return bShowLeftBooster;
	}

	public void setbShowLeftBooster(boolean bShowLeftBooster) {
		this.bShowLeftBooster = bShowLeftBooster;
	}

	public boolean isbShowRightBooster() {
		return bShowRightBooster;
	}

	public void setbShowRightBooster(boolean bShowRightBooster) {
		this.bShowRightBooster = bShowRightBooster;
	}

	public RightRocketThread getThreadRightRocket() {
		return threadRightRocket;
	}

	public void setThreadRightRocket(RightRocketThread threadRightRocket) {
		this.threadRightRocket = threadRightRocket;
	}    

	public LeftRocketThread getThreadLeftRocket() {
		return threadLeftRocket;
	}

	public void setThreadLeftRocket(LeftRocketThread threadLeftRocket) {
		this.threadLeftRocket = threadLeftRocket;
	}    

	public CountdownThread getThreadCountdown() {
		return threadCountdown;
	}

	public void setThreadCountdown(CountdownThread threadCountdown) {
		this.threadCountdown = threadCountdown;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
