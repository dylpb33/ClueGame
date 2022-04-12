package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClueGame extends JFrame{
	
	private static Board board;

	public ClueGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameControlPanel gameControlPanel = new GameControlPanel();
		add(gameControlPanel, BorderLayout.SOUTH);
		
		CardPanel cardPanel = new CardPanel();
		add(cardPanel, BorderLayout.EAST);
		
		board = Board.getInstance();
		add(board, BorderLayout.CENTER);
		
	}
	
	

	public static void main(String[] args) {
		//Create a Board
		board = Board.getInstance();

		//set file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");

		//Initialize will load config files
		board.initialize();
		
		ClueGame clueGame = new ClueGame(); //Create clueGame object which is a frame	
		clueGame.setVisible(true); // make it visible
		clueGame.pack();
	}

}
