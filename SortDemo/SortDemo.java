package SortDemo;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import SortDemo.threads.SortThread;
import SortDemo.ui.SortGraph;
import SortDemo.ui.SortGraph.SortingAlgorithms;

/**
 *  Description:
 *  This demo visualizes and compares some classic sorting algorithms.
 *  Select two sorting algorithms and click Go!
 */
@SuppressWarnings("serial")
public class SortDemo extends JFrame implements ActionListener, WindowListener {

	private SpringLayout containerLayout;
	private SortGraph leftGraph, rightGraph;
	private JComboBox<String> leftCombo, rightCombo;
	private Container container;    
	private JLabel leftLabel, rightLabel;
	private JLabel bottomLeftLabel;
	private static final int WIDTH = 450;
	private static final int HEIGHT = 330;
	private SortThread leftThread, rightThread;
	private JButton button;

	public SortDemo() {

		/** Create the frame */
		setTitle("Sort Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(this);        

		/** Create the container */
		container = getContentPane();
		containerLayout = new SpringLayout();
		container.setLayout(containerLayout);
		container.setPreferredSize(new Dimension(WIDTH,HEIGHT));          

		/** create the graph panels */
		leftGraph = new SortGraph();
		leftGraph.setAlgorithm(SortingAlgorithms.BUBBLE_SORT);
		rightGraph = new SortGraph();
		rightGraph.setAlgorithm(SortingAlgorithms.QUICK_SORT);
		container.add(leftGraph);
		container.add(rightGraph);

		/** create the labels above the graphs */
		leftLabel = new JLabel(leftGraph.getAlgorithmName());
		container.add(leftLabel);
		rightLabel = new JLabel(rightGraph.getAlgorithmName());
		container.add(rightLabel);          

		/** this button begins the sorting (runs the two threads) */
		button = new JButton("Go!");
		button.addActionListener(this);
		container.add(button);
		int leftButtonOffset = (WIDTH / 2) - ((int)button.getPreferredSize().getWidth() / 2);

		/** this label displays how many numbers are to be sorted */
		bottomLeftLabel = new JLabel("Total numbers: " + leftGraph.getTotalNumbers());
		container.add(bottomLeftLabel);

		/** combo box to select the sorting algorithm for the left graph */
		leftCombo = new JComboBox<String>(leftGraph.getSortingNames());
		leftCombo.setSelectedIndex(leftGraph.getAlgorithm().ordinal());
		leftCombo.addActionListener(this);
		container.add(leftCombo);
		int leftComboOffset = (WIDTH / 4) - ((int)leftCombo.getPreferredSize().getWidth() / 2);

		/** combo box to select the sorting algorithm for the right graph */
		rightCombo = new JComboBox<String>(rightGraph.getSortingNames());
		rightCombo.setSelectedIndex(rightGraph.getAlgorithm().ordinal());
		rightCombo.addActionListener(this);
		container.add(rightCombo);        
		int rightComboOffset = (WIDTH / 4 * 3) - ((int)rightCombo.getPreferredSize().getWidth() / 2);

		/** lay out the widgets using a Spring Layout */

		containerLayout.putConstraint(SpringLayout.WEST, leftLabel, 10, SpringLayout.WEST, container);
		containerLayout.putConstraint(SpringLayout.NORTH, leftLabel, 10, SpringLayout.NORTH, container); 
		containerLayout.putConstraint(SpringLayout.WEST, leftGraph, 0, SpringLayout.WEST, leftLabel);
		containerLayout.putConstraint(SpringLayout.NORTH, leftGraph, 10, SpringLayout.SOUTH, leftLabel);   

		containerLayout.putConstraint(SpringLayout.WEST, rightLabel, 10, SpringLayout.EAST, leftGraph);
		containerLayout.putConstraint(SpringLayout.NORTH, rightLabel, 10, SpringLayout.NORTH, container); 
		containerLayout.putConstraint(SpringLayout.WEST, rightGraph, 0, SpringLayout.WEST, rightLabel);
		containerLayout.putConstraint(SpringLayout.NORTH, rightGraph, 10, SpringLayout.SOUTH, rightLabel);  

		containerLayout.putConstraint(SpringLayout.WEST, button, leftButtonOffset, SpringLayout.WEST, container);
		containerLayout.putConstraint(SpringLayout.NORTH, button, 20, SpringLayout.SOUTH, leftCombo); 

		containerLayout.putConstraint(SpringLayout.WEST, leftCombo, leftComboOffset, SpringLayout.WEST, container);
		containerLayout.putConstraint(SpringLayout.NORTH, leftCombo, 5, SpringLayout.SOUTH, leftGraph); 
		containerLayout.putConstraint(SpringLayout.WEST, rightCombo, rightComboOffset, SpringLayout.WEST, container);
		containerLayout.putConstraint(SpringLayout.NORTH, rightCombo, 5, SpringLayout.SOUTH, rightGraph);    

		containerLayout.putConstraint(SpringLayout.WEST, bottomLeftLabel, 10, SpringLayout.WEST, container);
		containerLayout.putConstraint(SpringLayout.SOUTH, bottomLeftLabel, 10, SpringLayout.SOUTH, button); 
	}

	/**
	 * This is the main class that starts the demo.
	 */
	public static void main(String[] args) {

		/** Schedule a job for the event-dispatching thread: */
		/** creating and showing this application's GUI. */
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SortDemo sd = new SortDemo();
				sd.pack();
				sd.setVisible(true);
			}
		});        

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == button) {
			if ((leftThread != null && leftThread.isAlive()) || (rightThread != null && rightThread.isAlive())) {
				//wait for threads to complete before running again
				return;
			}

			/** make sure threads are done */
			if (leftThread != null) {
				leftThread.interrupt();
				leftThread = null;
				leftGraph.shuffle();
				leftLabel.setText(leftGraph.getAlgorithmName());
			}
			if (rightThread != null) {
				rightThread.interrupt();
				rightThread = null;
				rightGraph.shuffle();
				rightLabel.setText(rightGraph.getAlgorithmName());
			}            

			leftThread = new SortThread(leftGraph, leftLabel, leftCombo);
			rightThread = new SortThread(rightGraph, rightLabel, rightCombo);  

			/** start separate threads to do the sorting (and animate them too) */
			leftThread.start();
			rightThread.start();
		} else if (e.getSource() == leftCombo) {
			/** set the sorting algorithm for the left graph */
			leftGraph.setAlgorithm(SortingAlgorithms.values()[leftCombo.getSelectedIndex()]);
			leftLabel.setText(leftGraph.getAlgorithmName());
		}  else if (e.getSource() == rightCombo) {
			/** set the sorting algorithm for the right graph */
			rightGraph.setAlgorithm(SortingAlgorithms.values()[rightCombo.getSelectedIndex()]);
			rightLabel.setText(rightGraph.getAlgorithmName());
		}
	}

	public SortGraph getLeftGraph() {
		return leftGraph;
	}

	public void setLeftGraph(SortGraph leftGraph) {
		this.leftGraph = leftGraph;
	}

	public SortGraph getRightGraph() {
		return rightGraph;
	}

	public void setRightGraph(SortGraph rightGraph) {
		this.rightGraph = rightGraph;
	}    

	/** placeholders */
	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
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
