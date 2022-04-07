package tests;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import clueGame.*;

public class ComputerAITest {
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
		// will be passing in targets into the selectTargets() function that is called by ComputerPlayer which extends player
		Set<BoardCell> targets= board.getTargets();
		// testing the calculated targets 
		assertTrue(targets.contains(board.getCell(16, 2)));
		assertTrue(targets.contains(board.getCell(11, 0)));
		// Last target tested is doorway 
		assertTrue(targets.contains(board.getCell(13, 2)));

		// need to call ComputerPlayer with the selectTargets() function and return the selected position
		ComputerPlayer computer = new ComputerPlayer();
		BoardCell selected = computer.selectTargets(targets);
		assertEquals(board.getCell(13, 2), selected);

	}
	
	@Test
	public void createSuggestion() {
		ComputerPlayer newComputer = new ComputerPlayer();
		
		String room = "Room";
		String person = "Player";
		String weapon = "Weapon";
		
		//Set room as kitchen
		newComputer.setColumn(18);
		newComputer.setRow(11);
		
		//Set location
		String locationStr = board.getRoom(board.getCell(newComputer.getRow(), newComputer.getColumn())).getName();
		Card location = new Card(locationStr);
		
		//Create deck of cards for tests
		ArrayList<Card> possibleCards = new ArrayList<Card>();
		ArrayList<Card> seen = new ArrayList<Card>();
		Card room1 = new Card("Office");
		room1.setCardType(room);
		possibleCards.add(room1);
		Card room2 = new Card("Sunroom");
		room2.setCardType(room);
		possibleCards.add(room2);
		Card person1 = new Card("Moreen Ridley");
		person1.setCardType(person);
		possibleCards.add(person1);
		Card person2 = new Card("Ms. Pots");
		person2.setCardType(person);
		possibleCards.add(person2);
		Card weapon1 = new Card("knife");
		weapon1.setCardType(weapon);
		possibleCards.add(weapon1);
		Card weapon2 = new Card("Saw");
		weapon2.setCardType(weapon);
		possibleCards.add(weapon2);
		
		seen.add(weapon1);
		seen.add(person1);
		//Create suggestion
		newComputer.createSuggestion(location, possibleCards, newComputer, seen);
		
		//Room matches current location
		assertEquals("Kitchen", locationStr);
		
		//If only one weapon not seen, it's selected
		assertTrue(newComputer.getSuggestion().contains(weapon2));
		assertFalse(newComputer.getSuggestion().contains(weapon1));
		
		//If only one person not seen, it's selected (can be same test as weapon)
		assertTrue(newComputer.getSuggestion().contains(person2));
		assertFalse(newComputer.getSuggestion().contains(person1));
		
		//If multiple weapons not seen, one of them is randomly selected
		seen.remove(weapon1);
		assertTrue(newComputer.getSuggestion().contains(weapon1) || newComputer.getSuggestion().contains(weapon2));
		assertFalse(newComputer.getSuggestion().contains(weapon1) && newComputer.getSuggestion().contains(weapon2));
		
		//If multiple persons not seen, one of them is randomly selected
		seen.remove(person1);
		assertTrue(newComputer.getSuggestion().contains(person1) || newComputer.getSuggestion().contains(person2));
		assertFalse(newComputer.getSuggestion().contains(person1) && newComputer.getSuggestion().contains(person2));
		
		
	}
	

}
