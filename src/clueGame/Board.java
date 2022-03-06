package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import experiment.TestBoardCell;

public class Board {
	
	private BoardCell[][] grid;
	private int numRows;
	private int numColumns;
	private String layoutConfigFile;
	private String setupConfigFile;
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
		try {
			loadSetupConfig();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			loadLayoutConfig();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadSetupConfig() throws FileNotFoundException {
		FileReader file = new FileReader(setupConfigFile);
		Scanner in = new Scanner(file);
		while(in.hasNext()) {
			String str = in.nextLine();
			String [] arrStr = str.split(",", 0);
			for(int i = 0; i < arrStr.length; i++) {
				if(arrStr[i] == "Room") {
					Room r = new Room();
					r.name = arrStr[i+1];
				}
				else if (arrStr[i] == "Space") {
					Room r = new Room();
					r.name = arrStr[i+1];
				}
			}
		}
	}
	
	public void loadLayoutConfig() throws FileNotFoundException {
		FileReader file = new FileReader(layoutConfigFile);
		Scanner in = new Scanner(file);
		while(in.hasNext()) {
			String str = in.nextLine();
			String [] arrStr = str.split(",", 0);
		}
		
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
		
	}
	public BoardCell getRoom(BoardCell cell) {
		return new BoardCell(0,0);
	}
	

}
