// Authors: Jasmine Hernandez, Dylan Blaine

package clueGame;

public class BadConfigFormatException extends Exception {
	
	public BadConfigFormatException() {
		System.out.println("Error loading file: invalid file configuration");
	}
	
	public BadConfigFormatException(String s) {
		System.out.println(s);
	}


}
