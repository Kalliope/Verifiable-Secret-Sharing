package org.jcryptool.visual.verifiablesecretsharing.views;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.jcryptool.visual.verifiablesecretsharing.*;
import org.jcryptool.visual.verifiablesecretsharing.algorithm.VerifiableSecretSharing;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYRangeInfo;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.experimental.chart.swt.ChartComposite;



public class ChartView extends ViewPart{
	
	double[] playerID = {0.0,1.0,2.0,3.0,4.0,5.0,6.0,7.0};
	double[] shares = {4.0,7.4,10.2,11.1,12.2,13.3,14.4,22.5};
	
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
        
//        for(int i = 0;i<playerID[i];i++){
//        	series1.add(playerID[i],shares[i]);
//        }
        series1.add(playerID[1],shares[1]);
        series1.add(playerID[2],shares[2]);
        series1.add(playerID[3],shares[3]);
        series1.add(playerID[4],shares[4]);
        series1.add(playerID[5],shares[5]);
        series1.add(playerID[6],shares[6]);

        
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
    // XYSplineRenderer -- show data points
    chart.getXYPlot().setRenderer(new XYSplineRenderer());
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
