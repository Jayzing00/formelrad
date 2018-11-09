package application;

/**
 * Berechnet das Formelrad
 *
 * @version 05.10.2018
 */
public class Calculator {
	private double leistung;
	private double spannung;
	private double strom;
	private double widerstand;
	private String warning;

	public Calculator(double leistung, double spannung, double strom, double widerstand) {
		super();
		int cnt = 0;
		if (leistung != 0)
			cnt++;
		if (spannung != 0)
			cnt++;
		if (strom != 0)
			cnt++;
		if (widerstand != 0)
			cnt++;
		if (cnt<2)
			warning = "nicht genug Werte";
		else if (cnt>2)
			warning = "zu viele Werte eingegeben";
		else
			warning = "";

		if (warning.equals("")) {
		this.leistung = leistung;
		this.spannung = spannung;
		this.strom = strom;
		this.widerstand = widerstand;
		}
	}

	public double getLeistung() {
		return leistung;
	}

	public double getSpannung() {
		return spannung;
	}

	public double getStrom() {
		return strom;
	}

	public double getWiderstand() {
		return widerstand;
	}

//	@Override
//	public String toString() {
//		return "Calculator [leistung=" + leistung + ", spannung=" + spannung + ", strom=" + strom + ", widerstand="
//				+ widerstand + "]";
//	}

	public void calculate() {
		if (leistung != 0 && spannung != 0) {
			strom = iAusPundU(leistung, spannung);
			System.out.println("Strom (" + strom + ") wird aus Leistung " + leistung + " & Spannung " + spannung + " berechnet.");
			widerstand = rAusPundI(leistung, spannung);
			System.out.println("Widerstand (" + widerstand + ") wird aus Leistung " + leistung + " & Spannung " + spannung + " berechnet.");
			System.out.println("______________________________________");
		}else
		if (leistung != 0 && strom != 0) {
			spannung = uAusPundI(leistung, strom);
			System.out.println("Spannung (" + spannung + ") wird aus Leistung " + leistung + " & Strom " + strom + " berechnet.");
			widerstand = rAusPundI(leistung, strom);
			System.out.println("Widerstand (" + widerstand + ") wird aus Leistung " + leistung + " & Strom " + strom + " berechnet.");
			System.out.println("______________________________________");
		}else
		if (leistung != 0 && widerstand != 0) {
			spannung = uAusPundR(leistung, widerstand);
			strom = iAusPundR(leistung, widerstand);
		}else
		if (spannung != 0 && strom != 0) {
			leistung = pAusUundI(spannung, strom);
			widerstand = rAusUundI(spannung, strom);
		}else
		if (spannung != 0 && widerstand != 0) {
			leistung = pAusUundR(spannung, widerstand);
			strom = iAusUundR(spannung, widerstand);
		}else
		if (widerstand != 0 && strom != 0) {
			leistung = pAusRundI(widerstand, strom);
			spannung = uAusRundI(widerstand, strom);
		}

	}

	public double pAusUundI(double u, double i) {
		return u * i;
	}

	public double pAusRundI(double r, double i) {
		return r * Math.pow(i, 2);
	}

	public double pAusUundR(double u, double r) {
		return Math.pow(u, 2) / r;
	}

	public double uAusRundI(double r, double i) {
		return r * i;
	}

	public double uAusPundI(double p, double i) {
		return p / i;
	}

	public double uAusPundR(double p, double r) {
		return Math.sqrt(p * r);
	}

	public double iAusPundR(double p, double r) {
		return Math.sqrt(p / r);
	}

	public double iAusPundU(double p, double u) {
		return p / u;
	}

	public double iAusUundR(double u, double r) {
		return u / r;
	}

	public double rAusUundI(double u, double i) {
		return u/i;
	}

	public double rAusPundI(double p, double i) {
		return p/(i*i);
	}
	public double rAusPundU(double p, double u) {
		return (u*u)/p;
	}

	public String getWarning() {
		return warning;
	}

}
