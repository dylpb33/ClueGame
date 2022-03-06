package clueGame;

import java.util.*;

import experiment.TestBoardCell;

public class Board {
	
	private BoardCell[][] grid;
	int numRows;
	int numColumns;
	String layoutConfigFile;
	String setupConfigFile;
	private Map<Character, Room> roomMap;
	
	/*
	 * variable and methods used for singleton pattern
	 */
	private static Board theInstance = new Board();
	// constructor is private to ensure only one can be created
	private Board() {
		super() ;
	}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	/*
	 * initialize the board (since we are using singleton pattern)
	 */
	public void setConfigFiles(String layoutConfigFile, String setupConfigFile) {
		this.layoutConfigFile = layoutConfigFile;
		this.setupConfigFile = setupConfigFile;
	}
	
	public void initialize() {
	}
	
	public void loadSetupConfig() {
		
	}
	
	public void loadLayoutConfig() {
		
	}
	
	public Room getRoom(BoardCell cell) {
		return new Room();
	}
	public int getNumRows() {
		return 0;
	}
	public int getNumColumns() {
		return 0;
	}
	
	public BoardCell getCell(int row, int col) {
		return new BoardCell(0,0);
	}
	public Room getRoom(char c) {
		return new Room();
	}
	public BoardCell getRoom(BoardCell cell) {
		return new BoardCell(0,0);
	}
	

}
