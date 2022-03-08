 // Authors: Jasmine Hernandez, Dylan Blaine

package experiment;

import java.util.*;


public class TestBoard {
	private Set<TestBoardCell> targets;
	private Set<TestBoardCell> visited;
	final static int COLS = 4;
	final static int ROWS = 4;
	private TestBoardCell[][] grid;

	//Default constructor to test game board
	public TestBoard() {
		grid = new TestBoardCell[ROWS][COLS];
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				TestBoardCell cell = new TestBoardCell(i,j);
				grid[i][j] = cell;
			}
		}
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if((i-1) >= 0) {
					grid[i][j].addAdjacency(grid[i-1][j]);
				}
				if((j-1) >= 0) {
					grid[i][j].addAdjacency(grid[i][j-1]);
				}
				if((i+1) < ROWS) {
					grid[i][j].addAdjacency(grid[i+1][j]);
				}
				if((j+1) < COLS) {
					grid[i][j].addAdjacency(grid[i][j+1]);
				}
			}
		}
	}
	
	//	Calculates the next possible targets
	public void calcTargets(TestBoardCell startCell, int pathlength) {
		targets = new HashSet<TestBoardCell>();
		visited = new HashSet<TestBoardCell>();
		//	adding the starting cell into the visited list
		visited.add(startCell);
		findAllTargets(startCell, pathlength);
	}
	
	
	public void findAllTargets(TestBoardCell startCell, int pathlength) {
//		Parameters: thisCell and numSteps
//		• for each adjCell in adjacentCells
		for ( TestBoardCell cell: startCell.getAdjList()) {
//			– if already in visited list, skip rest of this
			if(!visited.contains(cell) && cell.getOccupied() == false) {
//				– add adjCell to visited list 
				visited.add(cell);
				if (cell.isRoom() == true) {
					targets.add(cell);
					break;
				}
//				– if pathlengths == 1, add adjCell to Targets
				if (pathlength == 1 && cell.getOccupied() == false) {
					targets.add(cell);
				}
//				– else call calcTargets() with adjCell, numSteps-1
				else {
					findAllTargets(cell, pathlength-1);
				}
//				– remove adjCell from visited list
				visited.remove(cell);
			}
		}
	}


	//Returns possible targets as a set
	public Set<TestBoardCell> getTargets(){
		return targets;
	}

	//Returns the board cell
	public TestBoardCell getCell(int row, int col) {
		return grid[row][col];
	}

}
