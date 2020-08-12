package Interfaces;

import java.awt.Color;

import javax.swing.JTextArea;

import ServerController.CardButtonListener;
import ServerController.CardsListener;
import View.INDOPanel;


public interface MyGameConstants extends MyUNOConstants {
	

	
	Color[] UnoColors = {BLUE,RED,  GREEN, YELLOW};
	Color WILD_CARDCOLOR = BLACK;
	int vsPC = 1;
	int Manual = 2;
        int Total_Cards = 108;
	int FirstHand = 8;
	
        int[] Uno_numbers =  {0,1,2,3,4,5,6,7,8,9};	
	String[] ActionTypes = {Revers,Skip,Draw2Plus};	
	String[] WildTypes = {W_ColorPicker, W_DrawPlus};
	
	
	
	int[] GameModes = {vsPC, Manual};
	
	CardsListener CardListener = new CardsListener();
	CardButtonListener ButonListenr = new CardButtonListener();
	
	INDOPanel infopanel = new INDOPanel();
}
