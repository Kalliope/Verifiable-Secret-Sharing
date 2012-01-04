package org.jcryptool.visual.verifiablesecretsharing.algorithm;

import java.math.BigInteger;

public class VerifiableSecretSharing {
	private double[] shares;
	private int[] commitments;
	private int[] sharesModP;
	private BigInteger[] sharesBig;
	
	private BigInteger calculatePolynom(int[] coefficients, int x, int p){	
		BigInteger y = new BigInteger(coefficients[0]+"");
		
		for(int i=1; i<coefficients.length;i++){
			y = y.add(new BigInteger(coefficients[i]+"").multiply(new BigInteger(x+"").pow(i)));
		}
		return y;
	}
	
	
	public void calculateShares(int[] coefficients, int p, int n){
		double[] shares = new double[n];
		BigInteger[] sharesBig = new BigInteger[n];
		int[] sharesModP = new int[n];
		
		for(int i=0, y =1; i < n; i++,y++){
			sharesBig[i] = calculatePolynom(coefficients,y,p);
			shares[i] = sharesBig[i].doubleValue();
			sharesModP[i] = (sharesBig[i].mod(new BigInteger(p+""))).intValue();
		}
		setShares(shares);
		setSharesModP(sharesModP);
		setSharesBig(sharesBig);

	}
	
	public int[] commitment(int g, int[] coefficients, int p){
		
		int[] commitments = new int[coefficients.length];
		
		for(int i=0; i<coefficients.length; i++){
			commitments[i] = (int)((power(g,coefficients[i])) % p);
		}
		setCommitments(commitments);
		return commitments;
	}
	
	public boolean check(int g, int p, int playerId){
		int[] commitments = getCommitments();
		BigInteger[] sharesBig = getSharesBig();
		
		boolean checked = false;

		BigInteger lValue = new BigInteger(g+"").modPow(sharesBig[playerId-1], new BigInteger(p+""));

		BigInteger rValue = new BigInteger("1");
		
		for(int j=0; j<commitments.length; j++){
			int help = (int)power(playerId,j);
			rValue = rValue.multiply(new BigInteger(commitments[j]+"").pow(help));
			rValue = rValue.mod(new BigInteger(p+""));
		}
		
		if(lValue.compareTo(rValue) == 0){
			checked = true;
		}

		return checked;	
	}
	
//	public int reconstruct(double[] shares, int[] playerID, int t, int p){
//		
//		int help1=0;
//		
//		
//		for(int i=0;i<t;i++){
//			int help2=1;	
//			
//				for(int j=0;j<t;j++){
//					if(j==i) continue;
//				
//				help2 *=(x-playerID[j])/playerID[i]-playerID[j];
//				}
//			help1 += help2;
//			
//		}
//		
//	return help1;
//		
//	}

	private double power(double x, double j){
		return Math.pow(x,j);
	}
	
	public BigInteger[] getSharesBig() {
		return sharesBig;
	}


	public void setSharesBig(BigInteger[] sharesBig) {
		this.sharesBig = sharesBig;
	}
	
	public void setSharesBig(int i, BigInteger x){
		this.sharesBig[i] = x;
	}


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
}
