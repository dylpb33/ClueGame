package clueGame;

public class Card {
	private String cardName;
	public enum CardType {ROOM,PERSON,WEAPON};
	private CardType type;
	String room = "room";
	String person = "person";
	String weapon = "weapon";
	
	//Default constructor
	public Card() {
		
	}
	
	//Constructor sets cardName
	public Card(String cardName) {
		this.cardName = cardName;
	}
	
	//Sets the CardType based on an input string.
	public void setCardType(String cardType) {
		if(cardType == "Room") {
			type = type.ROOM;
		}
		else if(cardType == "Player") {
			type = type.PERSON;
		}
		else {
			type = type.WEAPON;
		}
	}
	
	//Returns string based on CardType.
	public String getCardType() {
		if(type == type.ROOM) {
			return room;
		}
		else if(type == type.PERSON) {
			return person;
		}
		else {
			return weapon;
		}
	}
	
	//Determines whether a card equals another.
	public boolean equals(Card target){
		Card c = null;
		if(target == c) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Return cardName.
	public String getCardName() {
		return cardName;
	}

}
