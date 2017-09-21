import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JFreeChartExample extends Application {

	/**
	 * This example shows how to create a sample chart using the JFreeChart library.
	 * External jars: jfreechart-1.0.17.jar , jcommon-1.0.21.jar
	 */

	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	public Pane pane;
	public SwingNode swingNode;

	@Override
	public void start(Stage primaryStage) throws Exception {
		pane = new Pane();

		/** mock up some chart data */
		XYSeriesCollection my_data_series= new XYSeriesCollection();
		XYSeries xysBytesRead = new XYSeries("bytes read");
		XYSeries xysBytesWritten = new XYSeries("bytes written");
		for (int i=0; i < 60; i++) {
			xysBytesRead.add(i,Math.random() * 100);
			xysBytesWritten.add(i,Math.random() * 20);
		}
		my_data_series.addSeries(xysBytesRead);
		my_data_series.addSeries(xysBytesWritten);

		/** create the chart and add it to our frame */
		JFreeChart XYLineChart=ChartFactory.createXYLineChart("Performance","seconds","bytes",my_data_series,PlotOrientation.VERTICAL,true,true,false);
		ChartPanel chartpanel = new ChartPanel(XYLineChart);
		chartpanel.setPreferredSize(new Dimension(WIDTH,HEIGHT));			

		swingNode = new SwingNode();
		swingNode.setContent(chartpanel);
		pane.getChildren().add(swingNode);

		Scene scene = new Scene(pane,WIDTH,HEIGHT);
		primaryStage.setTitle("JFreeChart Example");
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				//stage is closing
				System.out.println("Bye.");
				Platform.exit();
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}    

}
