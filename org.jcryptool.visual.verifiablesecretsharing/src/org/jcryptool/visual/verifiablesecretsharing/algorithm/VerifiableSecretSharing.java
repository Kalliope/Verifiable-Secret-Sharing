package org.jcryptool.visual.verifiablesecretsharing.algorithm;

public class VerifiableSecretSharing {
	private int[] shares;
	private int[] commitments;
	
	private int calculatePolynom(int[] coefficients, int x, int p){
		int s = coefficients[0];
		int y = s;
		
		for(int i=1; i<coefficients.length; i++){
			y += (coefficients[i] * power(x,i)) % p;
		}
		
		return y;
	}
	
	
	private void calculateShares(int[] coefficients, int p, int n){
		int[] shares = new int[n];
		for(int i=0; i < coefficients.length; i++){
			shares[i] = calculatePolynom(coefficients, i, p);
		}
		setShares(shares);
	}
	
	private void commitment(int g, int[] coefficients, int p){
		
		int[] commitments = new int[coefficients.length];
		
		for(int i=0; i<coefficients.length; i++){
			commitments[i] = (power(g,coefficients[i])) % p;
		}
		
		setCommitments(commitments);
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


	public int power(double x, double j){
		return (int)Math.pow(x,j);
	}
	
}
