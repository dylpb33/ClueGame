package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class GameControlPanel extends JPanel{

	// variables
	private String theGuess;
	private String guessResult;
	private String turnName;
	private String rollNum;
	private Color color;
	private JTextField guess;
	private JTextField result;
	private JTextField turn;
	private JTextField roll;

	// sets various panels and adds them to the main panel
	public GameControlPanel() {

		setLayout(new GridLayout(2, 4));

		JPanel whoseTurn = whoseTurnPanel();
		add(whoseTurn);
		
		JPanel roll = rollPanel();
		add(roll);
		
		JButton makeAccusation = makeAccusationButton();
		add(makeAccusation);

		JPanel guessPanel = guessPanel();
		add(guessPanel);
		
		JPanel guessResult = guessResultPanel();
		add(guessResult);
		
		JButton nextPlayer = nextButton();
		add(nextPlayer);
		
		updateDisplay();
	}
	
	// updating the text shown in each text box
	public void updateDisplay() {
		turn.setText(turnName);
		turn.setBackground(color);
		roll.setText(rollNum);
		guess.setText(theGuess);
		result.setText(guessResult);
	}


	// panel showing whose turn it is
	public JPanel whoseTurnPanel() {

		JPanel panel = new JPanel();
		JPanel whoseTurnPanel = new JPanel();
		JLabel whoseTurn = new JLabel("Whose turn?");

		turn = new JTextField(25);
		turn.setText(turnName);
		turn.setBackground(color);

		panel.setLayout(new GridLayout(1, 0));
		whoseTurnPanel.setLayout(new GridLayout(2, 2));

		whoseTurnPanel.add(whoseTurn);
		whoseTurnPanel.add(turn);

		panel.setBorder(new TitledBorder (new EtchedBorder()));
		panel.add(whoseTurnPanel, BorderLayout.CENTER);
		
		return panel;
	}
	
	// panel for which roll player is on
	public JPanel rollPanel() {

		JPanel panel = new JPanel();
		JPanel rollPanel = new JPanel();
		JLabel theRoll = new JLabel("Roll:");

		roll = new JTextField();
		roll.setText(rollNum);

		panel.setLayout(new GridLayout(2, 0));
		rollPanel.setLayout(new GridLayout(1, 2));

		rollPanel.add(theRoll);
		rollPanel.add(roll);

		panel.setBorder(new TitledBorder (new EtchedBorder()));
		panel.add(rollPanel, BorderLayout.CENTER);

		return panel;
	}

	// panel for what the player is guessing
	public JPanel guessPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 0));

		guess = new JTextField();
		guess.setText(theGuess);

		panel.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
		panel.add(guess, BorderLayout.CENTER);

		return panel;
	}
	
	// checks if guess is right
	public JPanel guessResultPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 0));

		result = new JTextField();
		result.setText(guessResult);

		panel.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
		panel.add(result, BorderLayout.CENTER);
		
		return panel;
	}
	
	// button for player to make an accusation
	public JButton makeAccusationButton() {
		JButton button = new JButton();
		JLabel label = new JLabel("Make Accusation");
		button.add(label);
		return button;

	}
	
	// button to move on to next 
	public JButton nextButton() {
		JButton button = new JButton();
		JLabel label = new JLabel("NEXT!");
		button.add(label);
		return button;
	}
	
	// setters and getters
	public void setGuess(String string) {
		this.theGuess = string;
		updateDisplay();
	}

	public void setGuessResult(String string) {
		this.guessResult = string;
		updateDisplay();
	}
	

	private void setTurn(ComputerPlayer computerPlayer, int r) {
		turnName = computerPlayer.getName();
		rollNum = Integer.toString(r);
		color = computerPlayer.getColor();
		updateDisplay();
	}
	

	public static void main(String[] args) {

		GameControlPanel panel = new GameControlPanel();  // create the panel
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
		frame.pack();
		
		// test filling in the data
		panel.setTurn(new ComputerPlayer( "Col. Mustard", 0, 0, "orange"), 5);
		panel.setGuess( "I have no guess!");
		panel.setGuessResult( "So you have nothing?");

	}

}
