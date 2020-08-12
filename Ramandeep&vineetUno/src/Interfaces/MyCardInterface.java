package Interfaces;
import java.awt.*;

public interface MyCardInterface{
	
	int width = 50;
	int height = 75;
	Dimension SMALL = new Dimension(width,height);
	Dimension MEDIUM = new Dimension(width*2,height*2);
	Dimension BIG = new Dimension(width*3,height*3);	
	
	//Default card size
	Dimension CARDSIZE = MEDIUM;
	
	//Default offset
	int OFFSET = 71;
	
	void setColor(Color newColor);
	Color getColor();
	
	void setValue(String newValue);
	String getValue();
	
	void setType(int newType);
	int getType();
}
