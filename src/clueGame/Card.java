package clueGame;

public class Card {
	private String cardName;
	public enum CardType {ROOM,PERSON,WEAPON};
	private CardType type;

	public Card(String s) {
		cardName = s;
	}
	
	public void setCardType(String s) {
		if(s == "r") {
			type = type.ROOM;
		}
		else if(s == "p") {
			type = type.PERSON;
		}
		else {
			type = type.WEAPON;
		}
	}
	
	public String getCardType() {
		String r = "room";
		String p = "person";
		String w = "weapon";
		if(type == type.ROOM) {
			return r;
		}
		else if(type == type.PERSON) {
			return p;
		}
		else {
			return w;
		}
	}
	
	public boolean equals(Card target){
		Card c = null;
		if(target == c) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getCardName() {
		return cardName;
	}

}
