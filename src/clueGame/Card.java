package clueGame;

public class Card {
	private String cardName;

	public Card() {
		// TODO Auto-generated constructor stub
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
