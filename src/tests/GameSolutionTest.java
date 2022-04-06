package tests;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import clueGame.*;

public class GameSolutionTest {
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
	public void checkAccusation() {
		board.setSolution(new Card("Parlor"), new Card("Leslie Zayne"), new Card("Saw"));
		
		//Check that the correct accusation returns true
		assertTrue(board.checkAccusation("Parlor", "Leslie Zayne", "Saw"));
		
		//solution with wrong person
		assertFalse(board.checkAccusation("Parlor", "Pacman", "Saw"));
		//solution with wrong weapon
		assertFalse(board.checkAccusation("Parlor", "Leslie Zayne", "Hammer"));
		//solution with wrong room
		assertFalse(board.checkAccusation("Office", "Leslie Zayne", "Saw"));
	}
	
	@Test
	public void disproveSuggestion() {
		Card newCard;
		ComputerPlayer newComputer = new ComputerPlayer();
		newCard = new Card("Office");
		newComputer.updateHand(newCard);
		newCard = new Card("Ms. Pots");
		newComputer.updateHand(newCard);
		newCard = new Card("Knife");
		newComputer.updateHand(newCard);
		
		// 	If player has only one matching card it should be returned
		assertEquals("Office", newComputer.disproveSuggestion("Office", "Pacman", "Wire").getCardName());
		// If player has no matching cards, null is returned
		assertEquals(null, newComputer.disproveSuggestion("Wine Cellar", "Justin Prince", "Hammer").getCardName());
		// 	If players has >1 matching card, returned card should be chosen randomly
		int countOffice = 0;
		int countKnife = 0;
		for (int i = 0; i < 100; i++) {
			String answer = newComputer.disproveSuggestion("Office", "Justin Prince", "Knife").getCardName();
			if (answer == "Office") {
				countOffice++;
			}
			if (answer == "Knife") {
				countKnife++;
			}
		}
		// If the player has multiple cards that match, return should be random.
		assertTrue(countOffice > 1);
		assertTrue(countKnife > 1);
	}
	
	@Test
	public void handleSuggestion() {
		
		
		
	}
	

}
