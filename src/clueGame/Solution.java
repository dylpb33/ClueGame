package clueGame;

public class Solution {
	private Card room;
	private Card person;
	private Card weapon;
	
	//Default constructor
	public Solution() {
		super();
	}
	
	//Sets the solution variables.
	public void setSolution(Card a, Card b, Card c) {
		room = a;
		person = b;
		weapon = c;
	}
	
	//Returns room card in solution.
	public Card getSolutionRoom() {
		return room;
	}
	
	//Returns person card in solution.
	public Card getSolutionPerson() {
		return person;
	}
	
	//Returns weapon card in solution.
	public Card getSolutionWeapon() {
		return weapon;
	}

	public void setSolutionRoom(Card r) {
		room = r;
		
	}

	public void setSolutionPerson(Card p) {
		person = p;
		
	}

	public void setSolutionWeapon(Card w) {
		weapon = w;
	
	}

}
