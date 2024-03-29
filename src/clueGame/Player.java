package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
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
	protected boolean isFinished;
	protected boolean canDisprove = true;
	protected boolean inSuggestion = false;
	Board board = Board.getInstance();
	
	public void player() {
		
	}
	
	// drawing the player in their starting location
	public void drawPlayer(int width,  int height, int xOffset, int yOffset, Graphics g) {
		g.setColor(this.color);
		g.fillOval(xOffset , yOffset , width , height );
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
		else if("red".equals(playerColor)) {
			color = Color.RED;
		}
		else if("blue".equals(playerColor)) {
			color = Color.BLUE;
		}
		else if("green".equals(playerColor)) {
			color = Color.GREEN;
		}
		else if("white".equals(playerColor)) {
			color = Color.WHITE;
		}
		else if("pink".equals(playerColor)) {
			color = Color.PINK;
		}
	}
	
	public void Move() {
		
	};
	
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
		Card newCard = null;
		ArrayList<Card> newArray = new ArrayList<Card>();

		for (int j = 0; j < hand.size(); j++) {
			if (room == hand.get(j).getCardName()) {
				newCard = hand.get(j);
				newArray.add(newCard);
			}
			if (person == hand.get(j).getCardName()) {
				newCard = hand.get(j);
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
	
	public boolean getIsFinished(){
		return isFinished;
	}
	
	public void setIsFinished(boolean b){
		isFinished = b;
	}
	
	public void setInSuggestion(boolean b) {
		inSuggestion = b;
	}
	
	public boolean getCanDisprove() {
		return canDisprove;
	}
	
	public void setCanDisprove(boolean b) {
		canDisprove = b;
	}
	
	public boolean getInSuggestion() {
		return inSuggestion;
	}

	public void Move(BoardCell c) {
		
	}
	
}
