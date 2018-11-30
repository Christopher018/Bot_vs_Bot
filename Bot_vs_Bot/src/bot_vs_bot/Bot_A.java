package bot_vs_bot;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.util.Random;

public class Bot_A extends BOT {
	//Testing bot 
	Color DARK_GREEN = new Color(6, 50, 8);
	Color Nummer_Blau = new Color(10, 100, 150);
	// variablen für "Sturm"
	private boolean wand = false;
	private boolean angriff = true;
	private String name;

	// variablen für "Deckung"
	private boolean start = true;
	// variable für eigene bewegung
	private boolean wechsel = true;

	public Bot_A(String name, int x, int y, int leben, int schaden, int cooldown, int speed, int movespeed,
			int faehigkeit, Game game) throws HeadlessException {
		super(name, x, y, leben, schaden, cooldown, speed, movespeed, faehigkeit, game);
		this.name = name;
		System.out.println(getNUMMER());
	}

	@Override
	public void paint_char(Graphics2D g) {
		
		g.setColor(DARK_GREEN);
		// g.drawImage(Toolkit.getDefaultToolkit().getImage("C:/Users/toffe/Desktop/Testing/Fireball.png"),
		// x, y, this);
		g.fillOval(x, y, 20, 20);

		String anzeigeNummer = String.valueOf(getNUMMER());
		if (game.InputKey.getAuswahl() == getNUMMER()) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Nummer_Blau);
		}

		if (getNUMMER() < 10) {
			g.drawString(anzeigeNummer, x + 7, y + 14);
		} else {
			g.drawString(anzeigeNummer, x + 3, y + 14);
		}
	}

	@Override
	public void bewegen() {
		/*
		 * hoch(); runter(); rechts(); links(); schuss(1-8,geprüft ob das Ziel da
		 * ist(true/false)); (cooldown();)
		 */
		still();
		// hier folgt beispiel Code

		// bewegung
		// ab hier eigener Code
		try {
			// schuss 2,4,5,7
			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).getX() > x - 15 && game.getbot_team_B(i).getX() < x + 15
						&& game.getbot_team_B(i).getY() > y) {
					schuss(7, true);
				}
			}

			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).getX() > x - 15 && game.getbot_team_B(i).getX() < x + 15
						&& game.getbot_team_B(i).getY() < y) {
					schuss(2, true);
				}
			}

			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).getY() > y - 15 && game.getbot_team_B(i).getY() < y + 15
						&& game.getbot_team_B(i).getX() < x) {
					schuss(4, true);
				}
			}

			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).getY() > y - 15 && game.getbot_team_B(i).getY() < y + 15
						&& game.getbot_team_B(i).getX() > x) {
					schuss(5, true);
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

			if (game.InputKey.getAuswahl() == getNUMMER()) {
				// manuelle steuerung
				if (getZielx() != 0 && getZiely() != 0) {
					if (wechsel) {
						if (getZielx() - x > 2 || getZielx() - x < -2) {
							if (getZielx() > x) {
								if (!isAverkehrt()) {
									rechts();
								} else {
									links();
								}
							} else {
								if (!isAverkehrt()) {
									links();
								} else {
									rechts();
								}
							}
						}
						wechsel = false;
					} else {
						if (getZiely() - y > 2 || getZiely() - y < -2) {
							if (getZiely() > y) {
								if (!isAverkehrt()) {
									runter();
								} else {
									hoch();
								}
							} else {
								if (!isAverkehrt()) {
									hoch();
								} else {
									runter();
								}
							}
						}
						wechsel = true;
					}

				}
			}

			// hoch();
			// schuss(2,false);
		} catch (ArrayIndexOutOfBoundsException ar) {

		}

	}

}
