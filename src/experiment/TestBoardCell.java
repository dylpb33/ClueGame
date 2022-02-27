package experiment;

import java.util.*;

public class TestBoardCell {
	
	public TestBoardCell(int row, int column) {
		row = 0;
		column = 0;
	}

	public void addAdjacency(TestBoardCell cell) {
		
	}
	
	public Set<TestBoardCell> getAdjList(){
		return new HashSet<TestBoardCell>();
	}
	
	public void setRoom(boolean b) {
		
	}

	public boolean isRoom() {
		return true;
	}
	
	public void setOccupied(boolean b) {
		
	}

	public boolean getOccupied() {
		return true;
	}
}
