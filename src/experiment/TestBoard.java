package experiment;

import java.util.*;

public class TestBoard {
	private Set<TestBoardCell> targets;
	private Set<TestBoardCell> visited;
	final static int COLS = 4;
	final static int ROWS = 4;
	private TestBoardCell[][] grid = new TestBoardCell[ROWS][COLS];
	
	
	//Default constructor to test game board
	public TestBoard() {
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

	
	//Calculates the next possible targets
	public void calcTargets(TestBoardCell startCell, int pathlength) {

	}
	
	//Returns possible targets as a set
	public Set<TestBoardCell> getTargets(){
		return new HashSet<TestBoardCell>();
	}
	
	//Returns the board cell
	public TestBoardCell getCell(int row, int col) {
		return grid[row][col];
	}

}
