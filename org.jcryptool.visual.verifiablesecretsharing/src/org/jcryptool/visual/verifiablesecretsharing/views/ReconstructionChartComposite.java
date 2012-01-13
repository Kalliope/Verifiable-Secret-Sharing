//-----BEGIN DISCLAIMER-----
/*******************************************************************************
 * Copyright (c) 2011 JCrypTool Team and Contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
//-----END DISCLAIMER-----
package org.jcryptool.visual.verifiablesecretsharing.views;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.jcryptool.core.util.fonts.FontService;
import org.jcryptool.visual.verifiablesecretsharing.*;
import org.jcryptool.visual.verifiablesecretsharing.algorithm.Polynomial;
import org.jcryptool.visual.verifiablesecretsharing.algorithm.VerifiableSecretSharing;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.part.ViewPart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.PlotChangeEvent;
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
	private int[] playerID = { 0, 1, 2, 3, 4, 5, 6, 7 };
	private int[] shares = { 4, 7, 10, 11, 12, 13, 14, 22 };
	private Polynomial reconstructedPolynom = new Polynomial(new int[] { 123,
			25 });
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private Composite body;

	public int[] getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int[] playerID) {
		this.playerID = playerID;
	}

	public int[] getShares() {
		return shares;
	}

	public void setShares(int[] shares) {
		this.shares = shares;
	}

	public void setReconstructedPolynom(Polynomial reconstructedPolynom) {
		this.reconstructedPolynom = reconstructedPolynom;
	}
	
	public void redrawChart() {
        for(Control control : body.getChildren()) {
        	if(control.getData() == null) {
        		control.dispose();
        	}
        }
		chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Color.lightGray);
		chart = createChart(createDataset());
		chart.fireChartChanged();
        chartPanel.setChart(this.chart);
        chartPanel.updateUI();
		new ChartComposite(body, SWT.None, chart, true);
	}

	public ReconstructionChartComposite(final Composite parent,
			final int style,
			VerifiableSecretSharingView verifiableSecretSharingView) {
		super(parent, style);
		setLayout(new GridLayout());

		createHead();
		createBody();
	}

	/**
	 * Generates the head of the tab. The head has a title and a description.
	 */
	private void createHead() {
		final Composite head = new Composite(this, SWT.NONE);
		// head.setBackground(WHITE);
		head.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		head.setLayout(new GridLayout());

		final Label label = new Label(head, SWT.NONE);
		label.setFont(FontService.getHeaderFont());
		// label.setBackground(WHITE);
		label.setText(Messages.VerifiableSecretSharingComposite_tab_title);
		// stDescription = new StyledText(head, SWT.READ_ONLY | SWT.MULTI
		// | SWT.WRAP);
		// stDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
		// false));
		// stDescription
		// .setText(Messages.VerifiableSecretSharingComposite_description);
	}

	private void createBody() {
		body = new Composite(this, SWT.NONE);
		body.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		body.setLayout(new FillLayout());
		chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Color.lightGray);
		chart = createChart(createDataset());
		chart.fireChartChanged();
        chartPanel.setChart(this.chart);
        chartPanel.updateUI();
		new ChartComposite(body, SWT.None, chart, true);
		// createInputBody(body);
		// createDescriptionGroup(body);
	}

	private XYDataset createDataset() {

		final XYSeries playerAndSharesSeries = new XYSeries("Shares");
		final XYSeries reconstructionSeries = new XYSeries(
				"Reconstructed Polynom");
		int[] coef = reconstructedPolynom.getCoef();
		double y = 0;
		
		for (int i = 0; i < playerID.length && playerID[i] != 0; i++) {
			playerAndSharesSeries.add(playerID[i], shares[i]);
		}
		for (double i = 0; i < playerID[playerID.length-1]; i+=0.5) {
			for (int j=0; j<coef.length; j++) {
				y += coef[j]*Math.pow(i, j);
			}
			reconstructionSeries.add(i, y);
		}

		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(playerAndSharesSeries);
		dataset.addSeries(reconstructionSeries);

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

		final JFreeChart chart = ChartFactory.createXYLineChart("", // chart
																	// title
				"", // x axis label
				"", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, false, // include legend
				false, // tooltips
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
