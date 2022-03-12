// Authors: Jasmine Hernandez, Dylan Blaine

package clueGame;

import java.util.*;

import experiment.TestBoardCell;

public class BoardCell {

	private int row;
	private int col;
	private char initial;
	private DoorDirection doorDirection;
	private boolean roomLabel = false;
	private boolean roomCenter = false;
	private boolean isDoor = false;
	private char secretPassage;
	private boolean isSecretPassage = false;
	private Set<BoardCell> adjList;
	private Boolean isRoom = false;
	private Boolean isOccupied = false;
	
	
	//Constructor for each cell
	public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
		adjList = new HashSet<BoardCell>();
		doorDirection = DoorDirection.NONE;
	}
	
	// Returns initial for cell
	public char getInitial() {
		return initial;
	}
	
	// Sets cell initial
	public void setInitial(char initial) {
		this.initial = initial;
	}
	
	// Returns direction of door cells
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	// Sets direction for door cells
	public void setDoorDirection(DoorDirection doorDirection) {
		this.doorDirection = doorDirection;
	}
	
	// Returns whether a cell is a room label
	public boolean isLabel() {
		return roomLabel;
	}
	
	// Sets room label
	public void setRoomLabel(boolean roomLabel) {
		this.roomLabel = roomLabel;
	}
	
	// Returns whether a cell is the room center
	public boolean isRoomCenter() {
		return roomCenter;
	}
	
	// Sets room center
	public void setRoomCenter(boolean roomCenter) {
		this.roomCenter = roomCenter;
	}
	
	// Returns whether cell is a doorway
	public boolean isDoorway() {
		return isDoor;
	}
	
	// Sets cell as a door
	public void setDoor(boolean isDoor) {
		this.isDoor = isDoor;
	}
	
	// Returns character of secret passage
	public char getSecretPassage() {
		return secretPassage;
	}
	
	// Sets secret passage with char
	public void setSecretPassage(char secretPassage) {
		this.secretPassage = secretPassage;
	}
	
	// Sets secret passage with boolean
	public void setSecretPassage(boolean isSecretPassage) {
		this.isSecretPassage = isSecretPassage;
	}
	
	// Returns whether a cell is a secret passage
	public boolean isSecretPassage() {
		return isSecretPassage;
	}
	
	
	// Returns whether a cell is a room
	public Boolean getIsRoom() {
		return isRoom;
	}
	
	// Sets whether a cell is part of a room
	public void setIsRoom(Boolean isRoom) {
		this.isRoom = isRoom;
	}
	
	// Returns whether a cell is occupied
	public Boolean getIsOccupied() {
		return isOccupied;
	}
	
	// Sets cell as occupied or unoccupied
	public void setOccupied(Boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
	// Adds cell to adjList
	public void setAdjList(BoardCell cell) {
		adjList.add(cell);
	}
	
	// Returns adjList for a cell
	public Set<BoardCell> getAdjList(){
		return adjList;
	}
	

}
