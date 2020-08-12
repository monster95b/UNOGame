import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import View.MainFrame;
import java.awt.Color;

public class Main {
	
	public static void main(String[] args) {
		
		//Create Frame and invoke it.
		SwingUtilities.invokeLater(new Runnable() {					
			public void run() {
				JFrame frame = new MainFrame();
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setLocation(10, 10);
                                frame.setBackground(Color.GREEN);
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				
			}
		});	
	}
}
