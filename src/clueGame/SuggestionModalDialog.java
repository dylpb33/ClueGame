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
import javax.swing.JTextField;

import clueGame.Card.CardType;

public class SuggestionModalDialog extends JDialog {
	private static Board board = Board.getInstance();
	private ArrayList<Card> roomCards;
	private ArrayList<Card> playerCards;
	private ArrayList<Card> weaponCards;
	private String[] rooms;
	private String[] players;
	private String[] weapons;
	private JComboBox<String> playerMenu;
	private JComboBox<String> weaponMenu;
	JTextField roomText = new JTextField(15);
	private Room currentRoom;
	private static ClueGame game = ClueGame.getInstance();

	public SuggestionModalDialog() {
		
		setLayout(new GridLayout(4,4));
		setTitle("Make a Suggestion");
		
		setPlayers(board);
		setWeapons(board);
		
		JLabel room = new JLabel("Current room");
		JLabel person = new JLabel("Person");
		JLabel weapon = new JLabel("Weapon");
		
		playerMenu = new JComboBox<String>(players);
		weaponMenu = new JComboBox<String>(weapons);
		
		roomText.setText(currentRoom.getName());
		roomText.setEditable(false);
		
		add(room);
		add(roomText);
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
			String room = currentRoom.getName();
			String person = playerMenu.getSelectedItem().toString();
			String weapon = weaponMenu.getSelectedItem().toString();
			board.getGameControlPanel().setGuess(person + ", " + room + ", " + weapon);
			HumanPlayer human = board.getHumanPlayer();
			
			Card suggestionCard = board.handleSuggestion(room, person, weapon, human);
			
			//Case where card is disproven
			if(suggestionCard != null) {
				//Update game control panel to include the guess
				board.getGameControlPanel().setGuessResult(board.getDisprovingPlayer().getName() + " disproves suggestion with: " + suggestionCard.getCardName());
				//Add the suggested card to seen cards
				human.setSeenCards(suggestionCard);
				//Update card panel to include the card
				board.getCardPanel().updatePanels();
				//Set the human player in board
				board.setHuman(human);
				//Update panel in frame
				ClueGame.getInstance().setVisible(true);
				
			}
			//Case where card is not disproven
			else {
				board.getGameControlPanel().setGuessResult("No new clue");
			}
			
			//Change location of player in suggestion to the correct room
			for(Player player : board.getPlayerArray()) {
				if(player.getName().equals(person)) {
					player.setInSuggestion(true);
					player.setRow(human.getRow());
					player.setColumn(human.getColumn());
				}
			}
		}
	}
	
	public void setCurrentRoom(Room r) {
		currentRoom = r;
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
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