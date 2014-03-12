package com.etorres.jfcex;

import java.awt.Dimension;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Main {

    /**
     * Author: Edwin Torres
     *  email: CoachEd@gmail.com
     *  
     * This example shows how to create a sample chart using the JFreeChart library.
     * 
     */
    
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    
    public static void main(String[] args) {

        /** Schedule a job for the event-dispatching thread: */
        /** create and show the UI */
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
                /** create the frame */
                JFrame f = new JFrame("");

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
                final ChartPanel CP = new ChartPanel(XYLineChart);
                CP.setPreferredSize(new Dimension(WIDTH,HEIGHT));             
                f.add(CP);
                
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
