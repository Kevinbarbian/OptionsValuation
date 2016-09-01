import java.awt.event.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
public class OptionsGUI implements ActionListener {
	public static void main(String[] args){
		OptionsGUI g = new OptionsGUI();

	}
	private double ePF;
	private double dPF;
	private JFrame frame;
	private JLabel label;
	private JLabel userLabel;
	private JTextField userField;
	private JButton calculate;
	private JButton clear;
	private JTextField callValue;
	private JTextField putValue;
	private JTextField numberField;
	private JTextField S;
	private JTextField K;
	private JTextField tau;
	private JTextField v;
	private JTextField r;
	private JTextField deltaC;
	private JTextField gammaC;
	private JTextField rhoC;
	private JTextField thetaC;
	private JTextField vegaC;
	private JTextField deltaP;
	private JTextField gammaP;
	private JTextField rhoP;
	private JTextField thetaP;
	private JTextField vegaP;
	private JTextField valueC;
	private JTextField valueP;
	private String[] labels;
	private String[] labels2;
	private JTextField[] fields;
	private JTextField[] fields2;
	private JTextField[] fields3;
	private double[] values;
	private double[] results;
	public OptionsGUI() {
		// setup frame
//		valueC = new JTextF
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		frame.setTitle("Options Pricing");
		frame.setLayout(new BorderLayout());
		putValue = new JTextField(8);
		String[] labels = {"Underlying Price: ", "Strike Price: ", "Time until expiry in days: ", "Volatility: ", "Risk-free rate"};
		String[] labels2 = {"Value","Delta", "Gamma", "Rho", "Theta", "Vega"};
		JTextField[] fields = {S, K, tau, v, r};
		JTextField[] fields2 = {valueC,deltaC,gammaC,rhoC,thetaC,vegaC};
		JTextField[] fields3 = {valueP,deltaP, gammaP, rhoP, thetaP, vegaP};
		int parameters = labels.length;
		SpringLayout layout = new SpringLayout();
		JPanel p = new JPanel(layout);
		JPanel greeks = new JPanel(layout);
		for (int i = 0; i < labels.length; i++){
			fields[i] = new JTextField(8);
			p.add(new JLabel(labels[i]));
			p.add(fields[i]);
		}
		greeks.add(new JLabel("		"));
		JLabel call = new JLabel("Call");
		call.setHorizontalAlignment(JLabel.CENTER);
		greeks.add(call);
		JLabel put = new JLabel("Put");
		put.setHorizontalAlignment(JLabel.CENTER);
		greeks.add(put);
		for (int i = 0; i < labels.length+1; i++){
			fields2[i] = new JTextField(8);
			fields3[i] = new JTextField(8);
			greeks.add(new JLabel(labels2[i]));
			greeks.add(fields2[i]);
			greeks.add(fields3[i]);
		}
		double[] values = new double[fields.length];
		SpringUtilities.makeCompactGrid(p, parameters, 2, 6, 6, 6, 6);
		SpringUtilities.makeCompactGrid(greeks, parameters + 2, 3, 6, 6, 6, 6);
		JPanel south = new JPanel(new FlowLayout());
		calculate = new JButton("Calculate");
		calculate.setHorizontalAlignment(JButton.CENTER);
		calculate.addActionListener(this);
		clear = new JButton("Clear");
		clear.setHorizontalAlignment(JButton.CENTER);
		south.add(calculate);
		south.add(clear);
		frame.add(south, BorderLayout.SOUTH);
		frame.add(p, BorderLayout.WEST);
		frame.add(greeks, BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);
		
		
	}
	public void actionPerformed(ActionEvent event){
		Object source = event.getSource();
		if (source == calculate){
			Option o = new Option(Double.parseDouble(fields[0].getText()),2,2,2,2);
		
		}
		else {
			JOptionPane.showMessageDialog(null, "Lead Developer: Kevin Barbian");
		}
	}
//	public void runPrisonScene(){
//		JOptionPane.showMessageDialog(null,"...");
//	}
}
