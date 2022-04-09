package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public abstract class Player {
	protected String name;
	protected String playerColor;
	protected Color color;
	protected int row;
	protected int column;
	protected ArrayList<Card> hand = new ArrayList<Card>();
	protected ArrayList<Card> seenCards = new ArrayList<Card>();
	protected ArrayList<Card> suggestion = new ArrayList<Card>();
	
	public void player() {
		
	}
	
	//Sets player name.
	public void setName(String name) {
		this.name = name;
	}
	
	//Return player name.
	public String getName() {
		return name;
	}
	
	//Sets player color.
	public void setColor(String playerColor) {
		this.playerColor = playerColor;
		
		if("orange".equals(playerColor)) {
			color = Color.ORANGE;
		}
		if("red".equals(playerColor)) {
			color = Color.RED;
		}
		if("blue".equals(playerColor)) {
			color = Color.BLUE;
		}
	}
	
	//Return player color.
	public Color getColor() {
		return color;
	}
	
	//Sets row of player start location.
	public void setRow(int row) {
		this.row = row;
	}
	
	//Return player name.
	public int getRow() {
		return row;
	}
	
	//Sets column of player start location.
	public void setColumn(int column) {
		this.column = column;
	}
	
	//Return player name.
	public int getColumn() {
		return column;
	}
	
	//Add card to player hand
	public void updateHand(Card card){
		hand.add(card);
	}
	
	//Return player name.
	public Card getHand(int i) {
		return hand.get(i);
	}
	
	//Return player hand.
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	//Return size of player hand.
	public int getHandSize() {
		return hand.size();
	}
	
	public ArrayList<Card> getSeenCards(){
		return seenCards;
	}
	
	public void setSeenCards(Card c){
		seenCards.add(c);
	}
	
	public ArrayList<Card> getSuggestion(){
		return suggestion;
	}


	public Card disproveSuggestion(String room, String person, String weapon) {
		String newCard_ = null;
		Card newCard = new Card(newCard_);
		ArrayList<Card> newArray = new ArrayList<Card>();
		
			for (int j = 0; j < hand.size(); j++) {
				if (room == hand.get(j).getCardName()) {
					newCard =  hand.get(j);
					newArray.add(newCard);
				}
				if (person == hand.get(j).getCardName()) {
					newCard =  hand.get(j);
					newArray.add(newCard);
				}
				if (weapon == hand.get(j).getCardName()) {
					newCard = hand.get(j);
					newArray.add(newCard);
				}
			}
			
		if (newArray.size() > 1) {
			Random rand = new Random();
			int randNum = rand.nextInt(2);
			return newArray.get(randNum);
		}
		else {
			return newCard;
		}
	}
	
	
}
