package CardModel;

import java.awt.Color;
import java.util.LinkedList;

import ServerController.CardsListener;
import View.UnoCard;
import Interfaces.MyGameConstants;

/**
 * This Class contains standard 108-Card stack
 */
public final class MyCardDeck implements MyGameConstants {
	
	private final LinkedList<NumbersCard> numcards;
	private final LinkedList<ActionCard> actCards;
	private final LinkedList<WildCards> wildcards;
	
	private final LinkedList<UnoCard> UNOcards;
	
	public MyCardDeck(){
		
		//Initialize Cards
		numcards = new LinkedList<>();
		actCards = new LinkedList<>();
		wildcards = new LinkedList<>();
		
		UNOcards = new LinkedList<>();
		
		addCards();
		addCardListener(CardListener);
	}
	
	
	//Create 108 cards for this MyCardDeck
	private void addCards() {
		for(Color color:UnoColors){
			
			//Create 76 NumberCards --> doubles except 0s.
			for(int num : Uno_numbers){
				int i=0;
				do{
					UNOcards.add(new NumbersCard(color, Integer.toString(num)));
					i++;
				}while(num!=0 && i<2);
			}
			
			//Create 24 ActionCards --> everything twice
			for(String type : ActionTypes){
				for(int i=0;i<2;i++)
					UNOcards.add(new ActionCard(color, type));
			}					
		}		
		
		for(String type : WildTypes){
			for(int i=0;i<4;i++){
				UNOcards.add(new WildCards(type));
			}
		}
		
	}
	
	//Cards have MouseListener
	public void addCardListener(CardsListener listener){
            UNOcards.forEach((card) -> {
                card.addMouseListener(listener);
            });
	}
	
	public LinkedList<UnoCard> getCards(){
		return UNOcards;
	}
}
