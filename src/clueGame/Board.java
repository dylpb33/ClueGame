// Authors: Jasmine Hernandez, Dylan Blaine

package clueGame;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clueGame.Card.CardType;

public class Board extends JPanel implements MouseListener{

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
	private int currPlayerNum;
	private HumanPlayer human;
	private Player currentPlayer;
	private Boolean isFinished = true;
	private final static int ROLL_NUM = 6;
	private GameControlPanel gameControlPanel;
	private CardPanel cardPanel;
	private Player disprovingPlayer;

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
		String human1 = "human";
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
					// making sure spaces aren't added as rooms
					if (!space.equals(arrStr[0])) {
						newCard = new Card(arrStr[1]);
						newCard.setCardType(CardType.ROOM);
						Deck.add(newCard);
					}
				}
				// loading in Human player information
				if(player.equals(arrStr[0])) {
					int row = Integer.parseInt(arrStr[4]);
					int col = Integer.parseInt(arrStr[5]);
					if(human1.equals(arrStr[2])) {
						human = new HumanPlayer();
						human.setName(arrStr[1]);
						human.setColor(arrStr[3]);
						human.setRow(row);
						human.setColumn(col);
						newCard = new Card(arrStr[1]);
						newCard.setCardType(CardType.PERSON);
						Deck.add(newCard);
						Players.add(human);
						currentPlayer = human;
						currPlayerNum = -1;
					}
					if (computer.equals(arrStr[2])) {
						// loading in computer player information
						ComputerPlayer newComputer = new ComputerPlayer();
						newComputer.setName(arrStr[1]);
						newComputer.setColor(arrStr[3]);
						newComputer.setRow(row);
						newComputer.setColumn(col);
						newCard = new Card(arrStr[1]);
						newCard.setCardType(CardType.PERSON);
						Deck.add(newCard);
						Players.add(newComputer);
					}
				}
				// loading in weapons
				if (weapon.equals(arrStr[0])) {
					newCard = new Card(arrStr[1]);
					newCard.setCardType(CardType.WEAPON);
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
				// otherwise setup as a secret passage connection
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
				// setting up the rooms
				if (cell.getInitial() == 'S' || cell.getInitial() == 'P' || cell.getInitial() == 'R'
					|| cell.getInitial() == 'O' || cell.getInitial() == 'L' || cell.getInitial() == 'K'
					|| cell.getInitial() == 'T' || cell.getInitial() == 'C' || cell.getInitial() == 'G') {
					cell.setIsRoom(true);
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
				// remove adjCell from visited list
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
		solution = new Solution(null, null, null);

		//Find first occurrence of a room from the shuffledDeck
		for(int i = 0; i < getShuffledDeck().size(); i++) {
			if(shuffledDeck.get(i).getCardType() == CardType.ROOM) {
				solution.setSolutionRoom(shuffledDeck.get(i));
				shuffledDeck.remove(i);
				break;
			}
		}
		//Find first occurrence of a person from the shuffledDeck
		for(int i = 0; i < getShuffledDeck().size(); i++) {
			if(shuffledDeck.get(i).getCardType() == CardType.PERSON) {
				solution.setSolutionPerson(shuffledDeck.get(i));
				shuffledDeck.remove(i);
				break;
			}
		}
		//Find first occurrence of a weapon card from the shuffledDeck
		for(int i = 0; i < getShuffledDeck().size(); i++) {
			if(shuffledDeck.get(i).getCardType() == CardType.WEAPON) {
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
	
	// check to make sure that all cards match the accusation made
	public boolean checkAccusation(String room, String person, String weapon) {
		// setting the variables
		String roomGuess = solution.getSolutionRoom().getCardName();
		String personGuess = solution.getSolutionPerson().getCardName();
		String weaponGuess = solution.getSolutionWeapon().getCardName();
		// checking to make sure they are all true for correct accusation
		if (room == roomGuess && person == personGuess && weapon == weaponGuess) {
			return true;
		}
		else  {
			return false;
		}
	}

	public Card handleSuggestion(String room, String person, String weapon, Player accusingPlayer) {
		Card card = null;
	// iterating through players to see who can disprove the suggestion
		for(Player player : Players) {
			if(player != accusingPlayer) {
				if(player.disproveSuggestion(room, person, weapon) != null) {
					card = player.disproveSuggestion(room, person, weapon);
					disprovingPlayer = player;
				}
			}
		}
		return card;
	}
	
	//Draws the board and players
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Set board panel to be same width and height calculated in layoutConfig()
		//Calculate panel size
		int xOffset;
		int yOffset;
		int width = this.getWidth();
		int height = this.getHeight();
		
		//Use panel size to calculate cell width and height
		int cellWidth = width / getNumColumns();
		int cellHeight = height / getNumRows();		
		
		//Loop through each boardcell and call draw(int width, int height, Graphics g) method
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				// calculate offset
				xOffset = j * cellWidth;
				yOffset = i * cellHeight;
				getCell(i, j).drawCell(cellWidth, cellHeight, xOffset, yOffset, g);
			}
		}

		// getting room names and calling drawRoomName in Room class
		for(Map.Entry<Character, Room> entry : roomMap.entrySet()) {
			if(entry.getValue().getLabelCell() != null) {
				xOffset = (entry.getValue().getLabelCell().getColumn()) * cellWidth;
				yOffset = (entry.getValue().getLabelCell().getRow() + 1) * cellHeight;
				entry.getValue().drawRoomName(xOffset, yOffset, g, cellWidth, cellHeight);
			}
		}
		
		if(!isFinished) {
			for(BoardCell target : targets) {
				xOffset = target.getColumn() * cellWidth;
				yOffset = target.getRow() * cellHeight;
				target.highlightTargets(cellWidth, cellHeight, xOffset, yOffset, g);
			}
		}
		
		// if two players are in the same room add them to array
		Set<Player> sameCellPlayers = new HashSet<Player>();
		for(int i = 0; i < Players.size(); i++) {
			for(int j = i + 1; j < Players.size(); j++) {
				if(Players.get(i).getRow() == Players.get(j).getRow() && Players.get(i).getColumn() == Players.get(j).getColumn()) {
					sameCellPlayers.add(Players.get(i));
					sameCellPlayers.add(Players.get(j));
				}
					
			}
		}
		
		// drawing the players
		int locationOffset = 0;
		for(Player player : Players) {
			xOffset = player.getColumn() * cellWidth;
			yOffset = player.getRow() * cellHeight;	
			// offset the players in the same room so they  don't overlap when printed
			if(sameCellPlayers.contains(player)) {
				player.drawPlayer(cellWidth, cellHeight, xOffset + locationOffset, yOffset, g);
				locationOffset = locationOffset + cellWidth / 4;
			}
			else {
				player.drawPlayer(cellWidth, cellHeight, xOffset, yOffset, g);
			}
			
		}
	}
	
	//generates a random number between 1 and 6
	public int rollDie() {
		Random rand = new Random();
		return rand.nextInt(ROLL_NUM) + 1;
	}
	
	public void processNext(GameControlPanel controlPanel) {
		if(isFinished) {
			//Reset guess and guess result fields
			gameControlPanel.setGuess("");
			gameControlPanel.setGuessResult("");
			
			//Update current player
			currPlayerNum = (currPlayerNum + 1) % Players.size();
			currentPlayer = Players.get(currPlayerNum);

			//Roll the dice
			int roll = rollDie();

			//Calc Targets
			calcTargets(getCell(Players.get(currPlayerNum).getRow(), Players.get(currPlayerNum).getColumn()), roll);

			//Update Game control Panel
			controlPanel.setTurn(currentPlayer, roll);
			
			//Check if player placed in room because of suggestion or if player cannot move out of room
			if(currentPlayer.getInSuggestion() || targets.size() == 0) {
				BoardCell currentCell = getCell(Players.get(currPlayerNum).getRow(), Players.get(currPlayerNum).getColumn());
				targets.add(currentCell);
				currentPlayer.setInSuggestion(false);
			}
			//Is new player human?
			if(currentPlayer instanceof HumanPlayer) {
				//Flag unfinished
				isFinished = false;
				repaint();
			}
			// otherwise the computer moves
			else {
				Card currentLocation = null;
				//Get card for current location
				for(int i = 0; i < getDeck().size(); i++) {
					String roomName = getRoom(getCell(currentPlayer.getRow(), currentPlayer.getColumn()).getInitial()).getName();
					String cardName = getDeck().get(i).getCardName();
					if(roomName.equals(cardName)){
						currentLocation = getDeck().get(i);
					}
				}
				// checking to see if the solution is right
				if(currentPlayer.canDisprove == false) {
					Solution possibleSolution = ((ComputerPlayer) currentPlayer).createSuggestion(currentLocation, getDeck(), currentPlayer.getSeenCards());
					if(possibleSolution.equals(this.solution)) {
						JOptionPane.showMessageDialog(null, currentPlayer.getName() + " wins! " + " it was " + this.solution.getSolutionPerson() + " in the " + this.solution.getSolutionRoom() + " with a " + this.solution.getSolutionWeapon());
						System.exit(0);
					}
					currentPlayer.setCanDisprove(true);
				}
				currentPlayer.Move();
				
				if(getCell(currentPlayer.getRow(), currentPlayer.getColumn()).isRoomCenter()) {
					Solution possibleSolution = ((ComputerPlayer) currentPlayer).createSuggestion(currentLocation, getDeck(), currentPlayer.getSeenCards());
					for(Player player : Players) {
						if(player.getName().equals(possibleSolution.getSolutionPerson().getCardName())){
							player.setRow(currentPlayer.getRow());
							player.setColumn(currentPlayer.getColumn());
							player.setInSuggestion(true);
						}
					}
					
					gameControlPanel.setGuess(possibleSolution.getSolutionPerson().getCardName() + ", " + possibleSolution.getSolutionRoom().getCardName() + ", " + possibleSolution.getSolutionWeapon().getCardName());
					Card suggestedCard = handleSuggestion(possibleSolution.getSolutionRoom().getCardName(), possibleSolution.getSolutionPerson().getCardName(), possibleSolution.getSolutionWeapon().getCardName(), currentPlayer);
					if(suggestedCard != null) {
						gameControlPanel.setGuessResult("Suggestion diproven by " + disprovingPlayer.getName());
					}
					else {
						currentPlayer.setCanDisprove(false);
						gameControlPanel.setGuessResult("No card able to disprove accusation");
					}
					
				}
			}
			repaint();
		}
		// displays this message if Human selects next before finishing their turn
		else {
			JOptionPane.showMessageDialog(null, "Please finish your turn!");
		}
	}
	
		
	@Override
	public void mouseClicked(MouseEvent e) {
		// getting the cell clicked
		int row = (int) e.getY() / (getHeight() / getNumRows());
		int column = (int) e.getX() / (getWidth() / getNumColumns());
		BoardCell c = getCell(row, column);
		// if the current player is the human
		if(currentPlayer instanceof HumanPlayer) {
			// and the target contains the cell clicked
			if(targets.contains(c)) {
				// move the human to the cell clicked
				currentPlayer.Move(c);
				isFinished = true;
				repaint();
				if(getCell(currentPlayer.getRow(), currentPlayer.getColumn()).getIsRoom()) {
					SuggestionModalDialog suggestionBox = new SuggestionModalDialog(getRoom(getCell(currentPlayer.getRow(), currentPlayer.getColumn())));
				}
			}
			// if they select a cell that is not in the targets list
			else {
				JOptionPane.showMessageDialog(null, "That is not a target");
			}
		}
	}

	//Unused abstract methods
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}	
	

	
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
	
	public Player getDisprovingPlayer() {
		return disprovingPlayer;
	}
	
	public void setDisprovingPlayer(Player p) {
		disprovingPlayer = p;
	}
	
	//Return player from Players Array based on index.
	public HumanPlayer getHumanPlayer() {
		HumanPlayer human = null;
		for(int i = 0; i < Players.size(); i++) {
			if(Players.get(i) instanceof HumanPlayer) {
				human = (HumanPlayer) Players.get(i);
			}
		}
		return human;
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
	
	public void setGameControlPanel(GameControlPanel panel) {
		gameControlPanel = panel;
	}
	
	public GameControlPanel getGameControlPanel() {
		return gameControlPanel;
	}
	
	public void setCardPanel(CardPanel panel) {
		cardPanel = panel;
	}
	
	public CardPanel getCardPanel() {
		return cardPanel;
	}
	
	public void setHuman(HumanPlayer humanplayer) {
		human = humanplayer;
	}
	
	public HumanPlayer getHuman() {
		return human;
	}
	


}
