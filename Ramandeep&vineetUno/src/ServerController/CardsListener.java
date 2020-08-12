package ServerController;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import View.UnoCard;

public class CardsListener extends MouseAdapter {
	
	UnoCard sourceCard;
	MyServer myServer;
	
	public void setServer(MyServer server){
		myServer = server;
	}
	
        @Override
	public void mousePressed(MouseEvent e) {		
		sourceCard = (UnoCard) e.getSource();
		
		try{
			if(myServer.canPlay)
				myServer.playThisCard(sourceCard);			
			
		}catch(NullPointerException ex){
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);		
		
		sourceCard = (UnoCard) e.getSource();
		Point p = sourceCard.getLocation();
		p.y -=20;
		sourceCard.setLocation(p);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		sourceCard = (UnoCard) e.getSource();
		Point p = sourceCard.getLocation();
		p.y +=20;
		sourceCard.setLocation(p);
	}	

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}	

}
