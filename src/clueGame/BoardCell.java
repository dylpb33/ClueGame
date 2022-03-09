// Authors: Jasmine Hernandez, Dylan Blaine

package clueGame;

import java.util.*;

import experiment.TestBoardCell;

public class BoardCell {
	
	int row;
	int col;
	private char initial;
	private DoorDirection doorDirection;
	boolean roomLabel = false;
	boolean roomCenter = false;
	boolean isDoor = false;
	char secretPassage;
	boolean isSecretPassage = true;
	private Set<BoardCell> adjList = new HashSet<BoardCell>();
	private Boolean isRoom = false;
	private Boolean isOccupied = false;
	
	public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public void setInitial(char initial) {
		this.initial = initial;
	}

	public void addAdj(BoardCell cell) {
		adjList.add(cell);
	}
	//Returns the adjacency list as a set
	public Set<BoardCell> getAdjList(){
		return adjList;
	}

	public boolean isDoorway() {
		return isDoor;
	}

	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

	public void setDoorDirection(DoorDirection doorDirection) {
		this.doorDirection = doorDirection;
	}

	public boolean isLabel() {
		return roomLabel;
	}

	public boolean isRoomCenter() {
		return roomCenter;
	}

	public char getInitial() {
		return initial;
	}

	public char getSecretPassage() {
		return secretPassage;
	}
	
	
	//Sets cell as occupied
	public void setOccupied(boolean b) {
		if (b == true) {
			isOccupied = true;
		}
		else {
			isOccupied = false;
		}
	}
		
	//Returns whether a cell is occupied
	public boolean getOccupied() {
			return isOccupied;
	}	
	//Returns whether player is in a room
	public boolean isRoom() {
		 return isRoom;
	}	
		
	//Sets current room
	public void setRoom(boolean b) {
		if (b == true) {
			isRoom = true;
		}
		else {
			isRoom = false;
		}
	}
	

}
