package org.jcryptool.visual.verifiablesecretsharing.views;


import java.awt.Color;

import org.jcryptool.visual.verifiablesecretsharing.*;
import org.jcryptool.visual.verifiablesecretsharing.algorithm.VerifiableSecretSharing;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.experimental.chart.swt.ChartComposite;



public class ChartView extends ViewPart{
	
	@Override
	public void createPartControl(Composite parent) {	
		JFreeChart floatChart = createChart(createDataset());
		
//        ChartPanel chartPanel = new ChartPanel(floatChart);
        
//        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        
//		final XYDataset dataset = createDataset();
//        final JFreeChart chart = createChart(dataset);
       final ChartComposite frame = new ChartComposite(parent, SWT.NONE,floatChart, true);
	}
	
private XYDataset createDataset() {
        
        final XYSeries series1 = new XYSeries("P(x)");
        
        series1.add(1.0, 1.0);
        series1.add(2.0, 4.0);
        series1.add(3.0, 3.0);
        series1.add(4.0, 5.0);
        series1.add(5.0, 5.0);
        series1.add(6.0, 7.0);
        series1.add(7.0, 7.0);
        series1.add(8.0, 8.0);



        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
                
        return dataset;
        
    }

/**
 * Creates a chart.
 * 
 * @param dataset  the data for the chart.
 * 
 * @return a chart.
 */
private JFreeChart createChart(final XYDataset dataset) {
    
    // create the chart...
	
	
    final JFreeChart chart = ChartFactory.createXYLineChart(
        "Line Chart Demo 6",      // chart title
        "Player",                      // x axis label
        "Share",                      // y axis label
        dataset,                  // data
        PlotOrientation.VERTICAL,
        true,                     // include legend
        true,                     // tooltips
        false                     // urls
    );
    XYPlot plot = (XYPlot) chart.getPlot();
	plot.setBackgroundPaint(Color.lightGray);
	plot.setDomainGridlinePaint(Color.white);
	plot.setDomainGridlinesVisible(true);
	plot.setRangeGridlinePaint(Color.white);
    return chart;
    
}
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
