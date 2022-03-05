package clueGame;

public class Room {
	
	public Room() {
		
	}
	
	String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	
	public String getName() {
		String string = "";
		return string;
	}

	public BoardCell getLabelCell() {
		return new BoardCell(0,0);
	}

	public BoardCell getCenterCell() {
		return new BoardCell(0,0);
	}
	

}
