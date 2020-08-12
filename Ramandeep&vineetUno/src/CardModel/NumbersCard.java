package CardModel;
import java.awt.Color;

import View.UnoCard;

public class NumbersCard extends UnoCard {

	public NumbersCard(){
	}
	
	public NumbersCard(Color cardColor, String cardValue){
		super(cardColor, NUMBERS, cardValue);		
	}

}
