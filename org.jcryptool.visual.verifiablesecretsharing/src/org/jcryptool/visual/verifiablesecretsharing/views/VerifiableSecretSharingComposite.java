// -----BEGIN DISCLAIMER-----
/*******************************************************************************
 * Copyright (c) 2010 JCrypTool Team and Contributors
 *
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
// -----END DISCLAIMER-----
package org.jcryptool.visual.verifiablesecretsharing.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.jcryptool.core.util.fonts.FontService;


public class VerifiableSecretSharingComposite extends Composite {

	public final String uZero = ("\u2080"); //$NON-NLS-1$
	public final String uOne = ("\u2081"); //$NON-NLS-1$
	public final String uTwo = ("\u2082"); //$NON-NLS-1$
	public final String uThree = ("\u2083"); //$NON-NLS-1$
	public final String uFour = ("\u2084"); //$NON-NLS-1$
	public final String uFive = ("\u2085"); //$NON-NLS-1$
	public final String uSix = ("\u2086"); //$NON-NLS-1$
	public final String uSeven = ("\u2087"); //$NON-NLS-1$
	public final String uEight = ("\u2088"); //$NON-NLS-1$
	public final String uNine = ("\u2089"); //$NON-NLS-1$

    private static final int RESTARTBUTTONHEIGHT = 30;
    private static final int RESTARTBUTTONWIDTH = 120;
    
	/* colors for backgrounds. */
    private static final Color WHITE = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
    private static final Color CYAN = Display.getDefault().getSystemColor(SWT.COLOR_CYAN);
    
    StyledText stDescription;
	private Composite inputBody;
	private GridLayout inputBodyLayout;
	private Group parametersGroup;
	private Label playerLabel;
	private Spinner playerSpinner;
	private Label reconstructorLabel;
	private Spinner reconstructorSpinner;
	private Label secretLabel;
	private Text secretText;
	private Label moduleLabel;
	private Text moduleText;
	private Label primitiveRootLabel;
	private Text primitiveRootText;
	private Label space1;
	private Label space2;
	private Label nextStepButtonParameters;
	private Button determineCoefficients;
	private RowLayout coefficientsGroupLayout;
	private GridLayout coefficientsPolynomNextStepLayout;
	private Group coefficientsGroup;
	private ScrolledComposite scrolledCoefficientsGroup;
	private Composite scrolledCoefficientsGroupContent;
	private Label[] coefficientsLabelsCoefficients;
	private Spinner[] coefficientsSpinnersCoefficients;
	private GridLayout commitGenerateButtonLayout;
	private Composite commitGenerateButtonComposite;
	private Button commitCoefficientsButton;
	private Button generateCoefficientsButton;
	private Composite polynomContent;
	private Label polynomLabel;
	private Text polynomText;
	private Composite nextStepContent;
	private Label nextStepButtonCoefficients;
	private Button calculateShares;
	private RowLayout commitmentsGroupLayout;
	private GridLayout commitmentsGroupGridLayout;
	private Group commitmentsGroup;
	private Composite scrolledCommitmentsGroupSubtitleContent;
	private Label coefficientLabel;
	private Label commitmentLabel;
	private GridData seperatorData;
	private Label horizontalSeperator;
	private ScrolledComposite scrolledCommitmentsGroup;
	private Composite scrolledCommitmentsGroupContent;
	private Label[] coefficientsLabelsCommitment;
	private Text[] coefficientsTextCommitment;
	private Group sharesGroup;
	private Group reconstructionGroup;
	private Group descriptionGroup;
    
    //Button restart;
	
    public VerifiableSecretSharingComposite(final Composite parent, final int style, VerifiableSecretSharingView verifiableSecretSharingView) {
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
        head.setBackground(WHITE);
        head.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        head.setLayout(new GridLayout());

        final Label label = new Label(head, SWT.NONE);
        label.setFont(FontService.getHeaderFont());
        label.setBackground(WHITE);
        label.setText(Messages.VerifiableSecretSharingComposite_tab_title);
        stDescription = new StyledText(head, SWT.READ_ONLY | SWT.MULTI | SWT.WRAP);
        stDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        stDescription.setText(Messages.VerifiableSecretSharingComposite_description);
    }
    
    private void createBody() {
    	final Composite body = new Composite(this, SWT.NONE);
    	body.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    	body.setLayout(new GridLayout());
    	
    	final Button restart = new Button(body, SWT.PUSH);
    	restart.setText(Messages.VerifiableSecretSharingComposite_restart_button);
    	restart.setForeground(CYAN);
    	restart.setLayoutData(new GridData(RESTARTBUTTONWIDTH, RESTARTBUTTONHEIGHT));
    	
		final Group bodyGroup = new Group(body, SWT.FILL);
		bodyGroup.setLayout(new GridLayout());
		bodyGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    	
    	createInputBody(bodyGroup);
		createDescriptionGroup(bodyGroup);
    }

	private void createInputBody(Group parent) {
		inputBody = new Composite(parent, SWT.NONE);
		inputBodyLayout = new GridLayout(5, false);
		inputBodyLayout.marginWidth = 0;
		inputBody.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		inputBody.setLayout(inputBodyLayout);

		createParametersGroup(inputBody);
		createCoefficientsGroup(inputBody, 5);
		createCommitmentsGroup(inputBody, 5);
		createSharesGroup(inputBody);
		createReconstructionGroup(inputBody);
	}

	private void createParametersGroup(Composite parent) {
		parametersGroup = new Group(parent, SWT.NONE);
		parametersGroup.setLayout(new GridLayout(2, false));
		parametersGroup.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, false));
		parametersGroup.setText(Messages.VerifiableSecretSharingComposite_parameters_title);
		
		playerLabel = new Label(parametersGroup, SWT.NONE);
		playerLabel.setText(Messages.VerifiableSecretSharingComposite_parameters_players);
		
		playerSpinner = new Spinner(parametersGroup, SWT.BORDER);
		playerSpinner.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		reconstructorLabel = new Label(parametersGroup, SWT.NONE);
		reconstructorLabel.setText(Messages.VerifiableSecretSharingComposite_parameters_reconstructors);
		
		reconstructorSpinner = new Spinner(parametersGroup, SWT.BORDER);
		reconstructorSpinner.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		secretLabel = new Label(parametersGroup, SWT.NONE);
		secretLabel.setText(Messages.VerifiableSecretSharingComposite_parameters_secret);
		
		secretText = new Text(parametersGroup, SWT.BORDER);
		secretText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		moduleLabel = new Label(parametersGroup, SWT.NONE);
		moduleLabel.setText(Messages.VerifiableSecretSharingComposite_parameters_primeMod);
		
		moduleText = new Text(parametersGroup, SWT.BORDER);
		moduleText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		primitiveRootLabel = new Label(parametersGroup, SWT.NONE);
		primitiveRootLabel.setText(Messages.VerifiableSecretSharingComposite_parameters_primitiveRoot);
		
		primitiveRootText = new Text(parametersGroup, SWT.BORDER);
		primitiveRootText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		space1 = new Label(parametersGroup, SWT.NONE);
		space2 = new Label(parametersGroup, SWT.NONE);
		
		nextStepButtonParameters = new Label(parametersGroup, SWT.NONE);
		nextStepButtonParameters.setText(Messages.VerifiableSecretSharingComposite_nextStep_button);
		determineCoefficients = new Button(parametersGroup, SWT.NONE);
		determineCoefficients.setText(Messages.VerifiableSecretSharingComposite_parameters_determineCoefficients);
		determineCoefficients.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));		
	}

	private void createCoefficientsGroup(Composite parent, int coefficients) {
		coefficientsGroupLayout = new RowLayout();
		coefficientsGroupLayout.type = SWT.VERTICAL;
		coefficientsGroupLayout.marginWidth = 0;
		coefficientsGroupLayout.marginHeight = 0;
		
		coefficientsPolynomNextStepLayout = new GridLayout(2,false);
		coefficientsPolynomNextStepLayout.marginWidth = 0;
		coefficientsPolynomNextStepLayout.marginHeight = 0;
		
		coefficientsGroup = new Group(parent, SWT.NONE);
		coefficientsGroup.setLayout(coefficientsGroupLayout);
		coefficientsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		coefficientsGroup.setText(Messages.VerifiableSecretSharingComposite_coefficients_title);
		
		scrolledCoefficientsGroup = new ScrolledComposite(coefficientsGroup, SWT.V_SCROLL);
		scrolledCoefficientsGroup.setExpandHorizontal(true);
		scrolledCoefficientsGroup.setLayoutData(new RowData(200, 79));
		
		scrolledCoefficientsGroupContent = new Composite(scrolledCoefficientsGroup, SWT.NONE);
		scrolledCoefficientsGroupContent.setLayout(coefficientsPolynomNextStepLayout);
		scrolledCoefficientsGroupContent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		coefficientsLabelsCoefficients = new Label[coefficients];
		coefficientsSpinnersCoefficients = new Spinner[coefficients];
		coefficientsLabelsCoefficients[0] = new Label(scrolledCoefficientsGroupContent, SWT.NONE);
		coefficientsLabelsCoefficients[0].setText("a0 = s");
		coefficientsSpinnersCoefficients[0] = new Spinner(scrolledCoefficientsGroupContent, SWT.BORDER);
		coefficientsSpinnersCoefficients[0].setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, false));
		coefficientsSpinnersCoefficients[0].setEnabled(false);
		for (int i=1; i<coefficients; i++) {
			coefficientsLabelsCoefficients[i] = new Label(scrolledCoefficientsGroupContent, SWT.NONE);
			coefficientsLabelsCoefficients[i].setText("a"+i);
			
			coefficientsSpinnersCoefficients[i] = new Spinner(scrolledCoefficientsGroupContent, SWT.BORDER);
			coefficientsSpinnersCoefficients[i].setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, false));
		}
		
		scrolledCoefficientsGroup.setContent(scrolledCoefficientsGroupContent);
		scrolledCoefficientsGroupContent.pack();

		commitGenerateButtonLayout = new GridLayout(2,true);
		commitGenerateButtonLayout.marginWidth = 0;
		commitGenerateButtonLayout.marginHeight = 0;
		
		commitGenerateButtonComposite = new Composite(coefficientsGroup, SWT.NONE);
		commitGenerateButtonComposite.setLayout(commitGenerateButtonLayout);
		commitGenerateButtonComposite.setLayoutData(new RowData(220,-1));
		
		commitCoefficientsButton = new Button(commitGenerateButtonComposite, SWT.NONE);
		commitCoefficientsButton.setText(Messages.VerifiableSecretSharingComposite_coefficients_commit_button);
		commitCoefficientsButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		generateCoefficientsButton = new Button(commitGenerateButtonComposite, SWT.PUSH);
		generateCoefficientsButton.setText(Messages.VerifiableSecretSharingComposite_coefficients_generate_button);
		generateCoefficientsButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		polynomContent = new Composite(coefficientsGroup, SWT.NONE);
		polynomContent.setLayout(coefficientsPolynomNextStepLayout);
		polynomContent.setLayoutData(new RowData(220, -1));
		
		polynomLabel = new Label(polynomContent, SWT.NONE);
		polynomLabel.setText("P(x)    ");
		
		polynomText = new Text(polynomContent, SWT.BORDER);
		polynomText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		nextStepContent = new Composite(coefficientsGroup, SWT.NONE);
		nextStepContent.setLayout(coefficientsPolynomNextStepLayout);
		nextStepContent.setLayoutData(new RowData(220, -1));
		
		space1 = new Label(nextStepContent, SWT.NONE);
		space2 = new Label(nextStepContent, SWT.NONE);
		
		nextStepButtonCoefficients = new Label(nextStepContent, SWT.NONE);
		nextStepButtonCoefficients.setText(Messages.VerifiableSecretSharingComposite_nextStep_button);
		calculateShares = new Button(nextStepContent, SWT.NONE);
		calculateShares.setText(Messages.VerifiableSecretSharingComposite_coefficients_calculateShares_button);
		calculateShares.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));	
	}

	private void createCommitmentsGroup(Composite parent, int commitments) {
		commitmentsGroupLayout = new RowLayout();
		commitmentsGroupLayout.type = SWT.VERTICAL;
		commitmentsGroupLayout.marginWidth = 0;
		commitmentsGroupLayout.marginHeight = 0;
		
		commitmentsGroupGridLayout = new GridLayout(2,false);
		commitmentsGroupGridLayout.marginWidth = 0;
		commitmentsGroupGridLayout.marginHeight = 0;
		
		commitmentsGroup = new Group(parent, SWT.NONE);
		commitmentsGroup.setLayout(commitmentsGroupLayout);
		commitmentsGroup.setLayoutData(new GridData(SWT.FILL,SWT.FILL, false, false));
		commitmentsGroup.setText(Messages.VerifiableSecretSharingComposite_commitments_title);
		
		scrolledCommitmentsGroupSubtitleContent = new Composite(commitmentsGroup, SWT.NONE);
		scrolledCommitmentsGroupSubtitleContent.setLayout(commitmentsGroupGridLayout);
		scrolledCommitmentsGroupSubtitleContent.setLayoutData(new RowData(180,-1));
		
		coefficientLabel = new Label(scrolledCommitmentsGroupSubtitleContent, SWT.NONE);
		coefficientLabel.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, true));
		coefficientLabel.setText(Messages.VerifiableSecretSharingComposite_commitments_coefficient_subtitle);
		
		commitmentLabel = new Label(scrolledCommitmentsGroupSubtitleContent, SWT.NONE);
		commitmentLabel.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, true));
		commitmentLabel.setText(Messages.VerifiableSecretSharingComposite_commitments_commitment_subtitle);
		
		seperatorData = new GridData(SWT.FILL, SWT.FILL, true, false);
		seperatorData.horizontalSpan = 2;
		
		horizontalSeperator = new Label(scrolledCommitmentsGroupSubtitleContent, SWT.SEPARATOR | SWT.HORIZONTAL);
		horizontalSeperator.setLayoutData(seperatorData);
		
		scrolledCommitmentsGroup = new ScrolledComposite(commitmentsGroup, SWT.V_SCROLL);
		scrolledCommitmentsGroup.setExpandHorizontal(true);
		scrolledCommitmentsGroup.setLayoutData(new RowData(160, 160));

		scrolledCommitmentsGroupContent = new Composite(scrolledCommitmentsGroup, SWT.NONE);
		scrolledCommitmentsGroupContent.setLayout(commitmentsGroupGridLayout);
		scrolledCommitmentsGroupContent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		coefficientsLabelsCommitment = new Label[commitments];
		coefficientsTextCommitment = new Text[commitments];
		for (int i=0; i<commitments; i++) {
			coefficientsLabelsCommitment[i] = new Label(scrolledCommitmentsGroupContent, SWT.NONE);
			coefficientsLabelsCommitment[i].setText("a"+(i+1));
			coefficientsLabelsCommitment[i].setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));
			
			coefficientsTextCommitment[i] = new Text(scrolledCommitmentsGroupContent, SWT.BORDER);
			coefficientsTextCommitment[i].setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, false));
		}
		
		scrolledCommitmentsGroup.setContent(scrolledCommitmentsGroupContent);
		scrolledCommitmentsGroupContent.pack();
	}

	private void createSharesGroup(Composite parent) {
		sharesGroup = new Group(parent, SWT.NONE);
		sharesGroup.setLayout(new GridLayout(2, false));
		sharesGroup.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, false));
		sharesGroup.setText(Messages.VerifiableSecretSharingComposite_shares_title);
	}

	private void createReconstructionGroup(Composite parent) {
		reconstructionGroup = new Group(parent, SWT.NONE);
		reconstructionGroup.setLayout(new GridLayout(2, false));
		reconstructionGroup.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, false));
		reconstructionGroup.setText(Messages.VerifiableSecretSharingComposite_reconstruction_title);
	}
	
	private void createDescriptionGroup(Group parent) {
		descriptionGroup = new Group(parent, SWT.NONE);
		descriptionGroup.setLayout(new GridLayout(2, false));
		descriptionGroup.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, true));
		descriptionGroup.setText(Messages.VerifiableSecretSharingComposite_description_title);
	}

}
