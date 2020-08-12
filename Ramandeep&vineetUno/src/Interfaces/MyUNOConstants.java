package Interfaces;
import java.awt.Color;

public interface MyUNOConstants {
	
	//Colors
	public static Color RED = new Color(99,80,77);
	public static Color BLUE = new Color(35,73,125);
	public static Color GREEN = new Color(0,155,0);
	public static Color YELLOW = new Color(255,205,0);
	
	public static Color BLACK = new Color(0,0,0);
	
	//Types
	public static int NUMBERS = 1;
	public static int ACTION = 2;
	public static int WILD = 3;
	
	//ActionCard Characters
	Character charREVERSE = (char) 8634;							//Decimal
	Character charSKIP    = (char) Integer.parseInt("2718",16); 	//Unicode
	
	//ActionCard Functions
	String Revers = charREVERSE.toString();
	String Skip	= charSKIP.toString();
	String Draw2Plus = "2+";
	
	//Wild card functions
	String W_ColorPicker = "W";
	String W_DrawPlus = "4+";	
}
