package clueGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Card.CardType;

public class CardPanel extends JPanel{
	
	private ArrayList<Card> cardsInHand = new ArrayList<Card>();
	private ArrayList<Card> cardsSeen = new ArrayList<Card>();
	private int panelRows;
	private JTextField PeopleHand;
	private JTextField PeopleSeen;
	private JTextField RoomsHand;
	private JTextField RoomsSeen;
	private JTextField WeaponsHand;
	private JTextField WeaponsSeen;
	private JPanel peoplePanel;
	private JPanel roomPanel;
	private JPanel weaponPanel;
	
	//Default constructor that creates card panel.
	public CardPanel() {
		
		setBorder(new TitledBorder (new EtchedBorder(), "Known Cards"));
		setLayout(new GridLayout(3, 1));

		peoplePanel();
		add(peoplePanel);
		
		roomPanel();
		add(roomPanel);
		
		weaponPanel();
		add(weaponPanel);
		
		updatePanels();
		
	}
	
	public void updatePanels() {
		updatePanel(peoplePanel, CardType.PERSON);
		updatePanel(roomPanel, CardType.ROOM);
		updatePanel(weaponPanel , CardType.WEAPON);
	}
	
	public void peoplePanel() {		
		// creating the people panel 
		peoplePanel = new JPanel();
		
		//2+held+seen
		peoplePanel.setLayout(new GridLayout(0,1));
		peoplePanel.setBorder(new TitledBorder (new EtchedBorder(), "People"));
	}
	
	public void roomPanel() {
		// creating the rooms panel that goes in Known Cards
		roomPanel = new JPanel();
		roomPanel.setLayout(new GridLayout(0, 1));
		roomPanel.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
	}

	public void weaponPanel() {
		// creating the weapons panel that goes in Known Cards
		weaponPanel = new JPanel();
		weaponPanel.setLayout(new GridLayout(0, 1));
		weaponPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
	}
	
	public void updatePanel(JPanel panel, CardType card) {		
		if(cardsInHand != null && cardsSeen != null) {			
			panel.removeAll();
			if(card.equals(CardType.PERSON)) {
				
				JTextField noPeopleInHand = new JTextField(15);
				noPeopleInHand.setText("None");
				peoplePanel.add(noPeopleInHand);

				for(int i = 0; i < cardsInHand.size(); i++) {
					if(cardsInHand.get(i).getCardType().equals(CardType.PERSON)) {
						if(noPeopleInHand != null) {
							peoplePanel.remove(noPeopleInHand);
						}
						//Add in Hand text to people panel
						PeopleHand = new JTextField(15);
						PeopleHand.setText(cardsInHand.get(i).getCardName());
						peoplePanel.add(PeopleHand);
					}
				}

				//Add seen cards label to people panel
				JLabel seenLabel = new JLabel("Seen:");
				peoplePanel.add(seenLabel);
				
				JTextField noPeopleSeen = new JTextField(15);
				noPeopleSeen.setText("None");
				peoplePanel.add(noPeopleSeen);

				for(int i = 0; i < cardsSeen.size(); i++) {
					if(cardsSeen.get(i).getCardType().equals(CardType.PERSON)) {
						if(noPeopleSeen != null) {
							peoplePanel.remove(noPeopleSeen);
						}
						//Add seen cards text to people panel
						PeopleSeen = new JTextField(15);
						PeopleSeen.setText(cardsSeen.get(i).getCardName());
						peoplePanel.add(PeopleSeen);
					}
				}

			}
			else if(card.equals(CardType.ROOM)) {
				//Add in Hand label to room panel
				JLabel inHandLabel = new JLabel("In Hand:");
				roomPanel.add(inHandLabel);
				
				JTextField noRoomInHand = new JTextField(15);
				noRoomInHand.setText("None");
				roomPanel.add(noRoomInHand);

				for(int i = 0; i < cardsInHand.size(); i++) {
					if(cardsInHand.get(i).getCardType().equals(CardType.ROOM)) {
						if(noRoomInHand != null) {
							roomPanel.remove(noRoomInHand);
						}
						//Add rooms in hand text to room panel
						RoomsHand = new JTextField(15);
						RoomsHand.setText(cardsInHand.get(i).getCardName());
						roomPanel.add(RoomsHand);
					}
				}

				//Add seen cards label to room panel
				JLabel seenLabel = new JLabel("Seen:");
				roomPanel.add(seenLabel);
				
				JTextField noRoomSeen = new JTextField(15);
				noRoomSeen.setText("None");
				roomPanel.add(noRoomSeen);

				for(int i = 0; i < cardsSeen.size(); i++) {
					if(cardsSeen.get(i).getCardType().equals(CardType.ROOM)) {
						if(noRoomSeen != null) {
							roomPanel.remove(noRoomSeen);
						}
						//Add seen rooms text to room panel
						RoomsSeen = new JTextField(15);
						RoomsSeen.setText(cardsSeen.get(i).getCardName());
						roomPanel.add(RoomsSeen);
					}
				}

			}
			else {
				//Add in hand label to weapon panel
				JLabel inHandLabel = new JLabel("In Hand:");
				weaponPanel.add(inHandLabel);
				
				JTextField noWeaponInHand = new JTextField(15);
				noWeaponInHand.setText("None");
				weaponPanel.add(noWeaponInHand);

				for(int i = 0; i < cardsInHand.size(); i++) {
					if(cardsInHand.get(i).getCardType().equals(CardType.WEAPON)) {
						if(noWeaponInHand != null) {
							weaponPanel.remove(noWeaponInHand);
						}
						//Add hand text to weapon label
						WeaponsHand = new JTextField(15);
						WeaponsHand.setText(cardsInHand.get(i).getCardName());
						weaponPanel.add(WeaponsHand);
					}
				}

				//Add seen label to weapon panel
				JLabel seenLabel = new JLabel("Seen:");
				weaponPanel.add(seenLabel);

				JTextField noWeaponSeen = new JTextField(15);
				noWeaponSeen.setText("None");
				weaponPanel.add(noWeaponSeen);

				for(int i = 0; i < cardsSeen.size(); i++) {
					if(cardsSeen.get(i).getCardType().equals(CardType.WEAPON)) {
						if(noWeaponSeen != null) {
							weaponPanel.remove(noWeaponSeen);
						}
						//Add seen text to weapon panel
						WeaponsSeen = new JTextField(15);
						WeaponsSeen.setText(cardsSeen.get(i).getCardName());
						weaponPanel.add(WeaponsSeen);
					}
				}
			}
		}
	}

	
	public ArrayList<Card> getCardsInHand(){
		return cardsInHand;
	}
	
	public ArrayList<Card> getCardsSeen(){
		return cardsSeen;
	}
	
	public void setCardsInHand(Card card){
		cardsInHand.add(card);
		updatePanels();
	}
	
	public void setCardsSeen(Card card){
		cardsSeen.add(card);
		updatePanels();
	}
	
	public static void main(String[] args) {
		CardPanel panel = new CardPanel();  // create the panel
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible	}
	}
}
