/**
 * Calculate the value of European options
 * 
 * @author Kevin Barbian
 *
 */

import javax.swing.*;

public class OptionsClient {
	public static void main(String[] args) {
		int choice = JOptionPane.showConfirmDialog(null,
				"Welcome!.\nThis program is used to calculate the Greek values of " + "a given option\n"
						+ "Hit yes to proceed to the program.");
		if (choice == JOptionPane.YES_OPTION) {
			OptionsGUI run = new OptionsGUI();
		} else {
			JOptionPane.showMessageDialog(null, "Goodbye!");
		}
	}

}
