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
	
	
	public void createSuggestion(Card location, ArrayList<Card> possibleCards, ComputerPlayer player, ArrayList<Card> seen) {
		Random rand = new Random();
		player.getSuggestion().add(location);
		for(int i = 0; i < seen.size(); i++) {
			player.getSeenCards().add(seen.get(i));
		}
		ArrayList<Card> people = new ArrayList<Card>();
		ArrayList<Card> weapons = new ArrayList<Card>();
		String person = "person";
		String weapon = "weapon";
		
		for(int i = 0; i < possibleCards.size(); i++) {
			if(!player.getSeenCards().contains(possibleCards.get(i))) {
				if(possibleCards.get(i).getCardType().equals(person)) {
					people.add(possibleCards.get(i));
				}
				if(possibleCards.get(i).getCardType().equals(weapon)) {
					weapons.add(possibleCards.get(i));
				}
				
			}
		}
		
		player.getSuggestion().add(people.get(rand.nextInt(people.size())));
		player.getSuggestion().add(weapons.get(rand.nextInt(weapons.size())));
		
	}
	

}
