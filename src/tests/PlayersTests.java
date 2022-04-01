package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
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
		int total = 0;
		for(int i = 0; i < board.getPlayerArray().size(); i++) {
			total = total + board.getPlayer(i).getHandSize();
		}
		assertEquals(board.getDeck().size(), total);
		assertEquals()
	}
}
