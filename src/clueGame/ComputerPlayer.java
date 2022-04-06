package clueGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player{
	
	//Default constructor calls super.
	public ComputerPlayer() {
		super();
	}
	
	public BoardCell selectTargets(Set<BoardCell> targets) {

		ArrayList<BoardCell> availablePaths = new ArrayList<BoardCell>();
		ArrayList<BoardCell> availableDoorways = new ArrayList<BoardCell>();
		for (BoardCell cell : targets)
		{
			if (cell.isDoorway())
			{
				availableDoorways.add(cell);
			}
			if (cell.getInitial() == 'W')
			{
				availablePaths.add(cell);
			}
		}

		if (!availableDoorways.isEmpty())
		{
			int options = availableDoorways.size();
			Random rand = new Random(); 
			int selected = rand.nextInt(options); 
			//System.out.println( options + " doorways exist");

			return availableDoorways.get(selected);
		}
		else
		{
			int options = availablePaths.size();
			Random rand = new Random();
			int selected = rand.nextInt(options);
			return availablePaths.get(selected);
		}

	} 
	
	public void createSuggestion() {
//		When the computer player enters a room, it must make a suggestion (human players do too, but human will select from dialog box)
//		Goal: Ensure the computer player learns something. Therefore don’t ask for card in hand or seen (i.e., suggestion should choose randomly from the “unseen” cards).
//		Test must ensure:
//		Weapon is chosen randomly from those not seen
//		Person is chosen randomly from those not seen
//		Room is the room entered
//		Issue: This test relies on a player that has a hand and a list of “seen” cards. What should we do?
//		Create methods to add cards to the hand and to a list of “seen” cards
//		Call those methods to set up your test scenario(s)
//		Design decision. Will you store Cards or Strings in the “hand” and “seen” lists?
//		Issue: Suggestion must be based on room the player is in. How do we handle this?
//		Pass the room in or create a setter so you can specify?
//		Design decision. UML specifies that Player knows location (row, column). How will you determine what room the player is in?
//		Issue: Method must return the suggestion
//		Design decision. How will you represent a “suggestion” – as three strings? Given that the Solution class contains three cards (room, person, weapon), perhaps as shown in the UML, a Solution can be used as the return type.  
//		You will probably want to use the same data structure for both checking an accusation and returning/disproving a suggestion.
//		
	}

}
