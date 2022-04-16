package clueGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import clueGame.Card.CardType;

public class ComputerPlayer extends Player{
	private static Board board;
	
	//Default constructor calls super.
	public ComputerPlayer() {
		super();
	}
	
	public ComputerPlayer(String name, int row, int column, String color) {
		this.name = name;
		this.row = row;
		this.column = column;
		setColor(color);
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
		//Create random object
		Random rand = new Random();
		
		//Add location to suggestion
		player.getSuggestion().add(location);
		
		//Add seen cards to seenCards array
		for(int i = 0; i < seen.size(); i++) {
			player.getSeenCards().add(seen.get(i));
		}
		
		//Create people and weapons arrays
		ArrayList<Card> people = new ArrayList<Card>();
		ArrayList<Card> weapons = new ArrayList<Card>();
		
		//If not in seen cards add cards to corresponding card type array
		for(int i = 0; i < possibleCards.size(); i++) {
			if(!player.getSeenCards().contains(possibleCards.get(i))) {
				if(possibleCards.get(i).getCardType().equals(CardType.PERSON)) {
					people.add(possibleCards.get(i));
				}
				if(possibleCards.get(i).getCardType().equals(CardType.WEAPON)) {
					weapons.add(possibleCards.get(i));
				}
				
			}
		}
		

		
		//Randomly select cards to add to suggestion
		player.getSuggestion().add(people.get(rand.nextInt(people.size())));
		player.getSuggestion().add(weapons.get(rand.nextInt(weapons.size())));
		
	}

	@Override
	public void Move() {
		board = board.getInstance();
		BoardCell c = this.selectTargets(board.getTargets());
		this.setRow(c.getRow());
		this.setColumn(c.getColumn());
	}
	

}
