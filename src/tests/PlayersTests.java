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
	public void testHuman() {
		assertTrue(board.getPlayer(0) instanceof HumanPlayer);
		board.getPlayer(0).getName
	}
	
	@Test
	public void testComputer() {
		assertTrue(board.getPlayer(1) instanceof ComputerPlayer);
	}
	
	@Test
	public void testDeck() {
		
	}
	
	@Test
	public void testSolution() {
		
	}
	
	@Test
	public void testCardsDealt() {
		
	}
}
