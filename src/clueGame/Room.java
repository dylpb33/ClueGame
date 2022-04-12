// Authors: Jasmine Hernandez, Dylan Blaine

package clueGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Room {
	
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	private BoardCell secretPassageCell;
	
	public void drawRoomName(int xOffset, int yOffset, Graphics g, int cellWidth, int cellHeight) {
		g.setColor(Color.BLUE);
		double fontSize = (cellWidth * 0.25) + (cellHeight * 0.25);
		Font f = new Font("Comic Sans MS", Font.BOLD, (int) fontSize);
		g.setFont(f);
		g.drawString(name, xOffset, yOffset);
	}
	
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
