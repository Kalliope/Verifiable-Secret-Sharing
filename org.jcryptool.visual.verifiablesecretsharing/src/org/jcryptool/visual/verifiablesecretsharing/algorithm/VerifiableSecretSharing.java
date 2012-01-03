package org.jcryptool.visual.verifiablesecretsharing.algorithm;

public class VerifiableSecretSharing {
	private double[] shares;
	private int[] commitments;
	private int[] sharesModP;
	
	public int[] getSharesModP() {
		return sharesModP;
	}


	public void setSharesModP(int[] sharesModP) {
		this.sharesModP = sharesModP;
	}


	public double[] getShares() {
		return shares;
	}


	public void setShares(double[] shares) {
		this.shares = shares;
	}


	public int[] getCommitments() {
		return commitments;
	}


	public void setCommitments(int[] commitments) {
		this.commitments = commitments;
	}


	private double calculatePolynom(int[] coefficients, int x, int p){
		int s = coefficients[0];
		double y = s;
		
		for(int i=1; i<coefficients.length; i++){
			y += (coefficients[i] * power(x,i));
		}
		
		return y;
	}
	
	
	public void calculateShares(int[] coefficients, int p, int n){
		double[] shares = new double[n];
		int[] sharesModP = new int[n];
		for(int i=0, y =1; i < n; i++,y++){
			shares[i] = calculatePolynom(coefficients, y, p);
			sharesModP[i] = (int)((calculatePolynom(coefficients, y, p)) % p);
		}
		setShares(shares);
		setSharesModP(sharesModP);

	}
	
	public int[] commitment(int g, int[] coefficients, int p){
		
		int[] commitments = new int[coefficients.length];
		
		for(int i=1, y=0; i<coefficients.length; i++,y++){
			commitments[y] = (int)((power(g,coefficients[i])) % p);
		}
		setCommitments(commitments);
		return commitments;
	}
	
	public boolean check(int g, int p, int playerId){
		int[] commitments = getCommitments();
		int[] sharesModP = getSharesModP();
		
		boolean checked = false;
		
		int lValue = (int)((power(g,sharesModP[playerId - 1])) % p);
		int rValue = 1;
		
		for(int j=0; j<commitments.length-1; j++){
			rValue *= (int)(power(power(commitments[j], playerId), j));
			rValue %= p;
		}
		
		if(lValue == rValue){
			checked = true;
		}

		return checked;
		
	}
	
	public void reconstruct(int[] shares, int[] playerID, int t, int p){
		
		for(int i=1;i<=5;i++){
			
		}
		
	}

	private double power(double x, double j){
		return Math.pow(x,j);
	}
	
}
