package clueGame;

public class Solution {
	private Card room;
	private Card person;
	private Card weapon;
	
	//Default constructor
	public Solution(Card room, Card person, Card weapon) {
		this.room = room;
		this.person = person;
		this.weapon = weapon;
	}
	
	//Sets the solution variables.
	public void setSolution(Card room, Card person, Card weapon) {
		this.room = room;
		this.person = person;
		this.weapon = weapon;
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

	public void setSolutionRoom(Card room) {
		this.room = room;
		
	}

	public void setSolutionPerson(Card person) {
		this.person = person;
		
	}

	public void setSolutionWeapon(Card weapon) {
		this.weapon = weapon;
	
	}

}
