package bot_vs_bot;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.util.Random;

public class Bot_E2 extends BOT {
	//Testing bot
	Color DARK_ORANGE = new Color(204, 102, 0);
	// variablen für "Sturm"
	private boolean wand = false;
	private boolean angriff = true;
	private String name;

	// variablen für "Deckung"
	private boolean start = true;

	public Bot_E2(String name, int x, int y, int leben, int schaden, int cooldown, int speed, int movespeed,
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
				if (game.getbot_team_B(i).getY() > y - 15 && game.getbot_team_B(i).getY() < y + 15
						&& game.getbot_team_B(i).getX() < x) {
					schuss(4, true);
				}
			}

			 // schuss quer

			
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
			hoch();
			heilen();
			schuss(4,false);
		} catch (ArrayIndexOutOfBoundsException ar) {

		}

	}
}
