package CardModel;
import java.awt.Color;

import View.UnoCard;

public class ActionCard extends UnoCard{
	
	private int Function = 0;
	
	//Constructor
	public ActionCard(){
	}
	
	public ActionCard(Color cardColor, String cardValue){
		super(cardColor,ACTION, cardValue);		
	}	
}
