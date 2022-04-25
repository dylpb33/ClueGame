package clueGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import clueGame.Card.CardType;

public class AccusationModalDialog extends JDialog {
	private static Board board = Board.getInstance();
	private ArrayList<Card> roomCards;
	private ArrayList<Card> playerCards;
	private ArrayList<Card> weaponCards;
	private String[] rooms;
	private String[] players;
	private String[] weapons;
	private JComboBox<String> roomMenu;
	private JComboBox<String> playerMenu;
	private JComboBox<String> weaponMenu;

	public AccusationModalDialog() {
		
		setLayout(new GridLayout(4,4));
		setTitle("Make an Accusation");
		
		setRooms(board);
		setPlayers(board);
		setWeapons(board);
		
		JLabel room = new JLabel("Room");
		JLabel person = new JLabel("Person");
		JLabel weapon = new JLabel("Weapon");
		
		roomMenu = new JComboBox<String>(rooms);
		playerMenu = new JComboBox<String>(players);
		weaponMenu = new JComboBox<String>(weapons);
		
		add(room);
		add(roomMenu);
		add(person);
		add(playerMenu);
		add(weapon);
		add(weaponMenu);
		add(this.cancelButton());
		add(this.submitButton());
		
		setSize(600, 300);
		setVisible(true);
		

	}
	
	private JButton cancelButton() {
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new cancelListener());
		return cancelButton;
	}
	
	private JButton submitButton() {
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new submitListener());
		return submitButton;
	}
	
	private class cancelListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
	
	private class submitListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			String room = roomMenu.getSelectedItem().toString();
			String person = playerMenu.getSelectedItem().toString();
			String weapon = weaponMenu.getSelectedItem().toString();
			
			if(board.checkAccusation(room, person, weapon)) {
				JOptionPane.showMessageDialog(null, "That is correct! You win!");
				System.exit(0);
			}
			else {
				JOptionPane.showMessageDialog(null, "Sorry, not correct! You lose!");
				System.exit(0);
			}
		}
	}
	
	private void setRooms(Board board) {
		//Create arrayList of room cards
		roomCards = new ArrayList<Card>();
		for(Card c : board.getDeck()) {
			if(c.getCardType() == CardType.ROOM) {
				roomCards.add(c);
			}
		}
		
		//Fill array with room cards
		rooms = new String[roomCards.size()];
		int i = 0;
		for(Card c : roomCards) {
			rooms[i] = c.getCardName();
			i++;
		}
		
	}
	
	private void setPlayers(Board board) {
		//Create arrayList of player cards
		playerCards = new ArrayList<Card>();
		for(Card c : board.getDeck()) {
			if(c.getCardType() == CardType.PERSON) {
				playerCards.add(c);
			}
		}
		
		//Fill array with player cards
		players = new String[playerCards.size()];
		int i = 0;
		for(Card c : playerCards) {
			players[i] = c.getCardName();
			i++;
		}
		
	}
	
	private void setWeapons(Board board) {
		//Create arrayList of weapon cards
		weaponCards = new ArrayList<Card>();
		for(Card c : board.getDeck()) {
			if(c.getCardType() == CardType.WEAPON) {
				weaponCards.add(c);
			}
		}
		
		//Fill array with weapon cards
		weapons = new String[weaponCards.size()];
		int i = 0;
		for(Card c : weaponCards) {
			weapons[i] = c.getCardName();
			i++;
		}
		
	}

}
