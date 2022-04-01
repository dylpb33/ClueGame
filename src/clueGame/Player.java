package clueGame;

import java.util.ArrayList;

public abstract class Player {
	private String name;
	private String color;
	private int row;
	private int column;
	private ArrayList<Card> hand = new ArrayList();
	
	public Player() {

	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void setColor(String c) {
		color = c;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setRow(int i) {
		row = i;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setColumn(int i) {
		column = i;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void updateHand(Card card){
		
	}
	
	public int getHandSize() {
		return hand.size();
	}

}
