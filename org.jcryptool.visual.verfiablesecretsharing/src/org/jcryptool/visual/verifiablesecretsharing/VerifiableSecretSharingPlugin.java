package org.jcryptool.visual.verifiablesecretsharing;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class VerifiableSecretSharingPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.jcryptool.visual.verifiablesecretsharing"; //$NON-NLS-1$

	// The shared instance
	private static VerifiableSecretSharingPlugin plugin;
	
	/**
	 * The constructor
	 */
	public VerifiableSecretSharingPlugin() {
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static VerifiableSecretSharingPlugin getDefault() {
		return plugin;
	}
}
