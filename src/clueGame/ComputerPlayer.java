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
		// itterating through targets  to find doorways and walkways
		for (BoardCell cell : targets)
		{
			if (cell.isDoorway())
			{
				//  if doorway adding to  available list
				availableDoorways.add(cell);
			}
			if (cell.getInitial() == 'W')
			{
				// if walkway adding to available list
				availablePaths.add(cell);
			}
		}

		// if the doorway is not occupied select it
		if (!availableDoorways.isEmpty())
		{
			int options = availableDoorways.size();
			Random rand = new Random(); 
			int selected = rand.nextInt(options); 
			return availableDoorways.get(selected);
		}
		else
		{
		// otherwise select any random room
			int options = availablePaths.size();
			Random rand = new Random();
			int selected = rand.nextInt(options);
			return availablePaths.get(selected);
		}

	}
	
	
	public Solution createSuggestion(Card location, ArrayList<Card> possibleCards, ArrayList<Card> seen) {
		//Create random object
		Random rand = new Random();
		
		//Add location to suggestion
		Card roomCard = location;
		
		//Add seen cards to seenCards array
		for(int i = 0; i < seen.size(); i++) {
			this.getSeenCards().add(seen.get(i));
		}
		
		//Create people and weapons arrays
		ArrayList<Card> people = new ArrayList<Card>();
		ArrayList<Card> weapons = new ArrayList<Card>();
		
		//If not in seen cards add cards to corresponding card type array
		for(int i = 0; i < possibleCards.size(); i++) {
			if(!this.getSeenCards().contains(possibleCards.get(i))) {
				if(possibleCards.get(i).getCardType().equals(CardType.PERSON)) {
					people.add(possibleCards.get(i));
				}
				if(possibleCards.get(i).getCardType().equals(CardType.WEAPON)) {
					weapons.add(possibleCards.get(i));
				}
				
			}
		}
		
		//Randomly select cards to add to suggestion
		Card personCard = people.get(rand.nextInt(people.size()));
		Card weaponCard = weapons.get(rand.nextInt(weapons.size()));
		
		return new Solution(roomCard, personCard, weaponCard);
		
		
	}

	@Override
	public void Move() {
		board = board.getInstance();
		// getting the cell the player is currently in and setting it to not occupied
		board.getCell(this.row, this.column).setOccupied(false);
		BoardCell c = this.selectTargets(board.getTargets());
		// moving them to the clicked cell
		this.setRow(c.getRow());
		this.setColumn(c.getColumn());
		// setting the clicked cell to occupied
		c.setOccupied(true);
	}
	

}
