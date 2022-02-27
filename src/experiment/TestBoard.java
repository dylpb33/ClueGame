package experiment;

import java.util.*;

public class TestBoard {

	public TestBoard() {

	}
	
	public void calcTargets(TestBoardCell startCell, int pathlength) {

	}
	
	public Set<TestBoardCell> getTargets(){
		return new HashSet<TestBoardCell>();
	}
	
	public TestBoardCell getCell(int row, int col) {
		TestBoardCell cell = new TestBoardCell(row, col);
		return new TestBoardCell (0,0);
	}

}
