package View;


import javax.swing.JFrame;
import ServerController.MyServer;
import Interfaces.MyGameConstants;


public class MainFrame extends JFrame implements MyGameConstants {
	
	private MySession mainPanel;
	private MyServer server;
	
	public MainFrame(){	
		server = new MyServer();
		CardListener.setServer(server);
		ButonListenr.setServer(server);
		
		mainPanel = server.getSession();
		add(mainPanel);
	}
}
