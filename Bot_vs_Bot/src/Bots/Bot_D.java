package Bots;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.util.Random;

import Main.Game;
import Map_bestaende.Wand;

public class Bot_D extends BOT {
	//Testing bot
	Color DARK_GREEN = new Color(6, 50, 8);
	// private boolean wand = false;
	// private boolean beschussrechts = false;
	// private boolean beschussoben = false;
	// private int deckungszaehler = 0;

	public Bot_D(String name,int x, int y, int leben, int schaden, int cooldown, int speed, int movespeed, int faehigkeit,
			Game game) throws HeadlessException {
		super(name,x, y, leben, schaden, cooldown, speed, movespeed, faehigkeit, game);

		// waende spawnen
		Wand wand = new Wand(110, 458, 120, 20,2, game);
		game.addWaende(wand);

		
	}

	@Override
	public void paint_char(Graphics2D g) {
		g.setColor(DARK_GREEN);
		// g.drawImage(Toolkit.getDefaultToolkit().getImage("C:/Users/toffe/Desktop/Testing/Fireball.png"),
		// x, y, this);
		g.fillOval(x, y, 20, 20);
	}

	@Override
	public void bewegen() {
		/*
		 * hoch(); runter(); rechts(); links(); schuss(1-8,geprüft ob das Ziel da ist(true/false)); (cooldown();)
		 */
		still();
		//hier folgt beispiel Code

		// schuss 2,4,5,7

		for (int i = 0; i < game.getAnzBotB(); i++) {
			if (game.getbot_team_B(i).getX() > x - 15 && game.getbot_team_B(i).getX() < x + 15
					&& game.getbot_team_B(i).getY() < y) {
				schuss(2,true);
			}
		}

		for (int i = 0; i < game.getAnzBotB(); i++) {
			if (game.getbot_team_B(i).getX() > x - 15 && game.getbot_team_B(i).getX() < x + 15
					&& game.getbot_team_B(i).getY() > y) {
				schuss(7,true);
			}
		}

		for (int i = 0; i < game.getAnzBotB(); i++) {
			if (game.getbot_team_B(i).getY() > y - 15 && game.getbot_team_B(i).getY() < y + 15
					&& game.getbot_team_B(i).getX() < x) {
				schuss(4,true);
			}
		}

		for (int i = 0; i < game.getAnzBotB(); i++) {
			if (game.getbot_team_B(i).getY() > y - 15 && game.getbot_team_B(i).getY() < y + 15
					&& game.getbot_team_B(i).getX() > x) {
				schuss(5,true);
			}
		}
		// schuss quer 1,3,6,8

		for (int i = 0; i < game.getAnzBotB(); i++) {
			if (x - game.getbot_team_B(i).getX() != 0) {
				if (game.getbot_team_B(i).getY() < y) {
					if ((y - game.getbot_team_B(i).getY()) / (x - game.getbot_team_B(i).getX()) == 1) {
						schuss(1,true);
					}
				}
			}

		}

		for (int i = 0; i < game.getAnzBotB(); i++) {
			if (x - game.getbot_team_B(i).getX() != 0) {
				if (game.getbot_team_B(i).getY() < y) {
					if ((y - game.getbot_team_B(i).getY()) / (game.getbot_team_B(i).getX() - x) == 1) {
						schuss(3,true);
					}
				}
			}

		}

		for (int i = 0; i < game.getAnzBotB(); i++) {
			if (x - game.getbot_team_B(i).getX() != 0) {
				if (game.getbot_team_B(i).getY() > y) {
					if ((game.getbot_team_B(i).getY() - y) / (x - game.getbot_team_B(i).getX()) == 1) {
						schuss(6,true);
					}
				}
			}

		}

		for (int i = 0; i < game.getAnzBotB(); i++) {
			if (x - game.getbot_team_B(i).getX() != 0) {
				if (game.getbot_team_B(i).getY() > y) {
					if ((game.getbot_team_B(i).getY() - y) / (game.getbot_team_B(i).getX() - x) == 1) {
						schuss(8,true);
					}
				}
			}

		}

		// bewegung
		// ab hier eigener Code

		/*
		 * 
		 * if (isDarf_gehen() && wand == false) { hoch(); if (!isDarf_gehen()) { wand =
		 * true; } } else { if (!darfhoch()) { reparieren(); //links(); schild(5); }
		 * else { for (int i = 0; i < game.getAnzProjektil(); i++) { if
		 * (game.getProjektil(i).getRichtung() == 4 ||
		 * game.getProjektil(i).getRichtung() == 1 && game.getProjektil(i).getBesitzer()
		 * != 1) { beschussrechts = true; beschussoben = false; } if
		 * (game.getProjektil(i).getRichtung() == 6 &&
		 * game.getProjektil(i).getBesitzer() != 1) { beschussoben = true;
		 * beschussrechts = false; } }
		 * 
		 * if (beschussrechts == true) { if (deckungszaehler < 100) { hoch(); } else {
		 * runter(); } deckungszaehler++; if (deckungszaehler == 200) { beschussrechts =
		 * false; } } else if (beschussoben == true) { if (deckungszaehler < 100) {
		 * rechts(); } else { links(); } deckungszaehler++; if (deckungszaehler == 200)
		 * { beschussoben = false; } } else { schuss(5, 1); deckungszaehler = 0; } } }
		 */

	}

}
