package clueGame;

import java.util.ArrayList;

public abstract class Player {
	private String name;
	private String color;
	private int row;
	private int column;
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	
	//Sets player name.
	public void setName(String name) {
		this.name = name;
	}
	
	//Return player name.
	public String getName() {
		return name;
	}
	
	//Sets player color.
	public void setColor(String color) {
		this.color = color;
	}
	
	//Return player color.
	public String getColor() {
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

}
