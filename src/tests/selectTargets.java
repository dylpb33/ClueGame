package tests;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import clueGame.*;

public class selectTargets {
	private static Board board;
	private static Player player;

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
	
	@Test
	public void testSelectingATarget() {
		// One room is exactly 2 away
		board.calcTargets(board.getCell(12, 3), 4);
		// will be passing in targets into the pickLocation function that is called by ComputerPlayer which extends player
		Set<BoardCell> targets= board.getTargets();
		// testing the calculated targets 
		assertTrue(targets.contains(board.getCell(16, 2)));
		assertTrue(targets.contains(board.getCell(11, 0)));
		// Last target tested is doorway 
		assertTrue(targets.contains(board.getCell(13, 2)));

		// need to call ComputerPlayer with the pickLocation function and return the selected position
		ComputerPlayer computer = new ComputerPlayer();
		BoardCell selected = computer.selectTargets(targets);
		assertEquals(board.getCell(13, 2), selected);

	}

}
