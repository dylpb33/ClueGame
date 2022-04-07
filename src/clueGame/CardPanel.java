package clueGame;

import java.awt.GridLayout;

import javax.swing.JPanel;

import clueGame.Card.CardType;

public class CardPanel extends JPanel{

	public CardPanel() {
		JPanel main = new JPanel();
		main.setLayout(new GridLayout(3, 1));
		
	}
	
	public void updatePanels() {
		updatePanel(peoplePanel, Card.CardType.PERSON);
		updatePanel(roomPanel, Card.CardType.ROOM);
		updatePanel(weaponPanel, Card.CardType.WEAPON);
	}
	
	public void updatePanel(JPanel panel, CardType c) {
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
