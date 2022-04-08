package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Card.CardType;

public class CardPanel extends JPanel{
	
	private Card card;
	private JTextField PeopleHand;
	private JTextField PeopleSeen;
	private JTextField RoomsHand;
	private JTextField RoomsSeen;
	private JTextField WeaponsHand;
	private JTextField WeaponsSeen;
	private JPanel peoplePanel;
	private JPanel roomPanel;
	private JPanel weaponPanel;

	public CardPanel() {
		// creating the known cards panel
		JPanel knownCards = new JPanel();
		knownCards.setBorder(new TitledBorder (new EtchedBorder(), "Known Cards"));
		knownCards.setLayout(new GridLayout(3, 1));

		peoplePanel();
		knownCards.add(peoplePanel);
		
		roomPanel();
		knownCards.add(roomPanel);
		
		weaponPanel();
		knownCards.add(weaponPanel);
		
		add(knownCards);
		
	}
	
	public void updatePanels() {
		updatePanel(peoplePanel, Card.CardType.PERSON);
		updatePanel(roomPanel, Card.CardType.ROOM);
		updatePanel(weaponPanel , Card.CardType.WEAPON);
	}
	
	public void peoplePanel() {		
		// creating the people panel 
		peoplePanel = new JPanel();
		peoplePanel.setLayout(new GridLayout(0, 1));
		peoplePanel.setBorder(new TitledBorder (new EtchedBorder(), "People"));

		//Add in Hand label to people panel
		JLabel inHandLabel = new JLabel("In Hand:");
		peoplePanel.add(inHandLabel);
		
		//Add in Hand text to people panel
		PeopleHand = new JTextField(20);
		PeopleHand.setText("None");
		peoplePanel.add(PeopleHand);

		//Add seen cards label to people panel
		JLabel seenLabel = new JLabel("Seen:");
		peoplePanel.add(seenLabel);
		
		//Add seen cards text to people panel
		PeopleSeen = new JTextField(25);
		PeopleSeen.setText("None");
		peoplePanel.add(PeopleSeen);
		
	}
	
	public void roomPanel() {
		// creating the rooms panel that goes in Known Cards
		roomPanel = new JPanel();
		roomPanel.setLayout(new GridLayout(0, 1));
		roomPanel.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
		
		//Add in Hand label to room panel
		JLabel inHandLabel = new JLabel("In Hand:");
		roomPanel.add(inHandLabel);
		
		//Add rooms in hand text to room panel
		RoomsHand = new JTextField(20);
		RoomsHand.setText("None");
		roomPanel.add(RoomsHand);

		//Add seen cards label to room panel
		JLabel seenLabel = new JLabel("Seen:");
		roomPanel.add(seenLabel);
		
		//Add seen rooms text to room panel
		RoomsSeen = new JTextField(25);
		RoomsSeen.setText("None");
		roomPanel.add(RoomsSeen);
	}

	public void weaponPanel() {
		// creating the weapons panel that goes in Known Cards
		weaponPanel = new JPanel();
		weaponPanel.setLayout(new GridLayout(0, 1));
		weaponPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));

		//Add in hand label to weapon panel
		JLabel inHandLabel = new JLabel("In Hand:");
		weaponPanel.add(inHandLabel);
		
		//Add hand text to weapon label
		WeaponsHand = new JTextField(20);
		WeaponsHand.setText("None");
		weaponPanel.add(WeaponsHand);
		
		//Add hand text to weapon label
		WeaponsHand = new JTextField(20);
		WeaponsHand.setText("None2");
		weaponPanel.add(WeaponsHand);
		
		//Add seen label to weapon panel
		JLabel seenLabel = new JLabel("Seen:");
		weaponPanel.add(seenLabel);
		
		//Add seen text to weapon panel
		WeaponsSeen = new JTextField(25);
		WeaponsSeen.setText("None");
		weaponPanel.add(WeaponsSeen);
		
		//Add hand text to weapon label
		WeaponsHand = new JTextField(20);
		WeaponsHand.setText("None2");
		weaponPanel.add(WeaponsHand);
	}
	
	public void updatePanel(JPanel panel, CardType c) {
			panel.removeAll();
			if(c == Card.CardType.PERSON) {
				
			}
			else if(c == Card.CardType.ROOM) {
				
			}
			else {
				
			}
	}
	

	
	public static void main(String[] args) {
		CardPanel panel = new CardPanel();  // create the panel
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(400, 500);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
		
		//Create a test Board
		Board testBoard = Board.getInstance();
		
		//set file names to use my config files
		testBoard.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		
		//Initialize will load config files
		testBoard.initialize();
		
		//Deal deck of cards to players
		testBoard.deal();
		
		//Mark all cards not in human players hand as seen for testing
		for(Card card : testBoard.getDeck()) {
			if(!testBoard.getHumanPlayer().getHand().contains(card)) {
				testBoard.getHumanPlayer().setSeenCards(card);
			}
		}
		
		//Add cards in hand to correct panel
		for(Card card: testBoard.getHumanPlayer().getHand()) {
			
		}
		
		//Add cards seen to correct panel
		for(Card card: testBoard.getHumanPlayer().getSeenCards()) {
			
		}

	}

}
