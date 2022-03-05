package clueGame;

import java.util.*;

public class BoardCell {
	
	int row;
	int col;
	char initial;
	private DoorDirection doorDirection;
	boolean roomLabel;
	boolean roomCenter;
	char secretPassage;
	private Set<BoardCell> adjList;
	
	public BoardCell(int row, int col) {
		row= 0;
		col= 0;
	}
	
	public void addAdj(BoardCell cell) {
		adjList.add(cell);
	}

	public boolean isDoorway() {
		return false;
	}

	public DoorDirection getDoorDirection() {
		return DoorDirection.NONE;
	}

	public boolean isLabel() {
		return false;
	}

	public boolean isRoomCenter() {
		return false;
	}

	public char getSecretPassage() {
		char ch = 'z';
		return ch;
	}
	

}
