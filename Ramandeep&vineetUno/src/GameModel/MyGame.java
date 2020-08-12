package GameModel;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

import CardModel.*;
import View.UnoCard;
import Interfaces.MyGameConstants;

public class MyGame implements MyGameConstants {

	private Players[] players;
	private boolean isOver;
	private int GAMEMODE;
	
	private MyPC pc;
	private Dealer dealer;
	private Stack<UnoCard> cardStack;
	
	
	public MyGame(int mode){
		
		GAMEMODE = mode;
		
		//Create players
		String name = (GAMEMODE==Manual) ? JOptionPane.showInputDialog("Player A") : "PC";	
		String name2 = JOptionPane.showInputDialog("Player B");
		
		if(GAMEMODE==vsPC)
			pc = new MyPC();
		
		Players player1 = (GAMEMODE==vsPC) ? pc : new Players(name);
		Players player2 = new Players(name2);		
		player2.toggleTurn();				//Initially, player2's turn		
			
		players = new Players[]{player1, player2};			
		
		//Create Dealer
		dealer = new Dealer();
		cardStack = dealer.shuffle();
		dealer.spreadOut(players);
		
		isOver = false;
	}

	public Players[] getPlayers() {
		return players;
	}

	public UnoCard getCard() {
		return dealer.getCard();
	}
	
	public void removePlayedCard(UnoCard playedCard) {

		for (Players p : players) {
			if (p.hasCard(playedCard)){
				p.removeCard(playedCard);
				
				if (p.getTotalCards() == 1 && !p.getSaidUNO()) {
					infopanel.setError(p.getName() + " Forgot to say UNO");
					p.obtainCard(getCard());
					p.obtainCard(getCard());
				}else if(p.getTotalCards()>2){
					p.setSaidUNOFalse();
				}
			}			
		}
	}
	
	//give player a card
	public void drawCard(UnoCard topCard) {

		boolean canPlay = false;

		for (Players p : players) {
			if (p.isMyTurn()) {
				UnoCard newCard = getCard();
				p.obtainCard(newCard);
				canPlay = canplay(topCard, newCard);
				break;
			}
		}

		if (!canPlay)
			switchTurn();
	}

	public void switchTurn() {
		for (Players p : players) {
			p.toggleTurn();
		}
		whoseTurn();
	}
	
	//Draw cards x times
	public void drawPlus(int times) {
		for (Players p : players) {
			if (!p.isMyTurn()) {
				for (int i = 1; i <= times; i++)
					p.obtainCard(getCard());
			}
		}
	}
	
	//response whose turn it is
	public void whoseTurn() {

		for (Players p : players) {
			if (p.isMyTurn()){
				infopanel.updateText(p.getName() + "'s Turn");
				System.out.println(p.getName() + "'s Turn");
			}
		}
		infopanel.setDetail(playedcardsize(), remainingCard());
		infopanel.repaint();
	}
	
	//return if the game is over
	public boolean isOver() {
		
		if(cardStack.isEmpty()){
			isOver= true;
			return isOver;
		}
		
		for (Players p : players) {
			if (!p.hasCards()) {
				isOver = true;
				break;
			}
		}
		
		return isOver;
	}

	public int remainingCard() {
		return cardStack.size();
	}

	public int[] playedcardsize() {
		int[] nr = new int[2];
		int i = 0;
		for (Players p : players) {
			nr[i] = p.totalPlayedCards();
			i++;
		}
		return nr;
	}

	//Check if this card can be played
	private boolean canplay(UnoCard topCard, UnoCard newCard) {

		// Color or value matches
		if (topCard.getColor().equals(newCard.getColor())
				|| topCard.getValue().equals(newCard.getValue()))
			return true;
		// if chosen wild card color matches
		else if (topCard.getType() == WILD)
			return ((WildCards) topCard).getWildColor().equals(newCard.getColor());

		// suppose the new card is a wild card
		else if (newCard.getType() == WILD)
			return true;

		// else
		return false;
	}

	//Check whether the player said or forgot to say UNO
	public void checkUNO() {
		for (Players p : players) {
			if (p.isMyTurn()) {
				if (p.getTotalCards() == 1 && !p.getSaidUNO()) {
					infopanel.setError(p.getName() + " Forgot to say UNO");
					p.obtainCard(getCard());
					p.obtainCard(getCard());
				}
			}
		}		
	}

	public void setsaiduno() {
		for (Players p : players) {
			if (p.isMyTurn()) {
				if (p.getTotalCards() == 2) {
					p.saysUNO();
					infopanel.setError(p.getName() + " said UNO");
				}
			}
		}
	}
	
	public boolean ispcturn(){
		if(pc.isMyTurn()){
			return true;
		}
		return false;
	}

	//if it's MyPC's turn, play it for pc
	public void playpcc(UnoCard topCard) {		
		
		if (pc.isMyTurn()) {
			boolean done = pc.play(topCard);
			
			if(!done)
				drawCard(topCard);
		}
	}
}
