/**
 * This class represents a single Option.
 * 
 * @author kevinbarbian
 *
 */

public class Option {
	public static void main(String[] args) {
		Option one = new Option(27.07,20,.05,.46,30);
		System.out.println(one.valuateCallOption());
		System.out.println(one.valuatePutOption());
		System.out.println(one.getCallDelta());
		System.out.println(one.getPutDelta());
		System.out.println(one.getPutRho());

	}

	private double S;
	private double K;
	private double r;
	private double v;
	private double tau;
	private double dPF; // discounted probability factor
	private double ePF; // exercise probability factor

	public Option(double S, double K, double r, double v, double tau) {
		this.S = S;
		this.K = K;
		this.r = r;
		this.v = v;
		tau = tau/365;
		this.tau = tau;
		this.dPF = (Math.log(S / K) + ((r + (v * v) / 2)) * tau)
				/ (v * Math.sqrt(tau));
		this.ePF = dPF - (v * Math.sqrt(tau));

	}

	public double getS() {
		return S;
	}

	public double getK() {
		return K;
	}

	public double getRate() {
		return r;
	}

	public double getv() {
		return v;
	}

	public double gettau() {
		return tau;
	}

	public double getDPF() {
		return dPF;
	}

	public double getEPF() {
		return ePF;
	}

	public double valuatePutOption() {
		return (getK() * Math.exp(-getRate() * gettau()) * CNDF(-getEPF()))
				- (getS() * CNDF(-getDPF()));
	}

	public double valuateCallOption() {
		return (S * CNDF(dPF)) - (K * Math.exp(-r * tau)) * CNDF(ePF);
	}

	public double getCallDelta() {
		return CNDF(dPF);
	}

	public double getPutDelta() {
		return getCallDelta() - 1;
	}

	public double getCallTheta() {
		return ((-S * v * PNDF(dPF) * 0.5 * Math.sqrt(tau))
				- (r * K * Math.exp(-r * tau) * CNDF(ePF))) / 365;
	}

	public double getPutTheta() {
		return ((-S * v * PNDF(dPF) * 0.5 * Math.sqrt(tau))
				+ (r * K * Math.exp(-r * tau) * CNDF(-ePF))) / 365;
	}

	public double getCallRho() {
		return K * tau * Math.exp(-r * tau) * CNDF(ePF) / 100;
	}

	public double getPutRho() {
		return -K * tau * Math.exp(-r * tau) * CNDF(-ePF) / 100;
	}

	public double getGamma() {
		return PNDF(dPF) / (S * v * Math.sqrt(tau));
	}

	public double PNDF(double x) {
		return (1 / Math.sqrt(2 * Math.PI)) * Math.exp(-(x * x) / 2);
	}

	public double CNDF(double x) {
		int neg = (x < 0d) ? 1 : 0;
		if (neg == 1)
			x *= -1d;

		double k = (1d / (1d + 0.2316419 * x));
		double y = ((((1.330274429 * k - 1.821255978) * k + 1.781477937) * k - 0.356563782) * k + 0.319381530) * k;
		y = 1.0 - 0.398942280401 * Math.exp(-0.5 * x * x) * y;

		return (1d - neg) * y + neg * (1d - y);
	}

}