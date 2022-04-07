package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class GameControlPanel extends JPanel{

	// variables
	private String theGuess;
	private String guessResult;
	private String turnName;
	private JButton nextPlayer;
	private JButton accusation;
	private JTextField guess;
	private JTextField result;
	private JTextField turn;
	private JTextField roll;
	private int rollNum;

	// sets various panels and adds them to the main panel
	public GameControlPanel() {

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2, 4));

		JPanel whoseTurn = whoseTurnPanel();
		mainPanel.add(whoseTurn);
		
		JPanel roll = rollPanel();
		mainPanel.add(roll);

		JPanel guessButton = guessPanel();
		mainPanel.add(guessButton);
		
		JPanel guessResult = guessResultPanel();
		mainPanel.add(guessResult);

		JPanel guess = createGuess();
		mainPanel.add(guess);

		JPanel result = createResult();
		mainPanel.add(result);

		add(mainPanel);
	}



	// creates the needed panels and labels, and passes an instance variable
	// through for updating purposes. then adds everything to the panel.
	public JPanel whoseTurnPanel() {

		JPanel panel = new JPanel();
		JPanel whoseTurnPanel = new JPanel();
		JLabel whoseTurn = new JLabel("Whose turn?");

		turn = new JTextField(turnName, 30);

		panel.setLayout(new GridLayout(1, 0));
		whoseTurnPanel.setLayout(new GridLayout(2, 2));

		whoseTurnPanel.add(whoseTurn);
		whoseTurnPanel.add(turn);

		panel.add(whoseTurnPanel, BorderLayout.CENTER);
		
		return panel;
	}
	
	public JPanel rollPanel() {

		JPanel panel = new JPanel();
		JPanel rollPanel = new JPanel();
		JLabel theRoll = new JLabel("Roll:");

		roll = new JTextField(rollNum);

		panel.setLayout(new GridLayout(2, 0));
		rollPanel.setLayout(new GridLayout(1, 2));

		rollPanel.add(theRoll);
		rollPanel.add(roll);

		panel.add(rollPanel, BorderLayout.CENTER);

		return panel;
	}

	// creates the needed panels and labels, and passes an instance variable
	// through for updating purposes. then adds everything to the panel.
	public JPanel guessPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 0));

		guess = new JTextField(theGuess);

		panel.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
		panel.add(guess, BorderLayout.CENTER);

		return panel;
	}
	
	public JPanel guessResultPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 0));

		result = new JTextField(guessResult);

		panel.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
		panel.add(result, BorderLayout.CENTER);
		
		return panel;
	}
	
	public JPanel createGuess() {
		JPanel panel = new JPanel();
		return panel;

	}
	
	public JPanel createResult() {
		JPanel panel = new JPanel();
		return panel;
	}
	
	// setters and getters
	public void setGuess(String string) {
		this.theGuess = string;
	}

	public void setGuessResult(String string) {
		this.guessResult = string;
	}
	

	private void setTurn(ComputerPlayer computerPlayer, int i) {
		
	}
	
	//Be able to set or update the information in the fields with setters

	public static void main(String[] args) {
		GameControlPanel panel = new GameControlPanel();  // create the panel
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(1000, 180);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
		
		// test filling in the data
		panel.setTurn(new ComputerPlayer( "Col. Mustard", 0, 0, "orange"), 5);
		panel.setGuess( "I have no guess!");
		panel.setGuessResult( "So you have nothing?");

	}

}
