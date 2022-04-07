package clueGame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameControlPanel extends JPanel{
	private String theGuess;
	private JTextField name;

	public GameControlPanel() {
		// TODO Auto-generated constructor stub
	}
	
	public void setTurn(ComputerPlayer player, int num){
		
	}
	
	public void setGuess(String guess) {
		theGuess.setText(guess);
	}
	
	
	public void setGuessResult(String s) {
		
	}
	
	//Button to move to the next player
	private JPanel moveNextPlayerButton() {
		JPanel panel = new JPanel();
		return panel;
	}
	
	//Button to make accusation
	private JPanel makeAccusationButton() {
		JPanel panel = new JPanel();
		return panel;
	}
	
	//Display of the roll of the die
	private JPanel rollDiePanel() {
		JPanel panel = new JPanel();
		return panel;
	}
	
	//Display of whose turn it is
	private JPanel whoseTurnPanel() {
		JPanel panel = new JPanel();
		return panel;
	}
	
	//Display of guesses made by players and the result
	private JPanel guessesPanel() {
		JPanel panel = new JPanel();
		return panel;
	}
	
	//Be able to set or update the information in the fields with setters

	public static void main(String[] args) {
		GameControlPanel panel = new GameControlPanel();  // create the panel
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(750, 180);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
		
		// test filling in the data
		panel.setTurn(new ComputerPlayer( "Col. Mustard", 0, 0, "orange"), 5);
		panel.setGuess( "I have no guess!");
		panel.setGuessResult( "So you have nothing?");

	}

}
