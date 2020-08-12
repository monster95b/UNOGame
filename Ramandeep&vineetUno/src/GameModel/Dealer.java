package GameModel;

import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import CardModel.MyCardDeck;
import View.PlayerPanel;
import View.UnoCard;
import Interfaces.MyGameConstants;

public class Dealer implements MyGameConstants {
	
	private MyCardDeck cardDeck;
	private Stack<UnoCard> CardStack;	
	
	public Dealer(){
		this.cardDeck = new MyCardDeck();
	}
	
	//Shuffle cards
	public Stack<UnoCard> shuffle(){
		
		LinkedList<UnoCard> DeckOfCards = cardDeck.getCards();
		LinkedList<UnoCard> shuffledCards = new LinkedList<UnoCard>();
		
		while(!DeckOfCards.isEmpty()){
			int totalCards = DeckOfCards.size();
			
			Random random = new Random();
			int pos = (Math.abs(random.nextInt()))% totalCards;
			
			UnoCard randomCard = DeckOfCards.get(pos);
			DeckOfCards.remove(pos);
			shuffledCards.add(randomCard);
		}
		
		CardStack = new Stack<UnoCard>();
		for(UnoCard card : shuffledCards){
			CardStack.add(card);
		}
		
		return CardStack;
	}
	
	//Spread cards to players - 8 each
	public void spreadOut(Players[] players){		
		
		for(int i=1;i<=FirstHand;i++){
			for(Players p : players){
				p.obtainCard(CardStack.pop());
			}
		}		
	}
	
	public UnoCard getCard(){
		return CardStack.pop();
	}
}
