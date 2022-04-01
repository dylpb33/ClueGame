package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;

public class PlayersTests {
	
	private static Board board;

	@BeforeAll
	public void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		// Initialize will load BOTH config files
		board.initialize();
	}
	@Test
	public void testNumberOfPlayers() {
		assertEquals(board.getPlayerArray().size(), 6);
	}
	
	@Test
	public void testHuman() {
		assertTrue(board.getPlayer(0) instanceof HumanPlayer);
		assertEquals("Ms. Pots", board.getPlayer(0).getName());
		assertEquals("red", board.getPlayer(0).getColor());
		
	}
	
	@Test
	public void testComputer() {
		assertTrue(board.getPlayer(1) instanceof ComputerPlayer);
		assertEquals("Moreen Ridley", board.getPlayer(1).getName());
		assertEquals("blue", board.getPlayer(1).getColor());
	}
	
	@Test
	public void testDeck() {
		assertTrue(board.getDeck() != null);
		assertEquals(board.getDeck().size(), 21);
		assertEquals("Sunroom", board.getCardInDeck(0).getCardName());
		assertEquals("Dr. Brown", board.getCardInDeck(12).getCardName());
		assertEquals("Hammer", board.getCardInDeck(16).getCardName());
	}
	
	@Test
	public void testSolution() {
		assertTrue(board.getSolution().getSolutionRoom() != null);
		assertTrue(board.getSolution().getSolutionWeapon() != null);
		assertTrue(board.getSolution().getSolutionPerson() != null);
	}
	
	@Test
	public void testCardsDealt() {
		//Test total size of deck is correct.
		int total = 0;
		for(int i = 0; i < board.getPlayerArray().size(); i++) {
			total = total + board.getPlayer(i).getHandSize();
		}
		assertEquals(board.getDeck().size(), total);
		
		//Test that all players have roughly the same number of cards.
		int avgHandSize = (board.getDeck().size() - 3)/board.getPlayerArray().size();
		int min = avgHandSize - 1;
		int max = avgHandSize + 1;
		assertTrue(board.getPlayer(0).getHandSize() < max && board.getPlayer(0).getHandSize() > min);
		
		//Test same card is not given to more than one player
		ArrayList<Card> checkCards = new ArrayList();
		for(int i = 0; i < board.getPlayerArray().size(); i++) {
			for(int j = 0; j < 3; j++) {
				assertFalse(checkCards.contains(board.getPlayer(i).getHand(j)));
				checkCards.add(board.getPlayer(i).getHand(j));
			}
		}
		
	}
}