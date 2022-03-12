// Authors: Jasmine Hernandez, Dylan Blaine

package clueGame;

public class Room {
	
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	private BoardCell secretPassageCell;
	
	//Returns name of room
	public String getName() {
		return name;
	}
	
	//Set name of each room
	public void setName(String s) {
		name = s;
	}
	
	//Returns label cell for room
	public BoardCell getLabelCell() {
		return labelCell;
	}
	
	//Sets label cell for a room
	public void setLabelCell(BoardCell c) {
		labelCell = c;
	}
	
	//Returns center cell for a room
	public BoardCell getCenterCell() {
		return centerCell;
	}
	
	//Sets center cell for a room
	public void setCenterCell(BoardCell c) {
		centerCell = c;
	}
	
	//Returns center cell for a room
	public BoardCell getSecretPassageCell() {
		return secretPassageCell;
	}
	
	//Sets center cell for a room
	public void setSecretPassageCell(BoardCell c) {
		secretPassageCell = c;
	}

}
