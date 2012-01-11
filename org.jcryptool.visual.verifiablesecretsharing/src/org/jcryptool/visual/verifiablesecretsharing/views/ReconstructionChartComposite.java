package org.jcryptool.visual.verifiablesecretsharing.views;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.jcryptool.core.util.fonts.FontService;
import org.jcryptool.visual.verifiablesecretsharing.*;
import org.jcryptool.visual.verifiablesecretsharing.algorithm.VerifiableSecretSharing;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
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

public class ReconstructionChartComposite extends Composite {
	double[] playerID = { 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0 };
	double[] shares = { 4.0, 7.4, 10.2, 11.1, 12.2, 13.3, 14.4, 22.5 };

	public ReconstructionChartComposite(final Composite parent,
			final int style,
			VerifiableSecretSharingView verifiableSecretSharingView) {
		super(parent, style);
		setLayout(new GridLayout());
		
		createHead();
		createBody();
		
//		JFreeChart floatChart = createChart(createDataset());
//		final ChartComposite frame = new ChartComposite(this, SWT.NONE,
//				floatChart, true);
	}

//	 @Override
//	 public void createPartControl(Composite parent) {
//	 JFreeChart floatChart = createChart(createDataset());
//	
//	 // ChartPanel chartPanel = new ChartPanel(floatChart);
//	
//	 // chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
//	
//	 // final XYDataset dataset = createDataset();
//	 // final JFreeChart chart = createChart(dataset);
//	 final ChartComposite frame = new ChartComposite(parent,
//	 SWT.NONE,floatChart, true);
//	 }
	
	/**
	 * Generates the head of the tab. The head has a title and a description.
	 */
	private void createHead() {
		final Composite head = new Composite(this, SWT.NONE);
//		head.setBackground(WHITE);
		head.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		head.setLayout(new GridLayout());

		final Label label = new Label(head, SWT.NONE);
		label.setFont(FontService.getHeaderFont());
//		label.setBackground(WHITE);
		label.setText(Messages.VerifiableSecretSharingComposite_tab_title);
//		stDescription = new StyledText(head, SWT.READ_ONLY | SWT.MULTI
//				| SWT.WRAP);
//		stDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
//				false));
//		stDescription
//				.setText(Messages.VerifiableSecretSharingComposite_description);
	}

	private void createBody() {
		final Composite body = new Composite(this, SWT.NONE);
		body.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		body.setLayout(new FillLayout());
		
		JFreeChart chart = createChart(createDataset());
		new ChartComposite(body, SWT.None,
				chart, true);
//		createInputBody(body);
//		createDescriptionGroup(body);
	}

	private XYDataset createDataset() {

		final XYSeries series1 = new XYSeries("P(x)");

		// for(int i = 0;i<playerID[i];i++){
		// series1.add(playerID[i],shares[i]);
		// }
		series1.add(playerID[1], shares[1]);
		series1.add(playerID[2], shares[2]);
		series1.add(playerID[3], shares[3]);
		series1.add(playerID[4], shares[4]);
		series1.add(playerID[5], shares[5]);
		series1.add(playerID[6], shares[6]);

		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);

		return dataset;
	}

	/**
	 * Creates a chart.
	 * 
	 * @param dataset
	 *            the data for the chart.
	 * 
	 * @return a chart.
	 */
	private JFreeChart createChart(final XYDataset dataset) {

		// create the chart...

		final JFreeChart chart = ChartFactory.createXYLineChart(
				"Line Chart Demo 6", // chart title
				"Player", // x axis label
				"Share", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
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

}
