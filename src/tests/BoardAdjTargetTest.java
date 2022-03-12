package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTest {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
	}
	
	// Locations with only walkways as adjacent locations
	// These tests are Yellow on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on bottom edge of board, just one walkway piece
		Set<BoardCell> testList = board.getAdjList(23, 5);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCell(22, 5)));
		
		// Test near a door but not adjacent
		testList = board.getAdjList(1, 6);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(1, 5)));
		assertTrue(testList.contains(board.getCell(0, 6)));
		assertTrue(testList.contains(board.getCell(2, 6)));

		// Test adjacent to walkways
		testList = board.getAdjList(13, 6);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(14, 6)));
		assertTrue(testList.contains(board.getCell(12, 6)));
		assertTrue(testList.contains(board.getCell(13, 5)));
		assertTrue(testList.contains(board.getCell(13, 7)));

		// Test next to closet
		testList = board.getAdjList(7,13);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(6, 13)));
		assertTrue(testList.contains(board.getCell(8, 13)));
		assertTrue(testList.contains(board.getCell(7, 14)));
	
	}

	
	// Locations that are doorways and their walkways
	// These cells are LIGHT ORANGE on the planning spreadsheet
	@Test
	public void testAdjacencyDoor()
	{
		Set<BoardCell> testList = board.getAdjList(9, 14);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(11, 18)));
		assertTrue(testList.contains(board.getCell(10, 14)));
		assertTrue(testList.contains(board.getCell(8, 14)));
		assertTrue(testList.contains(board.getCell(9, 13)));

		testList = board.getAdjList(6, 18);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(3, 18)));
		assertTrue(testList.contains(board.getCell(6, 17)));
		assertTrue(testList.contains(board.getCell(6, 19)));
		
		testList = board.getAdjList(21, 5);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(22, 1)));
		assertTrue(testList.contains(board.getCell(22, 5)));
		assertTrue(testList.contains(board.getCell(20, 5)));
	}
	

	// Locations that are connected by a secret passage
	// These cells are PURPLE on the planning spreadsheet
	@Test
	public void testAdjacenciesRooms()
	{
		// we want to test a couple of different rooms.
		// First, the Laundry Room that has 2 doors and a secret room
		Set<BoardCell> testList = board.getAdjList(3, 18);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(3, 14)));
		assertTrue(testList.contains(board.getCell(6, 18)));
		assertTrue(testList.contains(board.getCell(19, 18)));
		
		// now test the WineCellar 
		testList = board.getAdjList(7, 2);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(4, 2)));
		assertTrue(testList.contains(board.getCell(6, 5)));
		assertTrue(testList.contains(board.getCell(11, 18)));
		
		// one more room, the kitchen
		testList = board.getAdjList(11, 18);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(15, 18)));
		assertTrue(testList.contains(board.getCell(9, 14)));
		assertTrue(testList.contains(board.getCell(7, 2)));
	}
	
	// Locations within rooms not center (should have empty adjacency list)
	@Test
	public void testAdjacenciesRoomsNotCenter()
	{
		// 
		Set<BoardCell> testList = board.getAdjList(2, 7);
		assertEquals(0, testList.size());
		assertFalse(testList.contains(board.getCell(1, 9)));
		assertFalse(testList.contains(board.getCell(1, 8)));
		assertFalse(testList.contains(board.getCell(3, 7)));
		
		// 
		testList = board.getAdjList(2, 16);
		assertEquals(0, testList.size());
		assertFalse(testList.contains(board.getCell(3, 18)));
		assertFalse(testList.contains(board.getCell(3, 14)));
		assertFalse(testList.contains(board.getCell(4, 16)));
		
		// 
		testList = board.getAdjList(19, 10);
		assertEquals(0, testList.size());
		assertFalse(testList.contains(board.getCell(20, 8)));
		assertFalse(testList.contains(board.getCell(16, 9)));
		assertFalse(testList.contains(board.getCell(17, 8)));
	}
	
	// Targets calculated when leaving a room with a secret passage
	// These are WHITE on the planning spreadsheet
	@Test
	public void testTargetsInGym() {
		// test a roll of 1
		board.calcTargets(board.getCell(19, 18), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(18, 13)));	
		assertTrue(targets.contains(board.getCell(19, 13)));
		assertTrue(targets.contains(board.getCell(20, 13)));
		assertTrue(targets.contains(board.getCell(3, 18)));
		
		// test a roll of 3
		board.calcTargets(board.getCell(19, 18), 3);
		targets= board.getTargets();
		assertEquals(15, targets.size());
		assertTrue(targets.contains(board.getCell(18, 12)));
		assertTrue(targets.contains(board.getCell(18, 11)));	
		assertTrue(targets.contains(board.getCell(20, 13)));	
		
		// test a roll of 4
		board.calcTargets(board.getCell(19, 18), 4);
		targets= board.getTargets();
		assertEquals(22, targets.size());
		assertTrue(targets.contains(board.getCell(19, 12)));
		assertTrue(targets.contains(board.getCell(3, 18)));	
		assertTrue(targets.contains(board.getCell(16, 13)));
		assertTrue(targets.contains(board.getCell(17, 11)));	
	}
	
	// Targets calculated when leaving a room without a secret passage
	// These are WHITE on the planning spreadsheet
	@Test
	public void testTargetsInGameRoom() {
		// test a roll of 1
		board.calcTargets(board.getCell(16, 2), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(13, 2)));
		assertTrue(targets.contains(board.getCell(18, 2)));	
		
		// test a roll of 3
		board.calcTargets(board.getCell(16, 2), 3);
		targets= board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCell(18, 4)));
		assertTrue(targets.contains(board.getCell(18, 0)));	
		assertTrue(targets.contains(board.getCell(13, 0)));
		assertTrue(targets.contains(board.getCell(12, 1)));	
		
		// test a roll of 4
		board.calcTargets(board.getCell(16, 2), 4);
		targets= board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCell(12, 0)));
		assertTrue(targets.contains(board.getCell(11, 1)));	
		assertTrue(targets.contains(board.getCell(18, 5)));
		assertTrue(targets.contains(board.getCell(13, 3)));	
	}

	// Targets that allow the user to enter a room
	// These are PURPLE on the planning spreadsheet
	@Test
	public void testTargetsAtDoor() {
		// test a roll of 1, at door
		board.calcTargets(board.getCell(2, 5), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(1, 5)));	
		assertTrue(targets.contains(board.getCell(3, 5)));	
		
		// test a roll of 3
		board.calcTargets(board.getCell(2, 5), 3);
		targets= board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCell(3, 5)));
		assertTrue(targets.contains(board.getCell(2, 6)));
		assertTrue(targets.contains(board.getCell(0, 6)));	
		assertTrue(targets.contains(board.getCell(3, 7)));
		assertTrue(targets.contains(board.getCell(4, 6)));	
		
		// test a roll of 4
		board.calcTargets(board.getCell(2, 5), 4);
		targets= board.getTargets();
		assertEquals(10, targets.size());
		assertTrue(targets.contains(board.getCell(4, 7)));
		assertTrue(targets.contains(board.getCell(4, 3)));
		assertTrue(targets.contains(board.getCell(4, 5)));	
		assertTrue(targets.contains(board.getCell(5, 6)));
		assertTrue(targets.contains(board.getCell(1, 6)));	
	}
	
	// Targets along walkways, at various distances
	@Test
	public void testTargetsInWalkway1() {
		// test a roll of 1
		board.calcTargets(board.getCell(18, 0), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCell(18, 1)));
		
		// test a roll of 3
		board.calcTargets(board.getCell(18, 0), 3);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(18, 3)));
		assertTrue(targets.contains(board.getCell(16, 2)));
		
		// test a roll of 4
		board.calcTargets(board.getCell(18, 0), 4);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(18, 4)));
		assertTrue(targets.contains(board.getCell(16, 2)));
	}


	@Test
	public void testTargetsOccupied() {
		// test a roll of 4 blocked 2 down
		board.getCell(1, 6).setOccupied(true);
		board.calcTargets(board.getCell(0, 6), 4);
		board.getCell(1, 6).setOccupied(false);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(3, 5)));
		assertTrue(targets.contains(board.getCell(2, 6)));	
		assertFalse( targets.contains( board.getCell(1, 6)));
		assertFalse( targets.contains( board.getCell(3, 7)));
	
		// we want to make sure we can get into a room, even if flagged as occupied
		board.getCell(20, 19).setOccupied(true);
		board.getCell(16, 7).setOccupied(true);
		board.calcTargets(board.getCell(16, 8), 1);
		board.getCell(20, 19).setOccupied(false);
		board.getCell(16, 7).setOccupied(false);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(20, 8)));	
		assertTrue(targets.contains(board.getCell(15, 8)));	
		assertTrue(targets.contains(board.getCell(16, 9)));	
		
		// check leaving a room with a blocked doorway
		board.getCell(9, 14).setOccupied(true);
		board.calcTargets(board.getCell(11, 18), 3);
		board.getCell(9, 14).setOccupied(false);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(7, 2)));
		assertTrue(targets.contains(board.getCell(15, 16)));	
		assertTrue(targets.contains(board.getCell(15, 20)));

	}
}