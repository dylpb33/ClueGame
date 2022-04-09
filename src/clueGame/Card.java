package clueGame;

public class Card {
	private String cardName;
	public enum CardType {ROOM,PERSON,WEAPON};
	private CardType type;
	
	//Constructor sets cardName
	public Card(String cardName) {
		this.cardName = cardName;
	}
	
	//Sets the CardType based on an input string.
	public void setCardType(CardType cardType) {
		if(cardType == CardType.ROOM) {
			type = CardType.ROOM;
		}
		else if(cardType == CardType.PERSON) {
			type = CardType.PERSON;
		}
		else {
			type = CardType.WEAPON;
		}
	}
	
	//Returns string based on CardType.
	public CardType getCardType() {
		return type;
	}
	
	//Return cardName.
	public String getCardName() {
		return cardName;
	}

}
