package org.jcryptool.visual.verifiablesecretsharing.algorithm;

public class VerifiableSecretSharing {
	public int[] shares;
	private int calculatePolynom(int[] coefficients, int x, int p){
		int s = coefficients[0];
		int y = s;
		
		for(int i=1; i<coefficients.length; i++){
			y += (coefficients[i] * power(x,i)) % p;
		}
		
		return y;
	}
	
	
	public void calculateShares(int[] coefficients, int p, int n){
		int[] shares = new int[n];
		for(int i=0; i < coefficients.length; i++){
			shares[i] = calculatePolynom(coefficients, i, p);
		}
		this.shares = shares;
	}
	
	public int power(double x, double j){
		return (int)Math.pow(x,j);
	}
	
}
