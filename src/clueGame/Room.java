package clueGame;

public class Room {
	
	public Room() {
		
	}
	
	String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	
	public String getName() {
		return name;
	}

	public BoardCell getLabelCell() {
		return labelCell;
	}
	
	public void setLabelCell(BoardCell c) {
		labelCell = c;
	}

	public BoardCell getCenterCell() {
		return centerCell;
	}
	
	public void setCenterCell(BoardCell c) {
		centerCell = c;
	}
	
	

}
