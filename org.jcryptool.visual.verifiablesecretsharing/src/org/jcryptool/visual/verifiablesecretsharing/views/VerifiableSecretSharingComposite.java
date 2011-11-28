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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
		Composite inputBody = new Composite(parent, SWT.NONE);
		GridLayout inputBodyLayout = new GridLayout(5, false);
		inputBodyLayout.marginWidth = 0;
		inputBody.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		inputBody.setLayout(inputBodyLayout);

		createParametersGroup(inputBody);
		createCoefficientsGroup(inputBody, 5);
		createCommitmentsGroup(inputBody);
		createSharesGroup(inputBody);
		createReconstructionGroup(inputBody);
	}

	private void createParametersGroup(Composite parent) {
		Group parametersGroup = new Group(parent, SWT.NONE);
		parametersGroup.setLayout(new GridLayout(2, false));
		parametersGroup.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, false));
		parametersGroup.setText(Messages.VerifiableSecretSharingComposite_parameters_title);
		
		Label playerLabel = new Label(parametersGroup, SWT.NONE);
		playerLabel.setText(Messages.VerifiableSecretSharingComposite_parameters_players);
		
		Spinner playerSpinner = new Spinner(parametersGroup, SWT.BORDER);
		playerSpinner.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Label reconstructorLabel = new Label(parametersGroup, SWT.NONE);
		reconstructorLabel.setText(Messages.VerifiableSecretSharingComposite_parameters_reconstructors);
		
		Spinner reconstructorSpinner = new Spinner(parametersGroup, SWT.BORDER);
		reconstructorSpinner.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Label secretLabel = new Label(parametersGroup, SWT.NONE);
		secretLabel.setText(Messages.VerifiableSecretSharingComposite_parameters_secret);
		
		Text secretText = new Text(parametersGroup, SWT.BORDER);
		secretText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Label moduleLabel = new Label(parametersGroup, SWT.NONE);
		moduleLabel.setText(Messages.VerifiableSecretSharingComposite_parameters_primeMod);
		
		Text moduleText = new Text(parametersGroup, SWT.BORDER);
		moduleText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Label primitiveRootLabel = new Label(parametersGroup, SWT.NONE);
		primitiveRootLabel.setText(Messages.VerifiableSecretSharingComposite_parameters_primitiveRoot);
		
		Text primitiveRootText = new Text(parametersGroup, SWT.BORDER);
		primitiveRootText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		Label space1 = new Label(parametersGroup, SWT.NONE);
		Label space2 = new Label(parametersGroup, SWT.NONE);
		
		Label nextStep = new Label(parametersGroup, SWT.NONE);
		nextStep.setText(Messages.VerifiableSecretSharingComposite_nextStep_button);
		Button determineCoefficients = new Button(parametersGroup, SWT.NONE);
		determineCoefficients.setText(Messages.VerifiableSecretSharingComposite_parameters_determineCoefficients);
		determineCoefficients.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));		
	}

	private void createCoefficientsGroup(Composite parent, int coefficients) {
		
		Group coefficientsGroup = new Group(parent, SWT.NONE);
		coefficientsGroup.setLayout(new GridLayout());
		coefficientsGroup.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, false));
		coefficientsGroup.setText(Messages.VerifiableSecretSharingComposite_coefficients_title);
		
		Composite compositeGenerateButton = new Composite(coefficientsGroup, SWT.NONE);
		compositeGenerateButton.setLayout(new GridLayout(2, true));
		compositeGenerateButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		
		Button generateCoefficientsButton = new Button(compositeGenerateButton, SWT.NONE);
		generateCoefficientsButton.setText(Messages.VerifiableSecretSharingComposite_coefficients_generate_button);
		generateCoefficientsButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		ScrolledComposite scrolledCoefficientsGroup = new ScrolledComposite(coefficientsGroup, SWT.V_SCROLL);
		scrolledCoefficientsGroup.setExpandHorizontal(true);
		scrolledCoefficientsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Composite scrolledCoefficientsGroupContent = new Composite(scrolledCoefficientsGroup, SWT.NONE);
		scrolledCoefficientsGroupContent.setLayout(new GridLayout(2, false));
		scrolledCoefficientsGroupContent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		
		Label[] coefficientsLabel = new Label[coefficients];
		Spinner[] coefficientsSpinner = new Spinner[coefficients];
		coefficientsLabel[0] = new Label(scrolledCoefficientsGroupContent, SWT.NONE);
		coefficientsLabel[0].setText("Y0 = s");
		coefficientsSpinner[0] = new Spinner(scrolledCoefficientsGroupContent, SWT.BORDER);
		coefficientsSpinner[0].setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, false));
		coefficientsSpinner[0].setEnabled(false);
		for (int i=1; i<coefficients; i++) {
			coefficientsLabel[i] = new Label(scrolledCoefficientsGroupContent, SWT.NONE);
			coefficientsLabel[i].setText("Y"+i);
			
			coefficientsSpinner[i] = new Spinner(scrolledCoefficientsGroupContent, SWT.BORDER);
			coefficientsSpinner[i].setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, false));
		}
		
		scrolledCoefficientsGroup.setContent(scrolledCoefficientsGroupContent);
		scrolledCoefficientsGroupContent.pack();
		

		Composite compositeCommitButton = new Composite(coefficientsGroup, SWT.NONE);
		compositeCommitButton.setLayout(new GridLayout(2, true));
		compositeCommitButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		Button commitCoefficientsButton = new Button(compositeCommitButton, SWT.NONE);
		commitCoefficientsButton.setText(Messages.VerifiableSecretSharingComposite_coefficients_commit_button);
		commitCoefficientsButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
	}

	private void createCommitmentsGroup(Composite parent) {
		Group commitmentsGroup = new Group(parent, SWT.NONE);
		commitmentsGroup.setLayout(new GridLayout(2, false));
		commitmentsGroup.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, false));
		commitmentsGroup.setText(Messages.VerifiableSecretSharingComposite_commitments_title);
	}

	private void createSharesGroup(Composite parent) {
		Group sharesGroup = new Group(parent, SWT.NONE);
		sharesGroup.setLayout(new GridLayout(2, false));
		sharesGroup.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, false));
		sharesGroup.setText(Messages.VerifiableSecretSharingComposite_shares_title);
	}

	private void createReconstructionGroup(Composite parent) {
		Group reconstructionGroup = new Group(parent, SWT.NONE);
		reconstructionGroup.setLayout(new GridLayout(2, false));
		reconstructionGroup.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, false));
		reconstructionGroup.setText(Messages.VerifiableSecretSharingComposite_reconstruction_title);
	}
	
	private void createDescriptionGroup(Group parent) {
		Group descriptionGroup = new Group(parent, SWT.NONE);
		descriptionGroup.setLayout(new GridLayout(2, false));
		descriptionGroup.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, true));
		descriptionGroup.setText(Messages.VerifiableSecretSharingComposite_description_title);
	}

}
