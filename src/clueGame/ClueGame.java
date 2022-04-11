package clueGame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClueGame extends JFrame{
	private static Board board;

	public ClueGame() {
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameControlPanel gameControlPanel = new GameControlPanel();
		add(gameControlPanel, BorderLayout.SOUTH);
		
		CardPanel cardPanel = new CardPanel();
		add(cardPanel, BorderLayout.EAST);
		
		board = Board.getInstance();
		add(board, BorderLayout.CENTER);
		
	}
	
	

	public static void main(String[] args) {
		ClueGame clueGame = new ClueGame(); //Create clueGame object which is a frame	
		clueGame.setVisible(true); // make it visible
	}

}
