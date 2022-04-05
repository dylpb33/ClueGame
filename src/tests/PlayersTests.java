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
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		// Initialize will load BOTH config files
		board.initialize();
		//Deal cards to players
		board.deal();
	}
	
	//Test correct number of players were created.
	@Test
	public void testNumberOfPlayers() {
		assertEquals(6, board.getPlayerArray().size());
	}
	
	//Test human player.
	@Test
	public void testHuman() {
		assertTrue(board.getPlayer(0) instanceof HumanPlayer);
		assertEquals("Ms. Pots", board.getPlayer(0).getName());
		assertEquals("red", board.getPlayer(0).getColor());
		
	}
	
	//Test computer player.
	@Test
	public void testComputer() {
		assertTrue(board.getPlayer(1) instanceof ComputerPlayer);
		assertEquals("Moreen Ridley", board.getPlayer(1).getName());
		assertEquals("blue", board.getPlayer(1).getColor());
	}
	
	//Test deck is correct size and holds correct cards.
	@Test
	public void testDeck() {
		assertTrue(board.getDeck() != null);
		assertEquals(21, board.getDeck().size());
		assertEquals("Sunroom", board.getCardInDeck(0).getCardName());
		assertEquals("Dr. Brown", board.getCardInDeck(12).getCardName());
		assertEquals("Hammer", board.getCardInDeck(16).getCardName());
	}
	
	//Test that solution is created properly with one of each type of card.
	@Test
	public void testSolution() {
		// creating a new solution
		board.setSolution(new Card("Kitchen"), new Card("Pacman"), new Card("Wire"));
		
		// testing to make sure the solution gets pulled correctly
		assertEquals("Kitchen", board.getSolution().getSolutionRoom().getCardName());
		assertEquals("Wire", board.getSolution().getSolutionWeapon().getCardName());
		assertEquals("Pacman", board.getSolution().getSolutionPerson().getCardName());
	}
	
	@Test
	public void testAccusation() {
		board.setSolution(new Card("Parlor"), new Card("Leslie Zayne"), new Card("Saw"));
		
		//Check that the correct accusation returns true
		assertTrue(board.checkAccusation("Parlor", "Leslie Zayne", "Saw"));
		
		//Check that various wrong accusations return false
		assertFalse(board.checkAccusation("Kitchen", "Ms. Pots", "Knife"));
		assertFalse(board.checkAccusation("Hammer", "Office", "Gym"));
	}
	
	//Test cards are dealt correctly.
	@Test
	public void testCardsDealt() {
		//Test total size of deck is correct.
		int total = 0;
		int a = board.getPlayer(0).getHandSize();
		for(int i = 0; i < board.getPlayerArray().size(); i++) {
			total = total + board.getPlayer(i).getHandSize();
		}
		total = total + 3; //Include cards from solution.
		assertEquals(total, board.getDeck().size());
		
		//Test that all players have roughly the same number of cards.
		int avgHandSize = (board.getDeck().size() - 3)/board.getPlayerArray().size();
		int min = avgHandSize - 1;
		int max = avgHandSize + 1;
		assertTrue(board.getPlayer(0).getHandSize() < max && board.getPlayer(0).getHandSize() > min);
		
		//Test same card is not given to more than one player.
		ArrayList<Card> checkCards = new ArrayList();
		for(int i = 0; i < board.getPlayerArray().size(); i++) {
			for(int j = 0; j < 3; j++) {
				assertFalse(checkCards.contains(board.getPlayer(i).getHand(j)));
				checkCards.add(board.getPlayer(i).getHand(j));
			}
		}
		
	}
}
