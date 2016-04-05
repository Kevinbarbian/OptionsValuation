import java.math.*;
public class BlackScholes {
	public static void main(String[] args){
	Option one = new Option(27.07,20,.05,.46,365);
	System.out.println(one.valuateCallOption());
	System.out.println(one.valuatePutOption());
	System.out.println(one.getCallTheta());
		
		
	}
}
//S = 27.07  #stock price
//double stockPrice 
//K = 20 #strike price
//r = .05 #risk free interest rate
//sigma = .46 #volatility
//tau = 1 #Time to expiry in years
//d1 = ((log(S/K) + ((r+0.5*sigma**2)*tau)))/(sigma*sqrt(tau))
//#d11 = d1**2
//d2 = d1 - (sigma*sqrt(tau))
//d22 = -d2
//#N1 = 0.5*(1+erf(d1/sqrt(2)))
//N1 = norm.cdf(d1)
//N11 = norm.cdf(-d1)
//NPrime = norm.pdf(d1)
//N2 = norm.cdf(d2)
//N22 = norm.cdf(-d2)
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
//deltas = [str(round(callDelta,3)),str(round(putDelta,3))]
//gammas = [str(round(callGamma,3)),str(round(putGamma,3))]
//vegas = [str(round(callVega,3)),str(round(putVega,3))]
//thetas = [str(round(callTheta,3)),str(round(putTheta,3))]
//rhos = [str(round(callRho,3)),str(round(putRho,3))]
//print("Call option value: ",str(round(C,3)))
//print("Put option value: ",str(round(P,3)))
//print("          Greeks     ")
//print("Option:   Call      Put")
//print("Delta: ",deltas)
//print("Gamma: ",gammas)
//print("Theta: ",thetas)
//print("Vega:  ",vegas)
//print("Rho:   ",rhos)
//##################################################
//#        Put    Call
//#Delta
//#Gamma
//#Rho
//#Theta
//#Vega