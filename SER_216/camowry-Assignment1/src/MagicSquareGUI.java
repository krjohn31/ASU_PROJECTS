import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** User interface to handle input and display results and validity of input.
 * 
 * @author Chris Mowry (ASU ID: camowry)
 * @version 1.0.0 (10/15/2017)
 * 
 */
public class MagicSquareGUI {
	
	/*
	 *  Class Variables
	 */
	private JFrame frame; // frame object to hold GUI Controls
	private JTextField userInputTextbox; // user input entry control
	
	// Output Display Objects
	private static JLabel false_Label = new JLabel("False");
	private static JLabel true_Label = new JLabel("True");
	
	// User message display label
	String defaultMessage = "Please enter your values separated with a space.";
	private JLabel userMessage_Label = new JLabel(defaultMessage);
	
	// holds the user input value.
	private String inputString;
	
	
	/** Initializes GUI Frame and Controls
	 * 
	 */
	public MagicSquareGUI() {
		setUserInputValue("");
		initialize();
	}	
	
	
	/** Launch the User interface.
	 * 
	 * 
	 */
	protected static void launchGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MagicSquareGUI window = new MagicSquareGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/* Gets the User Input from the Text box.
	 * 
	 * @returns the User Input String Value
	 */
	protected String getUserInputValue() {
		String value = this.inputString;
		return value;
	}
	
	
	/* Sets the User Input from the Text box.
	 * 
	 */	
	protected void setUserInputValue(String input) {
		this.inputString = input;
	}
	
	
	/** Setup of GUI frame and associated controls
	 * 
	 */
	private void initialize() {
		
		// GUI Frame Object Settings
		frame = new JFrame("Magic Square Checker");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 519, 233);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// User Entry Object Settings
		userInputTextbox = new JTextField();
		userInputTextbox.setBounds(105, 45, 388, 20);
		userInputTextbox.setBackground(Color.WHITE);
		frame.getContentPane().add(userInputTextbox);
		userInputTextbox.setColumns(10);
		
		// Check Button Object Settings
		JButton checkInput_Button = new JButton("Check");
		checkInput_Button.setBackground(Color.WHITE);
		checkInput_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkInput_Button_Click(); // method to handle button click
			}			
		});
		checkInput_Button.setBounds(409, 76, 84, 23);
		frame.getContentPane().add(checkInput_Button);
		
		// Input Box Label Object Settings
		JLabel input_Label = new JLabel("Check Value:");
		input_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		input_Label.setBounds(10, 48, 85, 14);
		frame.getContentPane().add(input_Label);
		
		// True Label Object Settings
		true_Label.setForeground(Color.WHITE);
		true_Label.setHorizontalAlignment(SwingConstants.CENTER);
		true_Label.setFont(new Font("Tahoma", Font.BOLD, 14));
		true_Label.setBackground(Color.GRAY);
		true_Label.setOpaque(true);
		true_Label.setBounds(181, 124, 91, 23);
		frame.getContentPane().add(true_Label);
		
		// False Label Object Settings
		false_Label.setOpaque(true);
		false_Label.setHorizontalAlignment(SwingConstants.CENTER);
		false_Label.setForeground(Color.WHITE);
		false_Label.setFont(new Font("Tahoma", Font.BOLD, 14));
		false_Label.setBackground(Color.GRAY);
		false_Label.setOpaque(true);
		false_Label.setBounds(282, 124, 91, 23);
		frame.getContentPane().add(false_Label);
		
		// User Message Label Object Settings
		userMessage_Label.setBounds(105, 14, 388, 20);
		userMessage_Label.setFont(new Font("Tahoma", Font.BOLD, 12));
		userMessage_Label.setForeground(Color.BLACK);
		userMessage_Label.setVisible(true);
		frame.getContentPane().add(userMessage_Label);
	}
	
	
	/** Process when checkInput_Button is clicked.
	 * 
	 */
	private void checkInput_Button_Click() {
		// resets the full display
		resetDisplay();
		
		setUserInputValue(this.userInputTextbox.getText());
		try {
			// sets the value of the square
			MagicSquare.setSquare(this.inputString);
			// validates the square
			displayResult(MagicSquare.isValidSquare());
		}
		// if invalid character or number of values, exception is thrown.
		catch(IllegalArgumentException ex) {
			// Displays invalid input to user
			displayValidateInput(false);
			this.userMessage_Label.setText(ex.getMessage());
		}
	}
	
	
	/** Configures the display of the program output.
	 * 
	 * @param status: Value to be displayed by the GUI
	 */
	private static void displayResult(boolean status) {
		// resets the results
		resetResults();
		
		// Based on the Status input, displays value.
		if(status) {
			// sets true label background to green.
			true_Label.setBackground(Color.GREEN);
		}
		else {
			// sets false label background to red.
			false_Label.setBackground(Color.RED);
		}
	}
	
	
	/** Resets the results to the default setting.
	 * 
	 */
	private static void resetResults() {
		// resets the process display
		false_Label.setBackground(Color.GRAY);
		true_Label.setBackground(Color.GRAY);		
	}
	
	
	/** Notifies the user of invalid input
	 * 
	 */
	private void displayValidateInput(boolean valid) {
		// resets the validation display.
		resetValidate();

		// sets the user message visibility & sets text box background to red.
		this.userMessage_Label.setVisible(true);
		this.userInputTextbox.setBackground(Color.RED);
	}
	
	
	/** Resets the validation display to its default settings
	 * 
	 */
	private void resetValidate() {
		this.userMessage_Label.setText(defaultMessage);; // makes user message not visible
		this.userInputTextbox.setBackground(Color.WHITE); // returns the input text box background to white.
	}
	
	
	/** Resets the full display to its default settings.
	 * 
	 */
	private void resetDisplay() {
		resetResults(); // resets the results
		resetValidate(); // resets the user input validation.
	}
}
