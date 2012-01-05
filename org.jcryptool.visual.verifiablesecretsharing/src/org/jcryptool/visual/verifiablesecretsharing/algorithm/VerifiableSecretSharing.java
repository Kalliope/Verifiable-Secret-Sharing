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
package org.jcryptool.visual.verifiablesecretsharing.algorithm;

import java.math.BigInteger;

/**
 * Implements the algorithms for the Verifiable Secret Sharing.
 * 
 * @author Dulghier Christoph, Reisinger Kerstin, Tiefenbacher Stefan, Wagner Thomas
 *
 */
public class VerifiableSecretSharing {
	private int[] commitments;
	private double[] shares;
	private int[] sharesModP;
	private BigInteger[] sharesBig;
	private BigInteger[] commitmentsBig;
	
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
		
		BigInteger[] commitmentsBig = new BigInteger[coefficients.length];
		
		for(int i=0; i<coefficients.length; i++){
			commitmentsBig[i] = new BigInteger(g+"").pow(coefficients[i]);
			commitmentsBig[i] = commitmentsBig[i].mod(new BigInteger(p+""));
			commitments[i] = commitmentsBig[i].intValue();
		}
		
		setCommitments(commitments);
		setCommitmentsBig(commitmentsBig);
		return commitments;
	}
	
	public boolean check(int g, int p, int playerId){
		int[] commitments = getCommitments();
		BigInteger[] sharesBig = getSharesBig();
		BigInteger[] commitmentsBig = getCommitmentsBig();
		
		boolean checked = false;
		
		BigInteger lValue;
		if(sharesBig[playerId-1].mod(new BigInteger(p+"")).compareTo(new BigInteger(0+"")) != 0){
			BigInteger help = sharesBig[playerId-1].mod(new BigInteger((p-1)+""));
			lValue = new BigInteger(g+"").modPow(help, new BigInteger(p+""));
		}
		else{
			lValue = new BigInteger(g+"").modPow(sharesBig[playerId-1], new BigInteger(p+""));
		}



		BigInteger rValue = new BigInteger("1");
		
		for(int j=0; j<commitments.length; j++){
			BigInteger help = new BigInteger(playerId+"").pow(j);
			
			if(help.mod(new BigInteger(p+"")).compareTo(new BigInteger(0+"")) != 0){
				help = help.mod(new BigInteger((p-1)+""));
				rValue = rValue.multiply(commitmentsBig[j].modPow(help, new BigInteger(p+"")));
				rValue = rValue.mod(new BigInteger(p+""));
			}
			else{
				rValue = rValue.multiply(commitmentsBig[j].modPow(help, new BigInteger(p+"")));
				rValue = rValue.mod(new BigInteger(p+""));
			}
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


	public BigInteger[] getCommitmentsBig() {
		return commitmentsBig;
	}


	public void setCommitmentsBig(BigInteger[] commitmentsBig) {
		this.commitmentsBig = commitmentsBig;
	}
	
	
}
