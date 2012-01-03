package org.jcryptool.visual.verifiablesecretsharing.algorithm;

/**
 * Implements the algorithms for the Verifiable Secret Sharing.
 * 
 * @author Dulghier Christoph, Reisinger Kerstin, Tiefenbacher Stefan, Wagner Thomas
 *
 */
public class VerifiableSecretSharing {
	
	/*claculated shares*/
	private int[] shares;
	/*calculated commitments*/
	private int[] commitments;
	/*calculated shares modulo prime p*/
	private int[] sharesModP;
	
	/**
	 * Calculates the function f(x) = s + ax + bx^2 + ... + ix^i
	 * y is the result for the chosen x. 
	 * @param coefficients --> a, b, ..., i
	 * @param x --> player Id
	 * @param p --> prime
	 * @return y
	 */
	private int calculatePolynom(int[] coefficients, int x, int p){
		int s = coefficients[0];
		int y = s;
		
		for(int i=1; i<coefficients.length; i++){
			y += (coefficients[i] * power(x,i));
		}
		
		return y;
	}
	
	/**
	 * Calculates the shares with and without modulo p.
	 * 
	 * @param coefficients --> a, b, ..., i
	 * @param p --> prime
	 * @param n --> number of player
	 * @return allShares --> contains one array with the shares 
	 * and one array with the shares modulo p
	 */
	public int[][] calculateShares(int[] coefficients, int p, int n){
		int[] shares = new int[n];
		int[] sharesModP = new int[n];
		int[][] allShares = new int[2][n];
		
		for(int i=0; i < n; i++){
			shares[i] = calculatePolynom(coefficients, i, p);
			sharesModP[i] = (calculatePolynom(coefficients, i, p)) % p;
		}
		
		setShares(shares);
		setSharesModP(sharesModP);
		allShares[0] = shares;
		allShares[1] = sharesModP;
		return allShares;
	}
	
	/**
	 * Calculates the commitments for the coefficients
	 * (including the secret s).
	 * @param g --> primitive root
	 * @param coefficients --> a, b, ..., i
	 * @param p --> prime
	 * @return commitments
	 */
	public int[] commitment(int g, int[] coefficients, int p){
		
		int[] commitments = new int[coefficients.length];
		
		for(int i=0; i<coefficients.length; i++){
			commitments[i] = (power(g,coefficients[i])) % p;
		}
		setCommitments(commitments);
		return commitments;
	}
	
	/**
	 * Checks if the share is correct.
	 * @param g --> primitive root
	 * @param p --> prime
	 * @param playerId --> player Id
	 * @return true --> check OK; false --> check not OK;
	 */
	public boolean check(int g, int p, int playerId){
		int[] commitments = getCommitments();
		int[] sharesModP = getSharesModP();
		
		boolean checked = false;
		
		int lValue = (power(g,sharesModP[playerId - 1])) % p;
		int rValue = 1;
		
		for(int j=0; j<commitments.length; j++){
			rValue *= (power(power(commitments[j], playerId), j)) % p;
		}
		
		if(lValue == rValue){
			checked = true;
		}

		return checked;	
	}

	public int[] getSharesModP() {
		return sharesModP;
	}


	public void setSharesModP(int[] sharesModP) {
		this.sharesModP = sharesModP;
	}


	public int[] getShares() {
		return shares;
	}


	public void setShares(int[] shares) {
		this.shares = shares;
	}


	public int[] getCommitments() {
		return commitments;
	}


	public void setCommitments(int[] commitments) {
		this.commitments = commitments;
	}
	
	private int power(double x, double j){
		return (int)Math.pow(x,j);
	}
	
}
