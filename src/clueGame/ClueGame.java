package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClueGame extends JFrame{
	
	private static Board board;

	public ClueGame() {

		//Create a test Board
		board = Board.getInstance();
		//set file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		//Initialize will load config files
		board.initialize();
		//Deal deck of cards to players
		board.deal();
		
		setSize(1000, 850);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameControlPanel gameControlPanel = new GameControlPanel();
		add(gameControlPanel, BorderLayout.SOUTH);
		
		CardPanel cardPanel = new CardPanel();
		//Mark all cards not in human players hand as seen for testing
		for(Card card : board.getDeck()) {
			if(!board.getHumanPlayer().getHand().contains(card)) {
				board.getHumanPlayer().setSeenCards(card);
				cardPanel.setCardsSeen(card);
			}
			else {
				cardPanel.setCardsInHand(card);
			}
			
		}
		add(cardPanel, BorderLayout.EAST);
		
		board = Board.getInstance();
		add(board, BorderLayout.CENTER);
		
		//pack();
		setVisible(true); // make it visible
	}
	
	

	public static void main(String[] args) {
		ClueGame clueGame = new ClueGame(); //Create clueGame object which is a frame	
	}

}
