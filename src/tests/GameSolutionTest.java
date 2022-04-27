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
		assertTrue(newComputer.disproveSuggestion("Wine Cellar", "Justin Prince", "Hammer") == null);
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
		ArrayList<Player> playersArray = new ArrayList<Player>();
		Card newCard;
		HumanPlayer newHuman = new HumanPlayer();
		newCard = new Card("Office");
		newHuman.updateHand(newCard);
		newCard = new Card("Ms. Pots");
		newHuman.updateHand(newCard);
		newCard = new Card("Knife");
		newHuman.updateHand(newCard);
		playersArray.add(newHuman);
		
		ComputerPlayer newComputer = new ComputerPlayer();
		newCard = new Card("Sunroom");
		newComputer.updateHand(newCard);
		newCard = new Card("Moreen Ridley");
		newComputer.updateHand(newCard);
		newCard = new Card("Hammer");
		newComputer.updateHand(newCard);
		playersArray.add(newComputer);
		
		ComputerPlayer newComputer2 = new ComputerPlayer();
		newCard = new Card("Parlor");
		newComputer2.updateHand(newCard);
		newCard = new Card("Pacman");
		newComputer2.updateHand(newCard);
		newCard = new Card("Wire");
		newComputer2.updateHand(newCard);
		playersArray.add(newComputer2);
		
		
		//Suggestion no one can disprove (newHuman is accuser)
		assertEquals(null, board.handleSuggestion("Wine Cellar", "Justin Prince", "Ice Pick", newHuman));
		assertEquals(null, board.handleSuggestion("Wine Cellar", "Justin Prince", "Ice Pick", newHuman));
		
		//Suggestion only accusing player can disprove returns null(newHuman is accuser)
		assertEquals(null, board.handleSuggestion("Office", "Justin Prince", "Ice Pick", newHuman));
		assertEquals(null, board.handleSuggestion("Wine Cellar", "Justin Prince", "Ice Pick", newHuman));
		
		//Suggestion only human can disprove returns answer (newComputer is accuser)
		assertEquals(null, board.handleSuggestion("Office", "Justin Prince", "Ice Pick", newComputer));
		assertEquals(null, board.handleSuggestion("Wine Cellar", "Justin Prince", "Ice Pick", newComputer));
		
		//Suggestion that two players can disprove, correct player returns answer (newHuman is accuser)
		assertTrue(board.handleSuggestion("Sunroom", "Pacman", "Ice Pick", newHuman) == null);
		assertTrue(board.handleSuggestion("Office", "Moreen Ridley", "Wire", newHuman) == null);
		
	}

}
