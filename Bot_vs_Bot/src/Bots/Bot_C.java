package Bots;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.util.Random;

import Main.Game;

public class Bot_C extends BOT {
	//Testing bot
	Color DARK_ORANGE = new Color(204, 102, 0);
	// variablen f�r "Sturm"
	private boolean wand = false;
	private boolean angriff = true;
	private String name;

	// variablen f�r "Deckung"
	private boolean start = true;

	public Bot_C(String name, int x, int y, int leben, int schaden, int cooldown, int speed, int movespeed,
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
		 * hoch(); | runter(); | rechts(); | links(); 
		 * darfhoch(); | darfrunter(); | darfrechts(); | darflinks(); 
		 * schuss(1-8(richtung),gepr�ft ob das Ziel da ist(true/false));
		 * faehigkeit 1 = reparieren()
		 * faehigkeit 2 = heilen()
		 * faehigkeit 3 = schild(1-5)
		 */
		try {
			if (name.equals("BotC:Sturm")) {
				if (angriff) {
					if (!isBverkehrt()) {
						if (game.getbot_team_A(0).getX() < x - 10) {
							rechts();
						}
						if (game.getbot_team_A(0).getX() > x + 10) {
							links();
						}
						if (game.getbot_team_A(0).getY() > y + 10) {
							runter();
						}
						if (game.getbot_team_A(0).getY() < y - 10) {
							hoch();
						}
					} else {
						if (game.getbot_team_A(0).getX() > x + 10) {
							rechts();
						}
						if (game.getbot_team_A(0).getX() < x - 10) {
							links();
						}
						if (game.getbot_team_A(0).getY() < y - 10) {
							runter();
						}
						if (game.getbot_team_A(0).getY() > y + 10) {
							hoch();
						}
					}

					// schuss 2,4,5,7
					for (int i = 0; i < game.getAnzBotB(); i++) {
						if (game.getbot_team_B(i).getX() > x - 15 && game.getbot_team_B(i).getX() < x + 15
								&& game.getbot_team_B(i).getY() < y) {
							schuss(7, true);
						}
					}

					for (int i = 0; i < game.getAnzBotB(); i++) {
						if (game.getbot_team_B(i).getX() > x - 15 && game.getbot_team_B(i).getX() < x + 15
								&& game.getbot_team_B(i).getY() > y) {
							schuss(2, true);
						}
					}

					for (int i = 0; i < game.getAnzBotB(); i++) {
						if (game.getbot_team_B(i).getY() > y - 15 && game.getbot_team_B(i).getY() < y + 15
								&& game.getbot_team_B(i).getX() < x) {
							schuss(5, true);
						}
					}

					for (int i = 0; i < game.getAnzBotB(); i++) {
						if (game.getbot_team_B(i).getY() > y - 15 && game.getbot_team_B(i).getY() < y + 15
								&& game.getbot_team_B(i).getX() > x) {
							schuss(4, true);
						}
					} // schuss quer

					for (int i = 0; i < game.getAnzBotB(); i++) {
						if (x - game.getbot_team_B(i).getX() != 0) {
							if (game.getbot_team_B(i).getY() < y) {
								if ((y - game.getbot_team_B(i).getY()) / (x - game.getbot_team_B(i).getX()) == 1) {
									schuss(1, true);
								}
							}
						}

					}

					for (int i = 0; i < game.getAnzBotB(); i++) {
						if (x - game.getbot_team_B(i).getX() != 0) {
							if (game.getbot_team_B(i).getY() < y) {
								if ((y - game.getbot_team_B(i).getY()) / (game.getbot_team_B(i).getX() - x) == 1) {
									schuss(3, true);
								}
							}
						}

					}

					for (int i = 0; i < game.getAnzBotB(); i++) {
						if (x - game.getbot_team_B(i).getX() != 0) {
							if (game.getbot_team_B(i).getY() > y) {
								if ((game.getbot_team_B(i).getY() - y) / (x - game.getbot_team_B(i).getX()) == 1) {
									schuss(6, true);
								}
							}
						}

					}

					for (int i = 0; i < game.getAnzBotB(); i++) {
						if (x - game.getbot_team_B(i).getX() != 0) {
							if (game.getbot_team_B(i).getY() > y) {
								if ((game.getbot_team_B(i).getY() - y) / (game.getbot_team_B(i).getX() - x) == 1) {
									schuss(8, true);
								}
							}
						}

					}
				}
			}

			if (!darfhoch()) {
				schuss(2, false);
			}
			if (!darfrunter()) {
				schuss(7, false);
			}
			if (!darfrechts()) {
				schuss(5, false);
			}
			if (!darflinks()) {
				schuss(4, false);
			}

		} catch (

		ArrayIndexOutOfBoundsException ar) {

		}

	}

}
