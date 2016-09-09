README FILE

A Black-Scholes Calculator for Java, Kevin Barbian, September 8, 2016.

CONTENTS
-------------------------------------------
I. ABOUT
II. LIST OF FILES
III. DESIGN
IV. MOTIVATION
V. TEST


I. ABOUT
-------------------------------------------
Options are one of the main financial derivatives that are traded in the markets.  This program is used for calculating the theoretical value of a European call or put option--calculations are based on the Black-Scholes equation.  The equation assumes European options, no dividends, efficient markets(no arbitrage), no commisions, constant interest rates, and a lognormal distribution.  Underlying price is the price of the asset.  Strike price is the exercise price.  Interest rates and volatility are to be entered in decimal format.  Time is to be entered as days until expiry.  The Greeks involved in this calculator are delta, gamma, rho, theta, and vega.  This program was compiled using JDK 8.


II. LIST OF FILES
-------------------------------------------
Option.java						Performs calculations of Greek values
OptionsGUI.java					Simple user interface built with swing
OptionsClient.java				Client program 
SpringForm.java 				Formatting GUI
SpringUtilities.java 			Formatting GUI


III. DESIGN
-------------------------------------------
Input from the user interface is passed as parameters to the Option class.  After the data is processed it is displayed in the appropriate text fields.  The GUI was designed using Swing and imports for the SpringForm layout.


IV. MOTIVATION
-------------------------------------------
This program was originally designed using Python, but I decided to re-write it in Java to see how it would go.  The purpose of this project was to learn more about GUI design and to create a client friendly interface for calculating European options. 


V. TEST
-------------------------------------------
Input:
Underlying Price: 95
Strike Price: 90
Risk-free interest rate: .05
Volatility: .20
Time: 30

Output:
             Call Option 				Put Option

Value:				5.7977					   0.4286
Delta:				0.8516					  -0.1484
Gamma:				0.0425						 0.0425
Rho:				  0.0617					  -0.0119
Theta:			 -0.0313					  -0.019
Vega:			  	0.0063						 0.006
