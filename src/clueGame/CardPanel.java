package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Card.CardType;

public class CardPanel extends JPanel{
	
	Player HumanPlayer;
	private JTextField hand;
	private JTextField seenHand;

	public CardPanel() {
		// creating the known cards panel
		JPanel knownCards = new JPanel();
		knownCards.setBorder(new TitledBorder (new EtchedBorder(), "Known Cards"));
		knownCards.setLayout(new GridLayout(3, 1));

		JPanel people = peoplePanel();
		knownCards.add(people);
		
		JPanel rooms = roomPanel();
		knownCards.add(rooms);
		
		JPanel weapons = weaponPanel();
		knownCards.add(weapons);
		
		add(knownCards);
	}
	
	public void updatePanels() {
		updatePanel(peoplePanel(), Card.CardType.PERSON);
		updatePanel(roomPanel(), Card.CardType.ROOM);
		updatePanel(weaponPanel(), Card.CardType.WEAPON);
	}
	
	public JPanel peoplePanel() {		
		// creating the people panel 
		JPanel people = new JPanel();
		people.setLayout(new GridLayout(2, 0));
		people.setBorder(new TitledBorder (new EtchedBorder(), "People"));

		// setting the panel that shows the players hand
		JPanel inHand = new JPanel();
		JLabel inHandLabel = new JLabel("In Hand:");

		hand = new JTextField(20);
		hand.setText("Test Name");

		inHand.setLayout(new GridLayout(2, 0));
		inHand.add(inHandLabel);
		inHand.add(hand);

		people.add(inHand, BorderLayout.CENTER);
		
		// setting the panel that shows the cards the player has seeen
		JPanel seen = new JPanel();
		JLabel seenLabel = new JLabel("Seen:");

		seenHand = new JTextField(25);
		seenHand.setText("Test Name");

		seen.setLayout(new GridLayout(2, 0));
		seen.add(seenLabel);
		seen.add(seenHand);

		people.add(seen, BorderLayout.SOUTH);
		
		return people;
	}
	
	public JPanel roomPanel() {
		// creating the rooms panel that goes in Known Cards
		JPanel rooms = new JPanel();
		rooms.setLayout(new GridLayout(2, 0));
		rooms.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));

		// setting the panel that shows the rooms the player has seen
		JPanel inHand = new JPanel();
		JLabel inHandLabel = new JLabel("In Hand:");

		hand = new JTextField(20);
		hand.setText("Test Name");

		inHand.setLayout(new GridLayout(2, 0));
		inHand.add(inHandLabel);
		inHand.add(hand);

		rooms.add(inHand, BorderLayout.CENTER);

		// setting the panel that shows the cards the player has seeen
		JPanel seen = new JPanel();
		JLabel seenLabel = new JLabel("Seen:");

		seenHand = new JTextField(25);
		seenHand.setText("Test Name");

		seen.setLayout(new GridLayout(2, 0));
		seen.add(seenLabel);
		seen.add(seenHand);

		rooms.add(seen, BorderLayout.SOUTH);
		return rooms;
	}

	public JPanel weaponPanel() {
		// creating the weapoons panel that goes in Known Cards
		JPanel weapons = new JPanel();
		weapons.setLayout(new GridLayout(2, 0));
		weapons.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));

		// setting the panel that shows the rooms the player has seen
		JPanel inHand = new JPanel();
		JLabel inHandLabel = new JLabel("In Hand:");

		hand = new JTextField(20);
		hand.setText("Test Name");

		inHand.setLayout(new GridLayout(2, 0));
		inHand.add(inHandLabel);
		inHand.add(hand);

		weapons.add(inHand, BorderLayout.CENTER);

		// setting the panel that shows the cards the player has seeen
		JPanel seen = new JPanel();
		JLabel seenLabel = new JLabel("Seen:");

		seenHand = new JTextField(25);
		seenHand.setText("Test Name");

		seen.setLayout(new GridLayout(2, 0));
		seen.add(seenLabel);
		seen.add(seenHand);

		weapons.add(seen, BorderLayout.SOUTH);
		return weapons;
	}
	
	public void updatePanel(JPanel panel, CardType c) {

	}
	
	public static void main(String[] args) {
		CardPanel panel = new CardPanel();  // create the panel
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(400, 500);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible

	}

}
