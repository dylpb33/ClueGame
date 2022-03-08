package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Board {

	private int numRows;
	private int numColumns;
	private BoardCell[][] grid;
	private String layoutConfigFile;
	private String setupConfigFile;
	private Map<Character, Room> roomMap;
	private ArrayList<String[]> arr;

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
			System.out.println(e);
		} catch (BadConfigFormatException e) {
			System.out.println(e);
		}

		try {
			loadLayoutConfig();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (BadConfigFormatException e) {
			System.out.println(e);
		}

	}

	public void loadSetupConfig() throws BadConfigFormatException, FileNotFoundException {
		FileReader file = new FileReader("data/" + setupConfigFile);
		Scanner in = new Scanner(file);
		roomMap = new HashMap<Character, Room>();
		// Loading character and room into Hashmap
		while(in.hasNext()) {
			String str = in.nextLine();
			String [] arrStr = str.split(", ");
			String room = "Room";
			String space = "Space";

			for (int i = 0; i < arrStr.length; i++) {
				if(room.equals(arrStr[i]) || space.equals(arrStr[i]) ) {
					char ch = arrStr[i+2].charAt(0);
					Room r = new Room();
					r.name = arrStr[i+1];
					roomMap.put(ch, r); 
				}
			}
		}
		in.close();
	}

	public void loadLayoutConfig() throws BadConfigFormatException, FileNotFoundException {
		FileReader file = new FileReader("data/" + layoutConfigFile);
		Scanner in = new Scanner(file);
		arr = new ArrayList<String[]>();
		numRows = 0;
		numColumns = 0;
		// Calculating number of rows and columns	
		while (in.hasNextLine()) {
			String inputLine = in.nextLine();
			arr.add(inputLine.split(","));
			numRows++;
		}
		numColumns = arr.get(0).length; 
		grid = new BoardCell[numRows][numColumns];
		
		// throws exception error if numColumns is not the same in each row
		for (int i = 0; i < numRows; i++) {
			if (arr.get(i).length != numColumns) {
				throw new BadConfigFormatException("File Configuration Error: Number of Columns are not the same for each row.");
			}
		}
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				BoardCell cell = new BoardCell(i,j);
				grid[i][j] = cell;
				String s = arr.get(i)[j];
				cell.setInitial(s.charAt(0));

				Room room = getRoom(cell);

				// checks to make sure the room exists in the legend
				if (room == null) {
					throw new BadConfigFormatException("File Configuration Error: Room does not exist in legend");
				}
					
				if(s.contains("#")) {
					room.setLabelCell(cell);
					cell.roomLabel = true;
					cell.isSecretPassage = false;
				}
				if(s.contains("*")) {
					room.setCenterCell(cell);
					cell.roomCenter = true;
					cell.isSecretPassage = false;
				}
				if(s.contains(">")) {
					cell.setDoorDirection(DoorDirection.RIGHT);
					cell.isDoor = true;
					cell.isSecretPassage = false;
				}
				if(s.contains("<")) {
					cell.setDoorDirection(DoorDirection.LEFT);
					cell.isDoor = true;
					cell.isSecretPassage = false;
				}
				if(s.contains("^")) {
					cell.setDoorDirection(DoorDirection.UP);
					cell.isDoor = true;
					cell.isSecretPassage = false;
				}
				if(s.contains("v")) {
					cell.setDoorDirection(DoorDirection.DOWN);
					cell.isDoor = true;
					cell.isSecretPassage = false;
				}
				if (s.length() == 1) {
					cell.isSecretPassage = false;
				}
				if (cell.isSecretPassage == true) {
					cell.secretPassage = s.charAt(1);
				}
			}
		}

		in.close();
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public BoardCell getCell(int row, int col) {
		return grid[row][col];
	}

	public Room getRoom(char c) {
		return roomMap.get(c);
	}

	public Room getRoom(BoardCell cell) {
		return roomMap.get(cell.getInitial());
	}

}
