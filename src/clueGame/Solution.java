package clueGame;

public class Solution {
	private Card room;
	private Card person;
	private Card weapon;

	public Solution() {
		
	}
	
	public void setSolution(Card a, Card b, Card c) {
		room = a;
		person = b;
		weapon = c;
	}
	
	public Card getSolutionRoom() {
		return room;
	}
	
	public Card getSolutionPerson() {
		return person;
	}
	
	public Card getSolutionWeapon() {
		return weapon;
	}

}
