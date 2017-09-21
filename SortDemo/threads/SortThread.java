package SortDemo.threads;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import SortDemo.ui.SortGraph;

public class SortThread extends Thread {

	private SortGraph sortGraph;
	private JLabel label;
	private JComboBox<String> comboBox;

	public SortThread(SortGraph sg, JLabel l, JComboBox<String> cb) {
		sortGraph = sg;
		label = l;
		comboBox = cb;
	}

	public void run() {
		comboBox.setEnabled(false);
		sortGraph.sort();
		label.setText(sortGraph.getAlgorithmName() + "  (" + sortGraph.getComparisons() + " comparisons)");
		comboBox.setEnabled(true);
	}
}
