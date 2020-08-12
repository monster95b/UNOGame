package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import GameModel.Dealer;
import GameModel.MyGame;
import GameModel.Players;
import ServerController.CardsListener;

public class MySession extends JPanel {
	private PlayerPanel player1;
	private PlayerPanel player2;
	private MyTablePanel table;	
	
	private MyGame game;
	
	public MySession(MyGame newGame, UnoCard firstCard){
		setPreferredSize(new Dimension(960,720));
		setBackground(new Color(30,36,40));
		setLayout(new BorderLayout());
		
		game = newGame;
		
		setPlayers();
		table = new MyTablePanel(firstCard);
		player1.setOpaque(false);
		player2.setOpaque(false);
		
		add(player1,BorderLayout.NORTH);
		add(table, BorderLayout.CENTER);
		add(player2, BorderLayout.SOUTH);		
	}
	
	private void setPlayers() {
		player1 = new PlayerPanel(game.getPlayers()[0]);
		player2 = new PlayerPanel(game.getPlayers()[1]);		
	}
	
	public void refreshPanel(){
		player1.setCards();
		player2.setCards();
		
		table.revalidate();		
		revalidate();
	}
	
	public void updatePanel(UnoCard playedCard){
		table.setPlayedCard(playedCard);
		refreshPanel();
	}	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
