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
		if (leistung > 0)
			cnt++;
		if (spannung > 0)
			cnt++;
		if (strom > 0)
			cnt++;
		if (widerstand > 0)
			cnt++;

		if (cnt < 2)
			warning = "nicht genug Werte";
		else if (cnt > 2)
			warning = "zu viele Werte eingegeben";
		else
			warning = "";

		if (leistung == -1) {
			warning = "Leisung nicht korrekt abgefüllt";
			leistung = 0;
		}
		if (spannung == -1) {
			warning = "Spannung nicht korrekt abgefüllt";
			spannung = 0;
		}
		if (strom == -1) {
			warning = "Strom nicht korrekt abgefüllt";
			strom = 0;
		}
		if (widerstand == -1) {
			warning = "Wiederstand nicht korrekt abgefüllt";
			widerstand = 0;
		}

		if (warning.equals("")) {
			this.leistung = leistung;
			this.spannung = spannung;
			this.strom = strom;
			this.widerstand = widerstand;
		}
	}

	public Calculator(String leistung, String spannung, String strom, String wiederstand) {
		this(parseDouble(leistung), parseDouble(spannung), parseDouble(strom), parseDouble(wiederstand));
	}

	private static double parseDouble(String s) {
		char[] in = s.toCharArray();
		double out = 0;
		int cntPoint = 0;
		if (s.length() > 0) {
			boolean isNummeric = Character.isDigit(in[0]) || in[0] == '-';

			for (int i = 1; i < in.length; i++) {
				if (!(in[i] == '.' && cntPoint == 0)) // for the snd point it isn't ok anymore
					isNummeric = isNummeric && Character.isDigit(in[i]);
				else
					cntPoint++;
			}
			if (isNummeric) // the String s is a valid double.
				out = Double.parseDouble(s);
			else
				out = -1;
		}
		return out;
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
			System.out.println(
					"Strom (" + strom + ") wird aus Leistung " + leistung + " & Spannung " + spannung + " berechnet.");
			widerstand = rAusPundI(leistung, spannung);
			System.out.println("Widerstand (" + widerstand + ") wird aus Leistung " + leistung + " & Spannung "
					+ spannung + " berechnet.");
			System.out.println("______________________________________");
			strom *= -1;
			widerstand *= -1;
		} else if (leistung != 0 && strom != 0) {
			spannung = uAusPundI(leistung, strom);
			System.out.println(
					"Spannung (" + spannung + ") wird aus Leistung " + leistung + " & Strom " + strom + " berechnet.");
			widerstand = rAusPundI(leistung, strom);
			System.out.println("Widerstand (" + widerstand + ") wird aus Leistung " + leistung + " & Strom " + strom
					+ " berechnet.");
			System.out.println("______________________________________");
			spannung *= -1;
			widerstand *= -1;
		} else if (leistung != 0 && widerstand != 0) {
			spannung = uAusPundR(leistung, widerstand);
			System.out.println("Spannung (" + spannung + ") wird aus Leistung " + leistung + " & Widerstand "
					+ widerstand + " berechnet.");
			strom = iAusPundR(leistung, widerstand);
			System.out.println("Strom (" + strom + ") wird aus Leistung " + leistung + " & Widerstand " + widerstand
					+ " berechnet.");
			System.out.println("______________________________________");
			spannung *= -1;
			strom *= -1;
		} else if (spannung != 0 && strom != 0) {
			leistung = pAusUundI(spannung, strom);
			System.out.println(
					"Leistung (" + leistung + ") wird aus Spannung " + spannung + " & Strom " + strom + " berechnet.");
			widerstand = rAusUundI(spannung, strom);
			System.out.println("Widerstand (" + widerstand + ") wird aus Spannung " + spannung + " & Strom " + strom
					+ " berechnet.");
			System.out.println("______________________________________");
			leistung *= -1;
			widerstand *= -1;
		} else if (spannung != 0 && widerstand != 0) {
			leistung = pAusUundR(spannung, widerstand);
			System.out.println("Leistung (" + leistung + ") wird aus Spannung " + spannung + " & Widerstand "
					+ widerstand + " berechnet.");
			strom = iAusUundR(spannung, widerstand);
			System.out.println("Strom (" + strom + ") wird aus Spannung " + spannung + " & Widerstand " + widerstand
					+ " berechnet.");
			System.out.println("______________________________________");
			leistung *= -1;
			strom *= -1;
		} else if (widerstand != 0 && strom != 0) {
			leistung = pAusRundI(widerstand, strom);
			System.out.println("Leistung (" + leistung + ") wird aus Widerstand " + widerstand + " & Strom " + strom
					+ " berechnet.");
			spannung = uAusRundI(widerstand, strom);
			System.out.println("Spannung (" + spannung + ") wird aus Widerstand " + widerstand + " & Strom " + strom
					+ " berechnet.");
			System.out.println("______________________________________");
			leistung *= -1;
			spannung *= -1;
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
		return u / i;
	}

	public double rAusPundI(double p, double i) {
		return p / (i * i);
	}

	public double rAusPundU(double p, double u) {
		return (u * u) / p;
	}

	public String getWarning() {
		return warning;
	}

}
