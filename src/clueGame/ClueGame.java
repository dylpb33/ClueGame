package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClueGame extends JFrame{
	
	private static Board board;
	private int xSize, ySize;

	public ClueGame() {

		//Create a test Board
		board = Board.getInstance();
		//set file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		//Initialize will load config files
		board.initialize();
		//Deal deck of cards to players
		board.deal();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		xSize = (int) (screen.getWidth() * 0.7);
		ySize = (int) (screen.getHeight() * 0.7);
		
		// setting JFrame size, title and exit
		setSize(xSize, ySize);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue Game - CSCI306");
		
		// adding the GameControlPanel
		GameControlPanel gameControlPanel = new GameControlPanel();
		add(gameControlPanel, BorderLayout.SOUTH);
		
		// adding the CardPanel and card data
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
		
		setVisible(true); // make it visible
	}
	
	

	public static void main(String[] args) {
		ClueGame clueGame = new ClueGame(); //Create clueGame object which is a frame	
	}

}
