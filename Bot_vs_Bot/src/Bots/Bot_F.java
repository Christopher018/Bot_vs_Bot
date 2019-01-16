package Bots;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.util.Random;

import Main.Game;

public class Bot_F extends BOT {
	//Testing bot
	Color DARK_ORANGE = new Color(204, 102, 0);
	private String name;



	public Bot_F(String name, int x, int y, int leben, int schaden, int cooldown, int speed, int movespeed,
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
		 * schuss(1-8(richtung),geprüft ob das Ziel da ist(true/false));
		 * faehigkeit 1 = reparieren()
		 * faehigkeit 2 = heilen()
		 * faehigkeit 3 = schild(1-5)
		 */
		
		try {
			// schuss 2,4,5,7
			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).getX() > x - 15 && game.getbot_team_A(i).getX() < x + 15
						&& game.getbot_team_A(i).getY() > y) {
					schuss(7, true);
				}
			}
			
			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).getX() > x - 15 && game.getbot_team_A(i).getX() < x + 15
						&& game.getbot_team_A(i).getY() < y) {
					schuss(2, true);
				}
			}

			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).getY() > y - 15 && game.getbot_team_A(i).getY() < y + 15
						&& game.getbot_team_A(i).getX() < x) {
					schuss(4, true);
				}
			}

			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).getY() > y - 15 && game.getbot_team_A(i).getY() < y + 15
						&& game.getbot_team_A(i).getX() > x) {
					schuss(5, true);
				}
			} // schuss quer

			for (int i = 0; i < game.getAnzBot(); i++) {
				if (x - game.getbot_team_A(i).getX() != 0) {
					if (game.getbot_team_A(i).getY() < y) {
						if ((y - game.getbot_team_A(i).getY()) / (x - game.getbot_team_A(i).getX()) == 1) {
							schuss(1, true);
						}
					}
				}

			}

			for (int i = 0; i < game.getAnzBot(); i++) {
				if (x - game.getbot_team_A(i).getX() != 0) {
					if (game.getbot_team_A(i).getY() < y) {
						if ((y - game.getbot_team_A(i).getY()) / (game.getbot_team_A(i).getX() - x) == 1) {
							schuss(3, true);
						}
					}
				}

			}

			for (int i = 0; i < game.getAnzBot(); i++) {
				if (x - game.getbot_team_A(i).getX() != 0) {
					if (game.getbot_team_A(i).getY() > y) {
						if ((game.getbot_team_A(i).getY() - y) / (x - game.getbot_team_A(i).getX()) == 1) {
							schuss(6, true);
						}
					}
				}

			}

			for (int i = 0; i < game.getAnzBot(); i++) {
				if (x - game.getbot_team_A(i).getX() != 0) {
					if (game.getbot_team_A(i).getY() > y) {
						if ((game.getbot_team_A(i).getY() - y) / (game.getbot_team_A(i).getX() - x) == 1) {
							schuss(8, true);
						}
					}
				}

			}
			
		
			// hoch();
			// schuss(2,false);
		} catch (ArrayIndexOutOfBoundsException ar) {

		}

	}

}
