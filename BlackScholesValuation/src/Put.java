
public class Put extends Option {
	public Put(double stockPrice, double strikePrice, double riskFreeRate, double volatility, double days){
		super(stockPrice, strikePrice, riskFreeRate, volatility, days);
	}
	public double valuate() {
		return (getStrikePrice() * Math.exp(-getRate() * getYears()) * CNDF(-getEPF()))
				- (getStockPrice() * CNDF(-getDPF()));
	}
	public double getDelta(){
		return CNDF(getDPF()) - 1;
	}

}
