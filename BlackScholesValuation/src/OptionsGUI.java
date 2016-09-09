import java.awt.event.*;
import java.math.*;
import javax.swing.*;
import java.awt.*;

/**
 * Calculate the value of European options
 * 
 * @author Kevin Barbian
 *
 */
public class OptionsGUI implements ActionListener {
	private JFrame frame;
	private JButton calculate;
	private JButton clear;
	private JTextField[] fields;
	private JTextField[] fields2;
	private JTextField[] fields3;
	private double[] callValues;
	private double[] putValues;
	private double[] input;
	private JPanel p;
	private JPanel greeks;
	private Option o;

	public OptionsGUI() {
		// setup frame
		setupFrame();
		createButtons();
		instantiateArrays();
		addParts();

	}

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == calculate) {
			for (int i = 0; i < fields.length; i++) {
				input[i] = Double.parseDouble(fields[i].getText());
			}
			o = new Option(input[0], input[1], input[2], input[3], input[4]);
			storeValues();
			setTextFields();

		} else if (source == clear) {
			for (int i = 0; i < fields.length; i++) {
				fields[i].setText("");
				fields2[i].setText("");
				fields3[i].setText("");
			}
			fields2[fields2.length - 1].setText("");
			fields3[fields2.length - 1].setText("");
		}
	}

	// Store greek values in arrays for easy access
	public void storeValues() {
		callValues[0] = round(o.valuateCallOption(), 4);
		callValues[1] = round(o.getCallDelta(), 4);
		callValues[2] = round(o.getGamma(), 4);
		callValues[3] = round(o.getCallRho(), 4);
		callValues[4] = round(o.getCallTheta(), 4);
		callValues[5] = round(o.getVega(), 4);
		putValues[0] = round(o.valuatePutOption(), 4);
		putValues[1] = round(o.getPutDelta(), 4);
		putValues[2] = round(o.getGamma(), 4);
		putValues[3] = round(o.getPutRho(), 4);
		putValues[4] = round(o.getPutTheta(), 4);
		putValues[5] = round(o.getVega(), 4);

	}

	public void instantiateArrays() {
		fields = new JTextField[5];
		fields2 = new JTextField[6];
		fields3 = new JTextField[6];
		callValues = new double[6];
		putValues = new double[6];
		input = new double[5];
	}

	// set the text fields for call/put option greek values
	public void setTextFields() {
		for (int i = 0; i < fields2.length; i++) {
			fields2[i].setText("" + callValues[i]);
			fields3[i].setText("" + putValues[i]);
		}
	}

	public void createButtons() {
		calculate = new JButton("Calculate");
		calculate.setHorizontalAlignment(JButton.CENTER);
		calculate.addActionListener(this);
		clear = new JButton("Clear");
		clear.setHorizontalAlignment(JButton.CENTER);
		clear.addActionListener(this);
	}

	// formatting and creating the springform layout
	public void addParts() {
		String[] labels = { "Underlying Price: ", "Strike Price: ", "Risk-free interest rate: ", "Volatility: ",
				"Time (days): " };
		String[] labels2 = { "Value", "Delta", "Gamma", "Rho", "Theta", "Vega" };
		int parameters = labels.length;
		SpringLayout layout = new SpringLayout();
		p = new JPanel(layout);
		greeks = new JPanel(layout);
		for (int i = 0; i < labels.length; i++) {
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
		for (int i = 0; i < labels.length + 1; i++) {
			fields2[i] = new JTextField(8);
			fields3[i] = new JTextField(8);
			greeks.add(new JLabel(labels2[i]));
			greeks.add(fields2[i]);
			greeks.add(fields3[i]);
		}
		SpringUtilities.makeCompactGrid(p, parameters, 2, 6, 6, 6, 6);
		SpringUtilities.makeCompactGrid(greeks, parameters + 2, 3, 6, 6, 6, 6);
		JPanel south = new JPanel(new FlowLayout());
		south.add(calculate);
		south.add(clear);
		frame.add(south, BorderLayout.SOUTH);
		frame.add(p, BorderLayout.WEST);
		frame.add(greeks, BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);
	}

	public void setupFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setTitle("Options Pricing");
		frame.setLayout(new BorderLayout());
	}
	public double round(double value, int places) {
		BigDecimal val = new BigDecimal(value);
		val = val.setScale(places, RoundingMode.HALF_UP);
		return val.doubleValue();
	}

}
