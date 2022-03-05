package experiment;

import java.util.*;

public class TestBoardCell {
	private int row;
	private int col;
	private Boolean isRoom;
	private Boolean isOccupied;
	Set<TestBoardCell> adjList = new HashSet<TestBoardCell>();
	
	//Constructor that sets the row and column of the cell
	public TestBoardCell(int r, int c) {
		row = r;
		col = c;
	}

	//Adds cell to adjacency list
	public void addAdjacency(TestBoardCell cell) {
		adjList.add(cell);
	}
	
	//Returns the adjacency list as a set
	public Set<TestBoardCell> getAdjList(){
		return adjList;
	}
	
	//Sets the current room
	public void setRoom(boolean b) {
	
	}
	
	//Returns whether player is in a room
	public boolean isRoom() {
	 return true;
	}
	
	//Sets cell as occupied
	public void setOccupied(boolean b) {
		
	}
	
	//Returns whether a cell is occupied
	public boolean getOccupied() {
		return true;
	}
}
