package ServerController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardButtonListener implements ActionListener {
		
	MyServer myserver;
	
	public void setServer(MyServer server){
		myserver = server;
	}
	
	public void drawCard() {
		if(myserver.canPlay)
			myserver.requestCard();	
	}
	
	public void sayUNO() {
		if(myserver.canPlay)
			myserver.submitSaidUNO();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	
}
