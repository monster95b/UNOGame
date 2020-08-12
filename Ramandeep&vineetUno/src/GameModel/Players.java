package GameModel;

import java.util.LinkedList;

import View.UnoCard;

public class Players {
	
	private String name = null;
	private boolean isMyTurn = false;
	private boolean saidUNO = false;
	private LinkedList<UnoCard> myCards;
	
	private int playedCards = 0;
	
	public Players(){
		myCards = new LinkedList<UnoCard>();
	}
	
	public Players(String player){
		setName(player);
		myCards = new LinkedList<UnoCard>();
	}
	
	public void setName(String newName){
		name = newName;
	}
	
	public String getName(){
		return this.name;
	}
	public void obtainCard(UnoCard card){
		myCards.add(card);
	}
	
	public LinkedList<UnoCard> getAllCards(){
		return myCards;
	}
	
	public int getTotalCards(){
		return myCards.size();
	}
	
	public boolean hasCard(UnoCard thisCard){
		return myCards.contains(thisCard);		
	}
	
	public void removeCard(UnoCard thisCard){
		myCards.remove(thisCard);
		playedCards++;
	}
	
	public int totalPlayedCards(){
		return playedCards;
	}
	
	public void toggleTurn(){
		isMyTurn = (isMyTurn) ? false : true;
	}
	
	public boolean isMyTurn(){
		return isMyTurn;
	}
	
	public boolean hasCards(){
		return (myCards.isEmpty()) ? false : true;
	}
	
	public boolean getSaidUNO(){
		return saidUNO;
	}
	
	public void saysUNO(){
		saidUNO = true;
	}
	
	public void setSaidUNOFalse(){
		saidUNO = false;
	}
	
	public void setCards(){
		myCards = new LinkedList<UnoCard>();
	}
}
