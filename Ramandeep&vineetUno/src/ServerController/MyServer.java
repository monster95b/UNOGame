package ServerController;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import CardModel.WildCards;
import GameModel.MyGame;
import GameModel.Players;
import View.MySession;
import View.UnoCard;
import Interfaces.MyGameConstants;

public class MyServer implements MyGameConstants {
	private MyGame game;
	private MySession session;
	private Stack<UnoCard> playedCards;
	public boolean canPlay;
	private int mode;

	public MyServer() {

		mode = requestMode();
		game = new MyGame(mode);
		playedCards = new Stack<UnoCard>();

		// First Card
		UnoCard firstCard = game.getCard();
		modifyFirstCard(firstCard);

		playedCards.add(firstCard);
		session = new MySession(game, firstCard);

		game.whoseTurn();
		canPlay = true;
	}

	//return if it's 2-Players's mode or PC-mode
	private int requestMode() {

		Object[] options = { "vs PC", "Manual", "Cancel" };

		int n = JOptionPane.showOptionDialog(null,
				"Choose a Game Mode to start", "Game Mode",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[0]);

		if (n == 2)
			System.exit(1);

		return GameModes[n];
	}
	
	//coustom settings for the first card
	private void modifyFirstCard(UnoCard firstCard) {
		firstCard.removeMouseListener(CardListener);
		if (firstCard.getType() == WILD) {
			int random = new Random().nextInt() % 4;
			try {
				((WildCards) firstCard).useWildColor(UnoColors[Math.abs(random)]);
			} catch (Exception ex) {
				System.out.println("Something wrong ");
			}
		}
	}
	
	//return Main Panel
	public MySession getSession() {
		return this.session;
	}
	
	
	//request to play a card
	public void playThisCard(UnoCard clickedCard) {

		// Check player's turn
		if (!isHisTurn(clickedCard)) {
			infopanel.setError("It is not your turn");
			infopanel.repaint();
		} else {

			// Card validation
			if (isValidMove(clickedCard)) {

				clickedCard.removeMouseListener(CardListener);
				playedCards.add(clickedCard);
				game.removePlayedCard(clickedCard);

				// function cards ??
				switch (clickedCard.getType()) {
				case ACTION:
					performAction(clickedCard);
					break;
				case WILD:
					performWild((WildCards) clickedCard);
					break;
				default:
					break;
				}

				game.switchTurn();
				session.updatePanel(clickedCard);
				checkResults();
			} else {
				infopanel.setError("Invalid move");
				infopanel.repaint();
			}
			
		}
		
		
		
		if(mode==vsPC && canPlay){
			if(game.ispcturn()){
				game.playpcc(peekTopCard());
			}
		}
	}

	//Check if the game is over
	private void checkResults() {

		if (game.isOver()) {
			canPlay = false;
			infopanel.updateText("GAME OVER");
		}
	}
	
	//check player's turn
	public boolean isHisTurn(UnoCard clickedCard) {

		for (Players p : game.getPlayers()) {
			if (p.hasCard(clickedCard) && p.isMyTurn())
				return true;
		}
		return false;
	}

	//check if it is a valid card
	public boolean isValidMove(UnoCard playedCard) {
		UnoCard topCard = peekTopCard();

		if (playedCard.getColor().equals(topCard.getColor())
				|| playedCard.getValue().equals(topCard.getValue())) {
			return true;
		}

		else if (playedCard.getType() == WILD) {
			return true;
		} else if (topCard.getType() == WILD) {
			Color color = ((WildCards) topCard).getWildColor();
			if (color.equals(playedCard.getColor()))
				return true;
		}
		return false;
	}

	// ActionCards
	private void performAction(UnoCard actionCard) {

		// Draw2PLUS
		if (actionCard.getValue().equals(Draw2Plus))
			game.drawPlus(2);
		else if (actionCard.getValue().equals(Revers))
			game.switchTurn();
		else if (actionCard.getValue().equals(Skip))
			game.switchTurn();
	}

	private void performWild(WildCards functionCard) {		

		//System.out.println(game.whoseTurn());
		if(mode==1 && game.ispcturn()){			
			int random = new Random().nextInt() % 4;
			functionCard.useWildColor(UnoColors[Math.abs(random)]);
		}
		else{
			
			ArrayList<String> colors = new ArrayList<String>();
			colors.add("RED");
			colors.add("BLUE");
			colors.add("GREEN");
			colors.add("YELLOW");
			
			String chosenColor = (String) JOptionPane.showInputDialog(null,
					"Choose a color", "Wild Card Color",
					JOptionPane.DEFAULT_OPTION, null, colors.toArray(), null);
	
			functionCard.useWildColor(UnoColors[colors.indexOf(chosenColor)]);
		}
		
		if (functionCard.getValue().equals(W_DrawPlus))
			game.drawPlus(4);
	}
	
	public void requestCard() {
		game.drawCard(peekTopCard());
		
		if(mode==vsPC && canPlay){
			if(game.ispcturn())
				game.playpcc(peekTopCard());
		}
		
		session.refreshPanel();
	}

	public UnoCard peekTopCard() {
		return playedCards.peek();
	}

	public void submitSaidUNO() {
		game.setsaiduno();
	}
}
