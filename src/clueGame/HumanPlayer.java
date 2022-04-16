package clueGame;

public class HumanPlayer extends Player{

	public HumanPlayer() {
		super();
	}
	
	public HumanPlayer(String name, int row, int column, String color) {
		this.name = name;
		this.row = row;
		this.column = column;
		this.playerColor = color;
	}

	@Override
	public void Move(BoardCell c) {
		c.setOccupied(false);
		this.setRow(c.getRow());
		this.setColumn(c.getColumn());
		c.setOccupied(true);
	}
	


}
