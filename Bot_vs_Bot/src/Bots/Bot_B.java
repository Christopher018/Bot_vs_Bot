package Bots;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.util.Random;

import Main.Game;

public class Bot_B extends BOT {
	//Testing bot
	Color DARK_ORANGE = new Color(204, 102, 0);
	// variablen für "Sturm"
	private boolean wand = false;
	private boolean angriff = true;
	private String name;

	// variablen für "Deckung"
	private boolean start = true;

	public Bot_B(String name, int x, int y, int leben, int schaden, int cooldown, int speed, int movespeed,
			int faehigkeit, Game game) throws HeadlessException {
		super(name, x, y, leben, schaden, cooldown, speed, movespeed, faehigkeit, game);
		this.name = name;
		

	}

	@Override
	public void paint_char(Graphics2D g) {
		g.setColor(DARK_ORANGE);
		// g.drawImage(Toolkit.getDefaultToolkit().getImage("C:/Users/toffe/Desktop/Testing/Fireball.png"),
		// x, y, this);
		g.fillOval(x, y, 20, 20);
	}

	@Override
	public void bewegen() {
		/*
		 * hoch(); runter(); rechts(); links(); schuss(1-8,geprüft ob das Ziel da
		 * ist(true/false)); (cooldown();)
		 */
		still();
		// hier folgt beispiel Code

		// schuss 2,4,5,7

		/*
		 * for (int i = 0; i < game.getAnzBotB(); i++) { if
		 * (game.getbot_team_B(i).getX() > x - 15 && game.getbot_team_B(i).getX() < x +
		 * 15 && game.getbot_team_B(i).getY() < y) { schuss(2,true); } }
		 * 
		 * for (int i = 0; i < game.getAnzBotB(); i++) { if
		 * (game.getbot_team_B(i).getX() > x - 15 && game.getbot_team_B(i).getX() < x +
		 * 15 && game.getbot_team_B(i).getY() > y) { schuss(7,true); } }
		 * 
		 * for (int i = 0; i < game.getAnzBotB(); i++) { if
		 * (game.getbot_team_B(i).getY() > y - 15 && game.getbot_team_B(i).getY() < y +
		 * 15 && game.getbot_team_B(i).getX() < x) { schuss(4,true); } }
		 * 
		 * for (int i = 0; i < game.getAnzBotB(); i++) { if
		 * (game.getbot_team_B(i).getY() > y - 15 && game.getbot_team_B(i).getY() < y +
		 * 15 && game.getbot_team_B(i).getX() > x) { schuss(5,true); } } // schuss quer
		 * 1,3,6,8
		 * 
		 * for (int i = 0; i < game.getAnzBotB(); i++) { if (x -
		 * game.getbot_team_B(i).getX() != 0) { if (game.getbot_team_B(i).getY() < y) {
		 * if ((y - game.getbot_team_B(i).getY()) / (x - game.getbot_team_B(i).getX())
		 * == 1) { schuss(1,true); } } }
		 * 
		 * }
		 * 
		 * for (int i = 0; i < game.getAnzBotB(); i++) { if (x -
		 * game.getbot_team_B(i).getX() != 0) { if (game.getbot_team_B(i).getY() < y) {
		 * if ((y - game.getbot_team_B(i).getY()) / (game.getbot_team_B(i).getX() - x)
		 * == 1) { schuss(3,true); } } }
		 * 
		 * }
		 * 
		 * for (int i = 0; i < game.getAnzBotB(); i++) { if (x -
		 * game.getbot_team_B(i).getX() != 0) { if (game.getbot_team_B(i).getY() > y) {
		 * if ((game.getbot_team_B(i).getY() - y) / (x - game.getbot_team_B(i).getX())
		 * == 1) { schuss(6,true); } } }
		 * 
		 * }
		 * 
		 * for (int i = 0; i < game.getAnzBotB(); i++) { if (x -
		 * game.getbot_team_B(i).getX() != 0) { if (game.getbot_team_B(i).getY() > y) {
		 * if ((game.getbot_team_B(i).getY() - y) / (game.getbot_team_B(i).getX() - x)
		 * == 1) { schuss(8,true); } } }
		 * 
		 * }
		 */
		// bewegung
		// ab hier eigener Code

		if (name.equals("BotC:Sturm")) {
			if (angriff) {
				if (wand == false) {
					links();
					if (!darflinks()) {
						hoch();
						if (isBverkehrt()) {
							if (y - 30 > game.getbot_team_A(0).getY()) {
								wand = true;
							}
						} else {
							if (y + 30 < game.getbot_team_A(0).getY()) {
								wand = true;
							}
						}
					}
				} else {
					rechts();
					if (x - 10 < game.getbot_team_A(0).getX() && x + 10 > game.getbot_team_A(0).getX()) {
						schuss(7, false);
						angriff = false;
					}
				}
			} else {
				rechts();
				if (!darfrechts()) {
					runter();
				}
				if (!isCooldownaktiv()) {
					angriff = true;
					wand = false;
				}
			}
		}
		try {
			if (name.equals("BotC:Deckung")) {
				if (start) {
					schuss(5, false);
				}

				if (game.getbot_team_B(0).getY() < 450 && game.getbot_team_B(0).getX() < 50) {
					start = false;
				} else {
					start = true;
				}

				if (darfhoch()) {
					schild(5);
				}
			}
		} catch (ArrayIndexOutOfBoundsException ar) {

		} catch (NullPointerException ar) {

		}

	}

}