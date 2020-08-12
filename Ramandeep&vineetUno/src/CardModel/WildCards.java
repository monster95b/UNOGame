package CardModel;

import java.awt.Color;

import View.UnoCard;

public class WildCards extends UnoCard {
	
	private int Function = 0;
	private Color chosenColor;
	
	public WildCards() {
	}
	
	public WildCards(String cardValue){
		super(BLACK, WILD, cardValue);		
	}
	
	public void useWildColor(Color wildColor){
		chosenColor = wildColor;
	}
	
	public Color getWildColor(){
		return chosenColor;
	}

}
