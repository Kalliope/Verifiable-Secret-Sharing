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
	
	/*Commitments for the output*/
	private int[] commitments;
	/*Shares for the output without modulo prime p*/
	private double[] shares;
	/*Shares for the output with modulo prime p*/
	private int[] sharesModP;
	/*Shares for the calculation*/
	private BigInteger[] sharesBig;
	/*Commitments for the calculation*/
	private BigInteger[] commitmentsBig;
	
	/**
	 * Calculates the function f(x) = s + ax + bx^2 + ... + ix^i.
	 * 
	 * @param coefficients --> a, b, ..., i
	 * @param x --> playerId
	 * @param p --> prime module
	 * @return y --> result of the function, for the chosen x
	 */
	private BigInteger calculatePolynom(int[] coefficients, int x, int p){	
		BigInteger y = new BigInteger(coefficients[0]+"");
		
		for(int i=1; i<coefficients.length;i++){
			y = y.add(new BigInteger(coefficients[i]+"").multiply(new BigInteger(x+"").pow(i)));
		}
		return y;
	}
	
	/**
	 * Calculates the shares for all players.
	 * Sets the global arrays:
	 * double[] shares, int[] sharesModP and BigInteger[] sharesBig.
	 * 
	 * @param coefficients --> a, b, ..., i
	 * @param p --> prime module
	 * @param n --> number of players
	 */
	public void calculateShares(int[] coefficients, int p, int n){
		/*
		 * shares is needed for the output without modulo prime p.
		 * sharesModP is for the output with modulo prime p.
		 * sharesBig is needed for the calculation .
		 * (BigInteger is used, because of the maxValue of double and int)
		 */
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
	
	/**
	 * Calculates the commitments.
	 * Sets the global arrays:
	 * int[] commitments and BigInteger[] commitmentsBig.
	 * 
	 * @param g --> primitive root of p
	 * @param coefficients --> a, b, ..., i
	 * @param p --> prime module
	 * @return commitments --> array including all commitments
	 */
	public int[] commitment(int g, int[] coefficients, int p){
		/*commitments is needed for the output.
		 *commitmentsBig is needed for the calculation.
		 */
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
	
	/**
	 * Calculates the check and compares: 
	 *                  
	 *             (t-1)__
	 * g^Share[i] ?=    || g^(PlayerId^j)
	 *                (j=0) 
	 *                
	 * @param g --> primitive root of p
	 * @param p --> prime
	 * @param playerId
	 * @return true --> check OK; 
	 * 		   false --> check not OK;
	 */
	public boolean check(int g, int p, int playerId){
		BigInteger[] sharesBig = getSharesBig();
		BigInteger[] commitmentsBig = getCommitmentsBig();
		
		boolean checked = false;
		
		BigInteger lValue;
		
		/*Calculates the left Value of the formula.
		 *Reduces the exponent if the exponent and p are relatively prime.
		 */
		if(sharesBig[playerId-1].mod(new BigInteger(p+"")).compareTo(new BigInteger(0+"")) != 0){
			BigInteger help = sharesBig[playerId-1].mod(new BigInteger((p-1)+""));
			lValue = new BigInteger(g+"").modPow(help, new BigInteger(p+""));
		}
		else{
			lValue = new BigInteger(g+"").modPow(sharesBig[playerId-1], new BigInteger(p+""));
		}

		BigInteger rValue = new BigInteger("1");
		
		/*Calculates the left Value of the formula.
		 *Reduces the exponent if the exponent and p are relatively prime.
		 */
		for(int j=0; j<commitmentsBig.length; j++){
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
	
	public String reconstruct(int[] playerIds, int p, int t){
		//BigInteger[] commitmentsBig = getCommitmentsBig();
		int[] sharesModP = getSharesModP();
		//int t = commitmentsBig.length;
		int[] helpCoef = {0,1};
		Polynomial x = new Polynomial(helpCoef);
		
		Polynomial resMul = new Polynomial(new int[]{1});
		Polynomial resAdd = new Polynomial(new int[]{0});
		int inverse;
		
		if(playerIds.length >= t){
			
			for(int i=0; i<t; i++){
				for(int j=0; j<t-1; j++){
					if(j!=i){
						helpCoef[0] = playerIds[j];
						x = new Polynomial(helpCoef).mod(p);
						inverse = new BigInteger(((playerIds[i])-(playerIds[j]))+"").modInverse(new BigInteger(p+"")).intValue();
						x=x.times(inverse).mod(p);
						resMul = resMul.times(x);
						resMul = resMul.mod(p);
					}
				}
				//resMul = resMul.times(commitmentsBig[i].intValue());
				resMul = resMul.times(sharesModP[playerIds[i]-1]).mod(p);
				resAdd = resAdd.add(resMul).mod(p);
				resMul = new Polynomial(new int[]{1});
			}
			return resAdd.toString();
		}
				
		
		return "false";
	}
	
	/**
	 * Getter for sharesBig.
	 * @return  sharesBig --> BigInteger array including all shares without modulo p
	 */
	public BigInteger[] getSharesBig() {
		return sharesBig;
	}

	/**
	 * Setter for sharesBig.
	 * @param sharesBig --> BigInteger array including all shares without modulo p
	 */
	public void setSharesBig(BigInteger[] sharesBig) {
		this.sharesBig = sharesBig;
	}
	
	/**
	 * Setter resetting an element in sharesBig.
	 * @param i --> index
	 * @param x --> value of the element
	 */
	public void setSharesBig(int i, BigInteger x){
		this.sharesBig[i] = x;
	}

	/**
	 * Getter for sharesModP.
	 * @return  sharesModP --> int array including all shares with modulo p
	 */
	public int[] getSharesModP() {
		return sharesModP;
	}

	/**
	 * Setter for sharesModP.
	 * @param sharesModP --> int array including all shares with modulo p
	 */
	public void setSharesModP(int[] sharesModP) {
		this.sharesModP = sharesModP;
	}
	
	/**
	 * Setter resetting an element in sharesModP.
	 * @param i --> index
	 * @param x --> value of the element
	 */
	public void setSharesModP(int i, int x){
		this.sharesModP[i] = x;
	}

	/**
	 * Getter for shares.
	 * @return  shares --> int array including all shares without modulo p
	 */
	public double[] getShares() {
		return shares;
	}

	/**
	 * Setter for shares.
	 * @param shares --> int array including all shares without modulo p
	 */
	public void setShares(double[] shares) {
		this.shares = shares;
	}

	/**
	 * Getter for commitments.
	 * @return  commitments --> int array including all commitments
	 */
	public int[] getCommitments() {
		return commitments;
	}

	/**
	 * Setter for commitments.
	 * @param  commitments --> int array including all commitments
	 */
	public void setCommitments(int[] commitments) {
		this.commitments = commitments;
	}

	/**
	 * Getter for commitmentsBig.
	 * @return  commitmentsBig --> BigInteger array including all commitments
	 */
	public BigInteger[] getCommitmentsBig() {
		return commitmentsBig;
	}

	/**
	 * Setter for commitmentsBig.
	 * @param  commitmentsBig --> BigInteger array including all commitments
	 */
	public void setCommitmentsBig(BigInteger[] commitmentsBig) {
		this.commitmentsBig = commitmentsBig;
	}
}
