// Authors: Jasmine Hernandez, Dylan Blaine

package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import experiment.TestBoardCell;

public class Board {

	private int numRows;
	private int numColumns;
	private BoardCell[][] grid;
	private String layoutConfigFile;
	private String setupConfigFile;
	private Map<Character, Room> roomMap;
	private ArrayList<String[]> arr;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
	

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
		// initializing variables needed
		roomMap = new HashMap<Character, Room>();
		String room = "Room";
		String space = "Space";
		
		// reading in files specified
		FileReader file = new FileReader("data/" + setupConfigFile);
		Scanner in = new Scanner(file);
		
		// Loading character and room into roomMap
		while(in.hasNext()) {
			String str = in.nextLine();
			String [] arrStr = str.split(", ");

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
		// initializing variables needed
		arr = new ArrayList<String[]>();
		numRows = 0;
		numColumns = 0;
		
		// reading in files specified
		FileReader file = new FileReader("data/" + layoutConfigFile);
		Scanner in = new Scanner(file);
		
		// Calculating number of rows and columns	
		while (in.hasNextLine()) {
			String inputLine = in.nextLine();
			arr.add(inputLine.split(","));
			numRows++;
			numColumns = arr.get(0).length; 
		}
		
		// setting grid with numRows and numColumns pulled from files
		grid = new BoardCell[numRows][numColumns];
		
		// finding if number of columns is not the same for all rows
		columnNumberException();
	    // loading in the grid with necessary values
		loadInGrid();
		// closing file
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
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		//	adding the starting cell into the visited list
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
	}
	
	public void findAllTargets(BoardCell startCell, int pathLength) {
//		Parameters: thisCell and numSteps
//		• for each adjCell in adjacentCells
		for ( BoardCell cell: startCell.getAdjList()) {
//			– if already in visited list, skip rest of this
			if(!visited.contains(cell) && cell.getOccupied() == false) {
//				– add adjCell to visited list 
				visited.add(cell);
				if (cell.isRoom() == true) {
					targets.add(cell);
					continue;
				}
//				– if pathLength == 1, add adjCell to Targets
				if (pathLength == 1 && cell.getOccupied() == false) {
					targets.add(cell);
				}
//				– else call calcTargets() with adjCell, numSteps-1
				else {
					findAllTargets(cell, pathLength-1);
				}
//				– remove adjCell from visited list
				visited.remove(cell);
			}
		}
	}
	
	public Set<BoardCell> getTargets() {
		return new HashSet<BoardCell>();
	}

	
	public void columnNumberException() throws BadConfigFormatException {
		// throws exception error if numColumns is not the same in each row
		for (int i = 0; i < numRows; i++) {
			if (arr.get(i).length != numColumns) {
				throw new BadConfigFormatException("File Configuration Error: Number of Columns are not the same for each row.");
			}
		}

	}
	
	public void loadInGrid() throws BadConfigFormatException {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				BoardCell cell = new BoardCell(i,j);
				grid[i][j] = cell;
				String s = arr.get(i)[j];
				cell.setInitial(s.charAt(0));
				Room room = getRoom(cell);
				
				
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
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				if((i-1) >= 0) {
					grid[i][j].addAdj(grid[i-1][j]);
				}
				if((j-1) >= 0) {
					grid[i][j].addAdj(grid[i][j-1]);
				}
				if((i+1) < numRows) {
					grid[i][j].addAdj(grid[i+1][j]);
				}
				if((j+1) < numColumns) {
					grid[i][j].addAdj(grid[i][j+1]);
				}
			}
		}
		
	}
	public Set<BoardCell> getAdjList(int i, int j) {
		return grid[i][j].getAdjList();
	}
}
