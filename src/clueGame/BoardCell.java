// Authors: Jasmine Hernandez, Dylan Blaine

package clueGame;

import java.util.*;

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
	private Set<BoardCell> adjList;
	
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

	public void setOccupied(boolean b) {

	}
	

}
