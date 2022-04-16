// Authors: Jasmine Hernandez, Dylan Blaine

package clueGame;

import java.awt.Color;
import java.awt.Graphics;
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
	private Boolean highlighted = false;
	
	//Constructor for each cell
	public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
		adjList = new HashSet<BoardCell>();
		doorDirection = DoorDirection.NONE;
	}
	
	//Draws each cell on board
	public void drawCell(int width,  int height, int xOffset, int yOffset, Graphics g) {
		// drawing the unused spaces black
		if(this.getInitial() == 'X') {
			g.setColor(Color.BLACK);
			g.fillRect(xOffset, yOffset, width, height);
		}
		// drawing the walkways yellow
		if(this.getInitial() == 'W') {
			g.setColor(Color.CYAN);
			g.fillRect(xOffset , yOffset , width , height );
			g.setColor(Color.BLACK);
			g.drawRect(xOffset, yOffset, width, height);
		}
		// drawing the doors
		if(isDoor) {
			if (this.doorDirection == doorDirection.UP) {
				g.setColor(Color.BLUE);
				g.fillRect(xOffset, yOffset-5, width, 5);
			}
			else if (this.doorDirection == doorDirection.DOWN) {
				g.setColor(Color.BLUE);
				g.fillRect(xOffset, yOffset + height, width, 5);
			}
			else if (this.doorDirection == doorDirection.LEFT) {
				g.setColor(Color.BLUE);
				g.fillRect(xOffset-5, yOffset, 5, height);
			}
			else if (this.doorDirection == doorDirection.RIGHT) {
				g.setColor(Color.BLUE);
				g.fillRect(xOffset + width, yOffset, 5, height);
			}
		}
		// drawing the rooms
		if (this.isRoom) {
			Color overlay = new Color(0, 0, 255, 30);
			g.setColor(overlay);
			g.fillRect(xOffset, yOffset, width, height);
		}
		
	}
	
	public void highlightTargets (int cellWidth, int cellHeight, int xOffset, int yOffset, Graphics g) {
		if(this.getInitial() == 'W') {
			g.setColor(Color.YELLOW);
			g.fillRect(xOffset , yOffset , cellWidth , cellHeight );
			g.setColor(Color.BLACK);
			g.drawRect(xOffset, yOffset, cellWidth, cellHeight);
		}
		else {
			g.setColor(Color.YELLOW);
			g.fillRect(xOffset , yOffset , cellWidth , cellHeight );
		}
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
	
	// Sets whether a cell is a door
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
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return col;
	}
	
	public void setHighlighted(Boolean b) {
		highlighted = b;
	}
	

}
