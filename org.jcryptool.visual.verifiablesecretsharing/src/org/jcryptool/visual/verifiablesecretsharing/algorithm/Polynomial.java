package org.jcryptool.visual.verifiablesecretsharing.algorithm;

public class Polynomial {
	int deg;
	private int[] coef;

	public int[] getCoef() {
		return coef;
	}

	// coef[i]*x^i
	public Polynomial(int[] coef) {
		this.coef = coef;
		deg = degree();
	}

	public int degree() {
		int d = 0;
		for (int i = 0; i < coef.length; i++)
			if (coef[i] != 0)
				d = i;
		return d;
	}

	public Polynomial times(Polynomial b) {
		Polynomial a = this;
		Polynomial c = new Polynomial(new int[a.deg + b.deg + 1]);
		for (int i = 0; i <= a.deg; i++) {
			for (int j = 0; j <= b.deg; j++) {
				c.coef[i + j] += (a.coef[i] * b.coef[j]);
			}
		}
		c.deg = c.degree();
		return c;
	}

	public Polynomial times(int b) {
		Polynomial a = this;
		Polynomial c = new Polynomial(new int[a.coef.length]);
		for (int i = 0; i < c.coef.length; i++) {
			c.coef[i] = (a.coef[i] * b);
		}
		c.deg = c.degree();
		return c;

	}

	public Polynomial add(Polynomial b) {
		Polynomial a = this;
		Polynomial c = new Polynomial(new int[(Math.max(a.coef.length,
				b.coef.length))]);

		for (int i = 0; i < a.coef.length; i++)
			c.coef[i] += a.coef[i];
		for (int i = 0; i < b.coef.length; i++)
			c.coef[i] += b.coef[i];
		c.deg = c.degree();
		return c;
	}

	public Polynomial add(int b) {
		Polynomial a = this;
		Polynomial c = new Polynomial(new int[a.coef.length]);
		for (int i = 0; i < a.coef.length; i++)
			c.coef[i] = a.coef[i];
		c.coef[0] += b;
		c.deg = c.degree();
		return c;
	}

	public Polynomial mod(int p) {
		Polynomial a = this;
		Polynomial c = new Polynomial(new int[a.coef.length]);
		for (int i = 0; i < a.coef.length; i++) {
			if (a.coef[i] > 0) {
				c.coef[i] = a.coef[i] % p;
			} else {
				c.coef[i] = p + a.coef[i];
			}
		}
		c.deg = c.degree();
		return c;
	}

	public String toString() {
		if (deg == 0)
			return "" + coef[0];
		if (deg == 1)
			return coef[0] + " + " + coef[1] + "x";
		String s = ""; // coef[deg] + "x" + convertIntegerToSuperscript(deg);
		for (int i = 0; i <= deg; i++) {
			if (coef[i] == 0)
				continue;
			else if (coef[i] > 0)
				s = s + " + " + (coef[i]);
			else if (coef[i] < 0)
				s = s + " - " + (-coef[i]);
			if (i == 1)
				s = s + "x";
			else if (i > 1)
				s = s + "x" + convertIntegerToSuperscript(i);
		}
		return s.substring(3, s.length());
	}
	
	private String convertIntegerToSuperscript(int number) {
		String result = "";
		String numberString = number + "";
		for (int i = 0; i < numberString.length(); i++) {
			switch (numberString.charAt(i)) {
			case '0':
				result += "\u2070";
				break;
			case '1':
				result += "\u00B9";
				break;
			case '2':
				result += "\u00B2";
				break;
			case '3':
				result += "\u00B3";
				break;
			case '4':
				result += "\u2074";
				break;
			case '5':
				result += "\u2075";
				break;
			case '6':
				result += "\u2076";
				break;
			case '7':
				result += "\u2077";
				break;
			case '8':
				result += "\u2078";
				break;
			case '9':
				result += "\u2079";
				break;
			default:
				result += "";
			}
		}
		return result;
	}
}
