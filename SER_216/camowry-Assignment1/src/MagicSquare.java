import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
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


/** Assignment 1: Determines if User Input is a Magic Square & displays True or False. 
 * 
 * @author Chris Mowry (ASU ID: camowry)
 * @version 1.0.0 (10/15/2017)
 *
 */
public class MagicSquare{
	
	/*
	 *  Class Variables
	 */
	private static JFrame frame; // frame object to hold GUI Controls
	private static JTextField userInputTextbox; // user input entry control
	
	// Output Display Objects
	private static JLabel false_Label = new JLabel("False");
	private static JLabel true_Label = new JLabel("True");
	
	// User message display label
	private static String  defaultMessage = "Please enter your values separated with a space.";
	private static JLabel userMessage_Label = new JLabel(defaultMessage);
	
	// holds the user input value.
	private static String inputString;	

	
	private static int n; // holds the number of rows/columns n**2 is the number of cells.
	private static int[][] square; // holds the integer representation of the square.
	private static boolean validSquare; // holds if the square is valid
	private static String[] squareStringArray; // holds the user input of the square as a string array
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Launches GUI
		launchGUI();
	}
	
	
	// GUI Components 
	
	
	/** Launch the User interface.
	 * 
	 * 
	 */
	protected static void launchGUI() {
		setUserInputValue("");
		initialize();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/* Sets the User Input from the Text box.
	 * 
	 */	
	protected static void setUserInputValue(String input) {
		inputString = input;
	}
	
	
	/** Setup of GUI frame and associated controls
	 * 
	 */
	private static void initialize() {
		
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
	private static void checkInput_Button_Click() {
		// resets the full display
		resetDisplay();
		
		setUserInputValue(userInputTextbox.getText());
		
		//MagicSquareObj mSquare = new MagicSquareObj(); // creates new magic square object
		
		
		try {
			// sets the value of the square
			setSquare(inputString);
			// validates the square
			displayResult(isValidSquare());
		}
		// if invalid character or number of values, exception is thrown.
		catch(IllegalArgumentException ex) {
			// Displays invalid input to user
			displayValidateInput(false);
			userMessage_Label.setText(ex.getMessage());
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
	private static void displayValidateInput(boolean valid) {
		// resets the validation display.
		resetValidate();

		// sets the user message visibility & sets text box background to red.
		userMessage_Label.setVisible(true);
		userInputTextbox.setBackground(Color.RED);
		displayResult(false);
	}
	
	
	/** Resets the validation display to its default settings
	 * 
	 */
	private static void resetValidate() {
		userMessage_Label.setText(defaultMessage);; // makes user message not visible
		userInputTextbox.setBackground(Color.WHITE); // returns the input text box background to white.
	}
	
	
	/** Resets the full display to its default settings.
	 * 
	 */
	private static void resetDisplay() {
		resetResults(); // resets the results
		resetValidate(); // resets the user input validation.
	}

	
	// Operational Components
	
	
		
	/** Sets the n value (number of rows/columns) of the magic square.
	 * 
	 * @param cellCount number of rows/columns
	 * @throws IllegalArgumentException
	 */
	protected static void setN(int nVal) throws IllegalArgumentException{
		// checks validity of cell count
		if(isValidN((int)Math.pow((double)n, 2))) {
			n = nVal; // sets class variable
		}
		else {
			setValidSquare(false); // sets validity to false
			throw new IllegalArgumentException("Invalid number of values.");			
		}
	}
	
	
	
	/** Sets the Magic Square Matrix
	 * 
	 * @param square integer nested list of the magic square.
	 */
	protected static void setSquare(String squareVals) {
		setSquareString(squareVals); // sets the square array variable
		int[][] newSquare = processSquareVals(squareVals); // processes user input
		square = newSquare; // sets class square to processed square
		setValidSquare(evaluateSquare()); // establishes validity
	}
	

	/** Mutator to set variable of square validity.
	 * 
	 * @param validSquare true/false if square is valid magic square.
	 */
	protected static void setValidSquare(boolean validSquareVal) {
		validSquare = validSquareVal;
	}
	
	
	/** Gets the validity of the square.
	 * 
	 * @return boolean true if the square meets the definition of a "Magic Square"
	 */
	protected static boolean isValidSquare() {
		boolean valid = validSquare;
		return valid;
	}
	
	
	/** Cleans up and converts user input string into array.
	 * 
	 * @param inputString user input of integers separated by spaces
	 */
	protected static void setSquareString(String inputString) {
		String trimmedVals = inputString.trim(); // removes spaces & returns from input
		String[] valArray = trimmedVals.split(" "); // creates an array from the trimmed input		
		squareStringArray = valArray;
	}
	
	
	/** Calculates & returns the square root of the total number input values.
	 * 
	 * @param stringArray string array from user input
	 * @return returns n value (number of rows/columns) as an integer.
	 */
	private static int calcN(String[] stringArray) {
		int nVal = 0;
		nVal = (int)Math.sqrt(stringArray.length); // gets the square root of the total number of input values.
	
		return nVal;
	}
	
	
	/** Checks validity of number of input values (is the root an integer)
	 * 
	 * @param cellCount total number of cells to be used in the square
	 * @return boolean if the input has a integer square root
	 */
	private static boolean isValidN(int cellCount) {
		double sqrtVal = Math.sqrt(cellCount); 
		int x = (int)sqrtVal;
		 // compares the integer **2 and the value **2
		return Math.pow(sqrtVal,2) == Math.pow(x,2);
	}
	
	
	/** Checks if all the values input are all integers
	 * 
	 * @param squareValsArray
	 * @return boolean if all input values are valid.
	 */
	private static boolean isAllIntegers(String[] squareValsArray) {
		for(String value: squareValsArray) {
			// attempts to parse the values as integers
			try {
				Integer.parseInt(value);
			}
			// if it fails, returns false
			catch(NumberFormatException e) {
				return false;
			}
		}
		
		return true;
	}
	

	/** Populates the 2d version of the square.
	 * 
	 * @param stringArray 1 dimensional array representation of the "Magic square"
	 * @return 2D matrix version of the square
	 */
	private static int[][] populateSquare(String[] stringArray){
		int[][] outputSquare = new int[n][n]; // matrix to be populated
		int stringArrayIndex = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				// Adds the values in the proper positions.
				outputSquare[i][j] = Integer.parseInt(stringArray[stringArrayIndex]);
				stringArrayIndex++;	// increments array index
			}
		}
		
		return outputSquare;
	}
	
	/** Final step in generating square (2D representation)
	 * 
	 * @param squareVals
	 * @return returns a full cleaned and populated square if input is valid.
	 * @throws IllegalArgumentException if input count or values are invalid
	 */
	private static int[][] processSquareVals(String squareVals) throws IllegalArgumentException{
		int rowCount = 0;	
		int[][] newSquare = null;

		rowCount = calcN(squareStringArray); // gets N value
		
		// checks if the total number of inputs is a valid amount for a Magic Square
		if(isValidN(squareStringArray.length)) {
			// checks if all the values are valid input values
			if(isAllIntegers(squareStringArray)) {
				setN(rowCount); // sets new N value
				// creates a new 2D integer array to hold input values.
				newSquare = populateSquare(squareStringArray);
			}
			else {
				setValidSquare(false);
				throw new IllegalArgumentException("Invalid input value.");
			}
		}
		else {
			setValidSquare(false);
			throw new IllegalArgumentException("The number of input values on the square is invalid.");
		}
		
		return newSquare;
	}
	
	
	/** Evaluates if the processed matrix meets all the definitions of a Magic Square
	 * I'm sure there is a more efficient way of testing this, but its escaping me!!! 
	 * @return boolean of whether or not the square meets the definition of a magic square.
	 */
	protected static boolean evaluateSquare() {
		
		// evaluates the sum of the rows
		int sum = 0, prevSum =0;

		// Evaluates for duplicates
		Set<String> evalSet = new HashSet<String>(Arrays.asList(squareStringArray));
		if(squareStringArray.length - evalSet.size() != 0) {
			return false;
		}
		
		// Sums the rows and evaluates the sums
		for(int i = 0; i < n; i++) {
			sum = 0;
			for(int j = 0; j < n; j++) {
				sum += square[i][j];
			}
			
			// Compares the row sum to the previous row sum.
			if(i != 0 && sum != prevSum) {
				return false;
			}
			prevSum = sum;
		}
		
		// evaluates the sum of the columns
		for(int i = 0; i < n; i++) {
			sum = 0;
			for(int j = 0; j < n; j++) {
				sum += square[j][i];
			}
			
			// Compares the column sum to the previous column sum.
			if(sum != prevSum) {
				return false;
			}
			prevSum = sum;
		}		
		
		// evaluates the sum of the diagonals
		// Sums the negative slope diagonal
		sum = 0;
		for(int i = 0; i < n; i++) {
			sum += square[i][i];
		}
		// Compares the negative slope diagonal sum to the previous column sum.
		if(sum != prevSum) {
			return false;
		}
		prevSum = sum;
		
		// Sums the positive slope diagonal
		sum = 0;
		for(int i = 0; i < n; i++) {
			sum += square[i][n-(i+1)];
		}
		// Compares the positive slope diagonal sum to the negative slope diagonal sum.
		if(sum != prevSum) {
			return false;
		}	
		
		// if none of the other evaluations returned false, returns true.
		return true;
	}
	 
}

