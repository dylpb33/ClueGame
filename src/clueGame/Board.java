package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import experiment.TestBoardCell;

public class Board {

	private int numRows = 0;
	private int numColumns = 0;
	private BoardCell[][] grid;
	private String layoutConfigFile;
	private String setupConfigFile;
	private Map<Character, Room> roomMap = new HashMap<Character, Room>();

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
			e.printStackTrace();
		}
		try {
			loadLayoutConfig();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void loadSetupConfig() throws FileNotFoundException {
		FileReader file = new FileReader("data/" + setupConfigFile);
		Scanner in = new Scanner(file);
		// Loading character and room into Hashmap
		while(in.hasNext()) {
			String str = in.nextLine();
			String [] arrStr = str.split(",");
			String room = "Room";
			String space = "Space";
			for (int i = 0; i < arrStr.length; i++) {
				if(room.equals(arrStr[i]) || space.equals(arrStr[i]) ) {
					char ch = arrStr[i+2].replaceAll("\\s", "").charAt(0);
					Room r = new Room();
					r.name = arrStr[i+1].replaceAll("\\s", "");
					roomMap.put(ch, r); 
				}
			}
		}
	}

	public void loadLayoutConfig() throws FileNotFoundException {
		FileReader file = new FileReader("data/" + layoutConfigFile);
		Scanner in = new Scanner(file);
		// Calculating number of rows and columns
		while (in.hasNextLine()) {
			String inputLine = in.nextLine();
			String[] array = inputLine.split(",");
			numRows++;
			numColumns = array.length;
		}
		in.close();
		
		// still need to load csv into grid

	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public BoardCell getCell(int row, int col) {
		return new BoardCell(0,0);
	}

	public Room getRoom(char c) {
		// how to pull character from map?
		return new Room();
	}

	public Room getRoom(BoardCell cell) {
		return new Room();
	}

}
