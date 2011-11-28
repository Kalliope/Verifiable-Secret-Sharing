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


import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.ui.*;
import org.eclipse.swt.SWT;
import org.jcryptool.visual.verifiablesecretsharing.VerifiableSecretSharingPlugin;


/**
 * This class ...
 *
 * @author Dulghier Christoph, Reisinger Kerstin, Tiefenbacher Stefan, Wagner Thomas
 */

public class VerifiableSecretSharingView extends ViewPart {

	private Composite parent;

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(final Composite parent) {
		this.parent = parent;
		final ScrolledComposite sc = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		VerifiableSecretSharingComposite vssc = new VerifiableSecretSharingComposite(sc, SWT.NONE, this);
		sc.setContent(vssc);
		sc.setMinSize(vssc.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, VerifiableSecretSharingPlugin.PLUGIN_ID + ".view"); //$NON-NLS-1$
		/*text.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				//do nothing
			}

			@Override
			public void focusLost(FocusEvent e) {
				text2.setText("Happens on focusLost");
			}
			
		});*/
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
}