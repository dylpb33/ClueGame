// Authors: Jasmine Hernandez, Dylan Blaine

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
	private ArrayList<String[]> cellList;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
	private ArrayList<Card> Deck;
	private ArrayList<Card> shuffledDeck;
	private ArrayList<Player> Players;
	private Solution solution;

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
		Deck = new ArrayList<Card>();
		Players = new ArrayList<Player>();
		Card newCard;	
		String room = "Room";
		String space = "Space";
		String player = "Player";
		String comment = "//";
		String human = "human";
		String weapon = "Weapon";
		String computer = "computer";

		// reading in files specified
		FileReader file = new FileReader("data/" + setupConfigFile);
		Scanner in = new Scanner(file);

		// Loading character and room into roomMap
		while(in.hasNext()) {
			String fileLine = in.nextLine();
			String [] arrStr = fileLine.split(", ");

			// only parse through if it is not a comment or empty line
			if(!(fileLine.contains(comment) || fileLine.isEmpty())) {

				//check to make sure it is a valid line that starts with Room or Space
				if(room.equals(arrStr[0]) || space.equals(arrStr[0])) {
					char roomInitial = arrStr[2].charAt(0);
					Room newRoom = new Room();
					newRoom.setName(arrStr[1]);
					roomMap.put(roomInitial, newRoom);
					// making sure
					if (!space.equals(arrStr[0])) {
						newCard = new Card(arrStr[1]);
						newCard.setCardType(room);
						Deck.add(newCard);
					}
				}
				// loading in Human player information
				if(player.equals(arrStr[0])) {
					int row = Integer.parseInt(arrStr[4]);
					int col = Integer.parseInt(arrStr[5]);
					if(human.equals(arrStr[2])) {
						HumanPlayer newHuman = new HumanPlayer();
						newHuman.setName(arrStr[1]);
						newHuman.setColor(arrStr[3]);
						newHuman.setRow(row);
						newHuman.setColumn(col);
						newCard = new Card(arrStr[1]);
						newCard.setCardType(player);
						Deck.add(newCard);
						Players.add(newHuman);
					}
					if (computer.equals(arrStr[2])) {
						// loading in computer player information
						ComputerPlayer newComputer = new ComputerPlayer();
						newComputer.setName(arrStr[1]);
						newComputer.setColor(arrStr[3]);
						newComputer.setRow(row);
						newComputer.setColumn(col);
						newCard = new Card(arrStr[1]);
						newCard.setCardType(player);
						Deck.add(newCard);
						Players.add(newComputer);
					}
				}
				if (weapon.equals(arrStr[0])) {
					newCard = new Card(arrStr[1]);
					newCard.setCardType(weapon);
					Deck.add(newCard);
				}
			}
		}	
		in.close();
	}



	public void loadLayoutConfig() throws BadConfigFormatException, FileNotFoundException {
		// initializing variables needed
		cellList = new ArrayList<String[]>();
		numRows = 0;
		numColumns = 0;

		// reading in files specified
		FileReader file = new FileReader("data/" + layoutConfigFile);
		Scanner in = new Scanner(file);

		// Calculating number of rows and columns before reading in file
		countRowsCols(in);

		// setting grid with numRows and numColumns pulled from files
		grid = new BoardCell[numRows][numColumns];

		// Fill grid with the cells, and create rooms from the cells
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numColumns; col++) {
				BoardCell cell = new BoardCell(row,col);
				grid[row][col] = cell;
				String fileLine = cellList.get(row)[col];
				char roomChar = fileLine.charAt(0);
				cell.setInitial(roomChar);
				Room room = getRoom(cell);
				// if there is no room
				if (room == null) {
					throw new BadConfigFormatException("File Configuration Error: Room does not exist in legend");
				}
				/* calling method to setup cells with indicators 
				 * (*, #, <, >, ^, v, extra char for room letter) */
				setupCellIndicator(fileLine,room, cell, roomChar);
			}
		}
		// calling method to setup where a member can go (adjacency cells)
		setupWalkways();
		// closing file
		in.close();
	}

	private void countRowsCols(Scanner in) throws BadConfigFormatException, FileNotFoundException  {
		while (in.hasNextLine()) {
			String inputLine = in.nextLine();
			cellList.add(inputLine.split(","));
			numRows++;
			numColumns = cellList.get(0).length; 
		}
		
		// throw exception if number of columns differ per row
		for (int col = 0; col < numRows; col++) {
			if (cellList.get(col).length != numColumns) {
				throw new BadConfigFormatException("File Configuration Error: Number of Columns are not the same for each row.");
			}
		}
	}
	
	public void setupCellIndicator(String fileLine, Room room, BoardCell cell, char roomChar) {

		if (fileLine.length() > 1) {
			// space for room name
			if(fileLine.contains("#")) {
				roomMap.get(roomChar).setLabelCell(cell);
				cell.setIsRoom(true);
				cell.setRoomLabel(true);
			}
			// room center
			else if(fileLine.contains("*")) {
				roomMap.get(roomChar).setCenterCell(cell);
				cell.setIsRoom(true);
				cell.setRoomCenter(true);
				cell.setSecretPassage(roomChar);
			}
			// door leading right into room
			else if(fileLine.contains(">")) {
				cell.setDoorDirection(DoorDirection.RIGHT);
				cell.setDoor(true);
			}
			// door leading left into room
			else if(fileLine.contains("<")) {
				cell.setDoorDirection(DoorDirection.LEFT);
				cell.setDoor(true);
			}
			// door leading up into room
			else if(fileLine.contains("^")) {
				cell.setDoorDirection(DoorDirection.UP);
				cell.setDoor(true);
			}
			// door leading down into room
			else if(fileLine.contains("v")) {
				cell.setDoorDirection(DoorDirection.DOWN);
				cell.setDoor(true);
			}
			// otherwise it is a secret passage
			else {
				cell.setSecretPassage(fileLine.charAt(1));
				cell.setSecretPassage(true);
				cell.setIsRoom(true);
				room.setSecretPassageCell(cell);
			}
		}
	}
	

	public void setupWalkways() {
		// Ensure door locations include their rooms and also additional walkways

		// checking for rooms doors can go into
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numColumns; col++) {
				BoardCell cell = this.getCell(row, col);
				Room room;
				// for doors that go up into a room
				if (cell.getDoorDirection() == DoorDirection.UP) {
					room = getRoom(grid[row-1][col]);
					cell.setAdjList(room.getCenterCell());
					room.getCenterCell().setAdjList(cell);
				}
				// for doors that go down into a room
				if (cell.getDoorDirection() == DoorDirection.DOWN) {
					room = getRoom(grid[row+1][col]);
					cell.setAdjList(room.getCenterCell());
					room.getCenterCell().setAdjList(cell);
				}
				// for doors that go left into a room
				if (cell.getDoorDirection() == DoorDirection.LEFT) {
					room = getRoom(grid[row][col-1]);
					cell.setAdjList(room.getCenterCell());
					room.getCenterCell().setAdjList(cell);
				}
				// for doors that go right into a room
				if (cell.getDoorDirection() == DoorDirection.RIGHT) {
					room = getRoom(grid[row][col+1]);
					cell.setAdjList(room.getCenterCell());
					room.getCenterCell().setAdjList(cell);
				}
				else if(cell.isRoomCenter()) {
					room = getRoom(cell);
					if(room.getSecretPassageCell() != null) {
						char c = room.getSecretPassageCell().getSecretPassage();
						room = getRoom(c);
						cell.setAdjList(room.getCenterCell());
					}
				}

				// checking for additional walkways
				if (cell.getInitial() == 'W') {
					if((row-1) >= 0) {
						cell = this.getCell(row-1, col);
						if (cell.getInitial() == 'W') {
							grid[row][col].setAdjList(cell);
						}
					}
					if((col-1) >= 0) {
						cell = this.getCell(row, col-1);
						if (cell.getInitial() == 'W') {
							grid[row][col].setAdjList(cell);
						}
					}
					if((row+1) < numRows) {
						cell = this.getCell(row+1, col);
						if (cell.getInitial() == 'W') {
							grid[row][col].setAdjList(cell);
						}
					}
					if((col+1) < numColumns) {
						cell = this.getCell(row, col+1);
						if (cell.getInitial() == 'W') {
							grid[row][col].setAdjList(cell);
						}
					}
				}
			}
		}
	}

	public void calcTargets(BoardCell startCell, int pathLength) {
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		//	adding the starting cell into the visited list
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
	}

	public void findAllTargets(BoardCell startCell, int pathLength) {
		// Parameters: thisCell and numSteps
		// for each adjCell in adjacentCells
		for ( BoardCell cell: startCell.getAdjList()) {
			// if already in visited list, skip rest of this
			if(!visited.contains(cell) && (!cell.getIsOccupied() || cell.getIsRoom())) {
				//add adjCell to visited list 
				visited.add(cell);
				if (Boolean.TRUE.equals(cell.getIsRoom()) || pathLength == 1) {
					targets.add(cell);
				}
				//else call calcTargets() with adjCell, numSteps-1
				else {
					findAllTargets(cell, pathLength-1);
				}
				//git remove adjCell from visited list
				visited.remove(cell);
			}
		}
	}
	
	public void deal() {


		//Allocate new shuffledDeck and fill it with Deck's contents.
		shuffledDeck = new ArrayList<Card>();
		for(int i = 0; i < getDeck().size(); i++) {
			shuffledDeck.add(Deck.get(i));
		}

		//Shuffle shuffledDeck.
		Collections.shuffle(shuffledDeck);

		//Set solution based on the first occurrence cards found.
		solution = new Solution();

		//Find first occurrence of a room, person, and weapon card from the shuffledDeck
		for(int i = 0; i < getShuffledDeck().size(); i++) {
			if(shuffledDeck.get(i).getCardType() == "room") {
				solution.setSolutionRoom(shuffledDeck.get(i));
				shuffledDeck.remove(i);
				break;
			}
		}
		for(int i = 0; i < getShuffledDeck().size(); i++) {
			if(shuffledDeck.get(i).getCardType() == "person") {
				solution.setSolutionPerson(shuffledDeck.get(i));
				shuffledDeck.remove(i);
				break;
			}
		}
		for(int i = 0; i < getShuffledDeck().size(); i++) {
			if(shuffledDeck.get(i).getCardType() == "weapon") {
				solution.setSolutionWeapon(shuffledDeck.get(i));
				shuffledDeck.remove(i);
				break;
			}
		}

		//Deal shuffled cards to players sequentially until all cards have been dealt.
		while(getShuffledDeck().size() > 0) {
			for(int i = 0; i < getPlayerArray().size(); i++) {	
				getPlayer(i).getHand().add(shuffledDeck.get(0));
				shuffledDeck.remove(0);
			}	
		}	
	}
	
	public boolean checkAccusation(String room, String person, String weapon) {
		if (room == solution.getSolutionRoom().getCardName() 
				&& person == solution.getSolutionPerson().getCardName()
				&& weapon == solution.getSolutionWeapon().getCardName()) {
			return true;
		}
		else  {
			return false;
		}
	}

	public Card handleSuggestion(String room, String person, String weapon, ArrayList<Player> playerArray, Player accusingPlayer) {
		boolean first = true;
		Card card = new Card();
		card = null;
	
		for(int i = 0; i < playerArray.size(); i++) {
			if(first == false) {
				break;
			}
			for(int j = 0; j < playerArray.get(i).getHand().size(); j++) {
				if(playerArray.get(i).getHand(j).getCardName().equals(room) && playerArray.get(i) != accusingPlayer) {
					card = playerArray.get(i).getHand(j);
					first = false;
					break;
				}
				if(playerArray.get(i).getHand(j).getCardName().equals(person) && playerArray.get(i) != accusingPlayer) {
					card = playerArray.get(i).getHand(j);
					first = false;
					break;
				}
				if(playerArray.get(i).getHand(j).getCardName().equals(weapon) && playerArray.get(i) != accusingPlayer) {
					card = playerArray.get(i).getHand(j);
					first = false;
					break;
				}
			}
		}
		return card;

	}

	
	// Returns number of rows in board.
	public int getNumRows() {
		return numRows;
	}
	
	// Returns number of columns in board.
	public int getNumColumns() {
		return numColumns;
	}
	
	// Returns cell on board at given row and column of cell.
	public BoardCell getCell(int row, int col) {
		return grid[row][col];
	}
	
	// Returns room of cell based on its initial.
	public Room getRoom(char c) {
		return roomMap.get(c);
	}
	
	// Returns the room a cell is in.
	public Room getRoom(BoardCell cell) {
		return roomMap.get(cell.getInitial());
	}
	
	// Returns possible targets.
	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	// Returns AdjList based on row and column of cell.
	public Set<BoardCell> getAdjList(int row, int col) {
		return grid[row][col].getAdjList();
	}
	
	//Add player to Players Array.
	public void setPlayerArray(Player p) {
		Players.add(p);
	}
	
	//Return player from Players Array based on index.
	public Player getPlayer(int i) {
		return Players.get(i);
	}
	
	//Return Players array.
	public ArrayList<Player> getPlayerArray() {
		return Players;
	}
	
	//Add card to Deck.
	public void setDeck(Card c) {
		Deck.add(c);
	}
	
	//Return card from Deck based on index.
	public Card getCardInDeck(int i) {
		return Deck.get(i);
	}
	
	//Return Deck.
	public ArrayList<Card> getDeck() {
		return Deck;
	}
	
	//Return shuffledDeck.
	public ArrayList<Card> getShuffledDeck() {
		return shuffledDeck;
	}
	
	//Set solution for board.
	public void setSolution(Card a, Card b, Card c) {
		solution.setSolution(a,b,c);
	}
	
	//Return solution for board.
	public Solution getSolution() {
		return solution;
	}

}
