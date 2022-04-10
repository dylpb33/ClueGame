package clueGame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClueGame extends JPanel{
	

	public ClueGame() {
		
		GameControlPanel gameControlPanel = new GameControlPanel();
		add(gameControlPanel, BorderLayout.SOUTH);
		
		CardPanel cardPanel = new CardPanel();
		add(cardPanel, BorderLayout.EAST);
		
	}
	
	

	public static void main(String[] args) {

		ClueGame clueGame = new ClueGame();  // create the panel		
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(clueGame); // put the panel in the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
		frame.pack(); //makes the frame fit the screen
	}

}
