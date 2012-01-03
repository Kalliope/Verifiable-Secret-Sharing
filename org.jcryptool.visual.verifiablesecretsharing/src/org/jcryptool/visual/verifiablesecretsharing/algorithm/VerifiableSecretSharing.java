package org.jcryptool.visual.verifiablesecretsharing.algorithm;

public class VerifiableSecretSharing {
	private int[] shares;
	private int[] commitments;
	private int[] sharesModP;
	
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


	private int calculatePolynom(int[] coefficients, int x, int p){
		int s = coefficients[0];
		int y = s;
		
		for(int i=1; i<coefficients.length; i++){
			y += (coefficients[i] * power(x,i));
		}
		
		return y;
	}
	
	
	public int[][] calculateShares(int[] coefficients, int p, int n){
		int[] shares = new int[n];
		int[] sharesModP = new int[n];
		int[][] allShares = new int[2][n];
		for(int i=0, y =1; i < n; i++,y++){
			shares[i] = calculatePolynom(coefficients, y, p);
			sharesModP[i] = (calculatePolynom(coefficients, y, p)) % p;
		}
		setShares(shares);
		setSharesModP(sharesModP);
		allShares[0] = shares;
		allShares[1] = sharesModP;
		return allShares;
	}
	
	public int[] commitment(int g, int[] coefficients, int p){
		
		int[] commitments = new int[coefficients.length];
		
		for(int i=1, y=0; i<coefficients.length; i++,y++){
			commitments[y] = (power(g,coefficients[i])) % p;
		}
		setCommitments(commitments);
		return commitments;
	}
	
	public boolean check(int g, int p, int playerId){
		int[] commitments = getCommitments();
		int[] shares = getShares();
		
		boolean checked = false;
		
		int lValue = (power(g,shares[playerId - 1])) % p;
		int rValue = 1;
		
		for(int j=0; j<commitments.length; j++){
			rValue *= (power(power(commitments[j], playerId), j)) % p;
		}
		
		if(lValue == rValue){
			checked = true;
		}

		return checked;
		
	}
	
	public void reconstruct(int[] shares, int[] playerID, int t){
		
		for(int i=1;i<=5;i++){
			
			
			
		}
		
	}

	private int power(double x, double j){
		return (int)Math.pow(x,j);
	}
	
}
