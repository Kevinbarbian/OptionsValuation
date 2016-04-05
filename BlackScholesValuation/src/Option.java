/**
 * This class represents a single Option.
 * 
 * @author kevinbarbian
 *
 */
public class Option {

	private double stockPrice;
	private double strikePrice;
	private double riskFreeRate;
	private double volatility;
	private double years;
	double discountedProbabilityFactor = (Math.log(stockPrice / strikePrice) + ((riskFreeRate + ((volatility * volatility) / 2)) * years))
			/ (volatility * Math.sqrt(years));
	
	double exerciseProbabilityFactor = discountedProbabilityFactor - (volatility * Math.sqrt(years));
	
	public Option(double stockPrice, double strikePrice, double riskFreeRate, double volatility, double days){
		this.stockPrice = stockPrice;
		this.strikePrice = strikePrice;
		this.riskFreeRate = riskFreeRate;
		this.volatility = volatility;
		this.years = days/365;
	}
	public double valuateCallOption() {
		double discountedProbabilityFactor = (Math.log(stockPrice / strikePrice) + ((riskFreeRate + ((volatility * volatility) / 2)) * years))
				/ (volatility * Math.sqrt(years));
		
		double exerciseProbabilityFactor = discountedProbabilityFactor - (volatility * Math.sqrt(years));
		return (stockPrice * CNDF(discountedProbabilityFactor))
				- (strikePrice * Math.exp(-riskFreeRate * years)) * CNDF(exerciseProbabilityFactor);
	}
	// Calculate put option value based on Black-Scholes
	public double valuatePutOption() {
		double discountedProbabilityFactor = (Math.log(stockPrice / strikePrice) + ((riskFreeRate + ((volatility * volatility) / 2)) * years))
				/ (volatility * Math.sqrt(years));
		
		double exerciseProbabilityFactor = discountedProbabilityFactor - (volatility * Math.sqrt(years));
		return (strikePrice * Math.exp(-riskFreeRate * years) * CNDF(-exerciseProbabilityFactor))
				- (stockPrice * CNDF(-discountedProbabilityFactor));
	}

	// returns the cumulative normal distribution function (CNDF)
	// for a standard normal: N(0,1)
	public double CNDF(double x) {
		int neg = (x < 0d) ? 1 : 0;
		if (neg == 1)
			x *= -1d;

		double k = (1d / (1d + 0.2316419 * x));
		double y = ((((1.330274429 * k - 1.821255978) * k + 1.781477937) * k - 0.356563782) * k + 0.319381530) * k;
		y = 1.0 - 0.398942280401 * Math.exp(-0.5 * x * x) * y;

		return (1d - neg) * y + neg * (1d - y);
	}
	public double getCallDelta(){
		double discountedProbabilityFactor = (Math.log(stockPrice / strikePrice) + ((riskFreeRate + ((volatility * volatility) / 2)) * years))
				/ (volatility * Math.sqrt(years));
		
		double exerciseProbabilityFactor = discountedProbabilityFactor - (volatility * Math.sqrt(years));
		return CNDF(discountedProbabilityFactor);
	}
	public double getPutDelta(){
		double discountedProbabilityFactor = (Math.log(stockPrice / strikePrice) + ((riskFreeRate + ((volatility * volatility) / 2)) * years))
				/ (volatility * Math.sqrt(years));
		
		double exerciseProbabilityFactor = discountedProbabilityFactor - (volatility * Math.sqrt(years));
		return getCallDelta() - 1;
	}
	public double getCallTheta(){
		double discountedProbabilityFactor = (Math.log(stockPrice / strikePrice) + ((riskFreeRate + ((volatility * volatility) / 2)) * years))
				/ (volatility * Math.sqrt(years));
		
		double exerciseProbabilityFactor = discountedProbabilityFactor - (volatility * Math.sqrt(years));
		return ((-stockPrice * volatility * PNDF(discountedProbabilityFactor)) * (Math.sqrt(years)/2)) - (riskFreeRate*strikePrice*Math.exp(-riskFreeRate*years)*CNDF(exerciseProbabilityFactor));
	}
	public double PNDF(double x){
		return (1/Math.sqrt(2*Math.PI)) * Math.exp(-(x*x)/2);
	}

}
//callTheta = (-S*sigma*NPrime)*.5*sqrt(tau) - r*K*exp(-r*tau)*N2
//putTheta = -S*sigma*NPrime*.5*sqrt(tau) + (r*K*exp(-r*tau))*N22
//C = S*N1-(K*exp(-r*tau))*N2 #good
//P = K*exp(-r*tau)*N22 - S*N11 #good
//callDelta = N1 #good
//putDelta = N1 - 1 #good
//callVega = (S*sqrt(tau))*(exp(-0.5 * d1**2)/sqrt(2*pi))/100 #good
//putVega = callVega #good
//callTheta = callTheta/365
//putTheta = putTheta/365
//callGamma = NPrime/(S*sigma*sqrt(tau))
//putGamma = callGamma
//callRho = K*tau*exp(-r*tau)*N2/100 #good
//putRho = -K*tau*exp(-r*tau)*N22/100 #good