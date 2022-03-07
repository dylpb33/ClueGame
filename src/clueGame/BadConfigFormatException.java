package clueGame;

public class BadConfigFormatException extends Exception {
	
	public BadConfigFormatException() {
		System.out.println("Error loading file");
	}
	
	public BadConfigFormatException(String s) {
		System.out.println("Error loading file" + s);
	}


}
