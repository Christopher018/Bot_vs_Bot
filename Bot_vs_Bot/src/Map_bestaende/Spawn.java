package Map_bestaende;

import java.awt.Frame;

import javax.swing.JFrame;

import Bots.Bot_A;
import Bots.Bot_B;
import Bots.Bot_E;
import Bots.Bot_E2;
import Bots.Bot_E3;
import Bots.Bot_F;
import Main.Game;

public class Spawn extends JFrame {
	Game game;
	private boolean Averkehrt = false;
	private boolean Bverkehrt = false;

	public Spawn(Game game, boolean Aoben, int welchesteamA, int welchesteamB) {
		this.game = game;
		// spawnen von Bots // hoechster stat 16
		// werte muessen min 1 sein, max 16 und alle insgesamt max 23 (ohne faehigkeit)
		// (wird nicht geprueft... have fun)
		// (x, y, leben, schaden, cooldown, speed, movespeed,Faehigkeit, game);
		if (welchesteamA == 1) {
			Bot_A bot_a = new Bot_A("BotA:Sturm", 307, 480, 5, 1, 16, 4, 2, 3, game);
			game.addBot_Team_A(bot_a);
			
			// waende spawnen
			Wand wand1 = new Wand(40, 458, 200, 20, 1, game);
			game.addWaende(wand1);

			Wand wand3 = new Wand(400, 458, 200, 20, 1, game);
			game.addWaende(wand3);
		}
		
		if (welchesteamA == 2) {
			game.frame.setSize(900, 800);
			Bot_A bot_a = new Bot_A("BotA:Sturm", 134, 667, 5, 1, 16, 4, 2, 3, game);
			game.addBot_Team_A(bot_a);
			
			//waende spawnen
			//vier gebaeude (links)
			int x = 108;
			int y = 200;
			Wand wand = new Wand(x - 75, y - 75, 15, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 15 - 75, y - 75, 40, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 15 - 75, 15, 40, 2, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 95 - 75, 15, 40, 2, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 135 - 75, 15, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 15 - 75, y + 135 - 75, 40, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 95 - 75, y + 135 - 75, 40, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 135 - 75, 15, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 95 - 75, 15, 40, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 15 - 75, 15, 40, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 95 - 75, y - 75, 40, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y - 75, 15, 15, 2, game);
			game.addWaende(wand);
			
			x = 293;
			y = 200;
			wand = new Wand(x - 75, y - 75, 15, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 15 - 75, y - 75, 40, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 15 - 75, 15, 40, 2, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 95 - 75, 15, 40, 2, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 135 - 75, 15, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 15 - 75, y + 135 - 75, 40, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 95 - 75, y + 135 - 75, 40, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 135 - 75, 15, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 95 - 75, 15, 40, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 15 - 75, 15, 40, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 95 - 75, y - 75, 40, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y - 75, 15, 15, 2, game);
			game.addWaende(wand);
			
			
			
			
			
			
			
			x = 108;
			y = 460;
			wand = new Wand(x - 75, y - 75, 15, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 15 - 75, y - 75, 40, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 15 - 75, 15, 40, 1, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 95 - 75, 15, 40, 1, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 135 - 75, 15, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 15 - 75, y + 135 - 75, 40, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 95 - 75, y + 135 - 75, 40, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 135 - 75, 15, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 95 - 75, 15, 40, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 15 - 75, 15, 40, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 95 - 75, y - 75, 40, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y - 75, 15, 15, 1, game);
			game.addWaende(wand);
			
			x = 293;
			y = 460;
			wand = new Wand(x - 75, y - 75, 15, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 15 - 75, y - 75, 40, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 15 - 75, 15, 40, 1, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 95 - 75, 15, 40, 1, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 135 - 75, 15, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 15 - 75, y + 135 - 75, 40, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 95 - 75, y + 135 - 75, 40, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 135 - 75, 15, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 95 - 75, 15, 40, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 15 - 75, 15, 40, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 95 - 75, y - 75, 40, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y - 75, 15, 15, 1, game);
			game.addWaende(wand);
			
			//rechts von oberen gebaeude
			
			wand = new Wand(411, 260, 82, 15, 2, game);
			game.addWaende(wand);
			wand = new Wand(493, 260, 15, 15, 2, game);
			game.addWaende(wand);
			wand = new Wand(493, 169, 15, 92, 2, game);
			game.addWaende(wand);
			
			
			
			wand = new Wand(493, 83, 15, 33, 2, game);
			game.addWaende(wand);
			wand = new Wand(493, 68, 15, 15, 2, game);
			game.addWaende(wand);
			wand = new Wand(508, 68, 240, 15, 2, game);
			game.addWaende(wand);
			wand = new Wand(748, 68, 15, 15, 2, game);
			game.addWaende(wand);
			wand = new Wand(748, 83, 15, 33, 2, game);
			game.addWaende(wand);
			
			
			wand = new Wand(508, 192, 50, 15, 2, game);
			game.addWaende(wand);
			wand = new Wand(583, 192, 30, 15, 2, game);
			game.addWaende(wand);
			wand = new Wand(663, 192, 30, 15, 2, game);
			game.addWaende(wand);
			wand = new Wand(714, 192, 34, 15, 2, game);
			game.addWaende(wand);
			wand = new Wand(748, 192, 15, 15, 2, game);
			game.addWaende(wand);
			wand = new Wand(748, 169, 15, 23, 2, game);
			game.addWaende(wand);
			
			//kleines gebaeude ganz rechts
			x = 809;
			y = 342;
			wand = new Wand(x - 75, y - 75, 15, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 15 - 75, y - 75, 40, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 15 - 75, 15, 40, 2, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 95 - 75, 15, 40, 1, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 135 - 75, 15, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 15 - 75, y + 135 - 75, 40, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 95 - 75, y + 135 - 75, 40, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 135 - 75, 15, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 95 - 75, 15, 40, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 15 - 75, 15, 40, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 95 - 75, y - 75, 40, 15, 2, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y - 75, 15, 15, 2, game);
			game.addWaende(wand);
			
			//kleines gebaeude rechts
			x = 552;
			y = 460;
			wand = new Wand(x - 75, y - 75, 15, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 15 - 75, y - 75, 40, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 15 - 75, 15, 40, 1, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 95 - 75, 15, 40, 1, game);
			game.addWaende(wand);

			wand = new Wand(x - 75, y + 135 - 75, 15, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 15 - 75, y + 135 - 75, 40, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 95 - 75, y + 135 - 75, 40, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 135 - 75, 15, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 95 - 75, 15, 40, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y + 15 - 75, 15, 40, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 95 - 75, y - 75, 40, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(x + 135 - 75, y - 75, 15, 15, 1, game);
			game.addWaende(wand);
			
			//mauer rechts
			
			wand = new Wand(670, 439 , 15, 220, 1, game);
			game.addWaende(wand);

			wand = new Wand(670, 352 , 15, 33, 1, game);
			game.addWaende(wand);
			
			//tueren schliessen
			
			wand = new Wand(789, 402 , 40, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(353, 440 , 15, 40, 1, game);
			game.addWaende(wand);
			
			
			//laengliches haus mitte unten
			
			wand = new Wand(477, 670 , 15, 92, 1, game);
			game.addWaende(wand);

			wand = new Wand(477, 534 , 15, 99, 1, game);
			game.addWaende(wand);
			
			wand = new Wand(437, 534 , 40, 15, 1, game);
			game.addWaende(wand);
			
			wand = new Wand(363, 534 , 40, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(352, 534 , 15, 230, 1, game);
			game.addWaende(wand);
			
			//laengliches haus rechts unten
			
			wand = new Wand(734, 417 , 15, 125, 1, game);
			game.addWaende(wand);

			wand = new Wand(734, 577 , 15, 103, 1, game);
			game.addWaende(wand);
			
			wand = new Wand(734, 680 , 15, 15, 1, game);
			game.addWaende(wand);
			
			wand = new Wand(749, 680 , 135, 15, 1, game);
			game.addWaende(wand);
			
			
			//wand links unten
			
			wand = new Wand(71, 604 , 180, 15, 1, game);
			game.addWaende(wand);

			wand = new Wand(251, 604 , 15, 15, 1, game);
			game.addWaende(wand);
			
			wand = new Wand(251, 619 , 15, 60, 1, game);
			game.addWaende(wand);
		}

		if (welchesteamB == 1) {
			Bot_B bot_c = new Bot_B("BotC:Sturm", 307, 530, 2, 5, 1, 1, 16, 3, game);
			game.addBot_Team_B(bot_c);

			Bot_B bot_c2 = new Bot_B("BotC:Deckung", 46, 435, 5, 2, 12, 4, 2, 3, game);
			game.addBot_Team_B(bot_c2);
			
			// waende spawnen
			Wand wand1 = new Wand(40, 458, 200, 20, 2, game);
			game.addWaende(wand1);

			Wand wand2 = new Wand(270, 458, 100, 20, 2, game);
			game.addWaende(wand2);

			Wand wand3 = new Wand(400, 458, 200, 20, 2, game);
			game.addWaende(wand3);

			Wand wand4 = new Wand(40, 415, 80, 20, 2, game);
			game.addWaende(wand4);
		}
		
		if (welchesteamB == 2) {
			Bot_E bot_a = new Bot_E("BotE:Sturm", 307, 530, 5, 1, 16, 4, 2, 3, game);
			game.addBot_Team_B(bot_a);

			Bot_E2 bot_a2 = new Bot_E2("BotE:Sturm", 297, 550, 5, 1, 16, 4, 2, 2, game);
			game.addBot_Team_B(bot_a2);

			Bot_E3 bot_a3 = new Bot_E3("BotE:Sturm", 317, 550, 5, 1, 16, 4, 2, 2, game);
			game.addBot_Team_B(bot_a3);
			
			
		}
		
		if (welchesteamB == 3) {
			Bot_F bot_f = new Bot_F("BotF:Sturm", 227, 62, 5, 1, 16, 4, 2, 3, game);
			game.addBot_Team_B(bot_f);
		}
		
		
		
		
		
		// oben und unten wechseln

		if (Aoben) {
			if (game.getbot_team_A(0).getY() > 300) {
				for (int i = 0; i < game.getAnzBot(); i++) {
					game.getbot_team_A(i).setY(game.getHeight() - game.getbot_team_A(i).getY() - 20);
					game.getbot_team_A(i).setX(game.getWidth() - game.getbot_team_A(i).getX() - 20);
					game.getbot_team_A(i).setAverkehrt(true);
				}
			}
			if (game.getbot_team_B(0).getY() < 300) {
				for (int i = 0; i < game.getAnzBotB(); i++) {
					game.getbot_team_B(i).setY(game.getHeight() - game.getbot_team_B(i).getY() - 20);
					game.getbot_team_B(i).setX(game.getWidth() - game.getbot_team_B(i).getX() - 20);
					game.getbot_team_B(i).setBverkehrt(false);
				}
			}

			for (int i = 0; i < game.getAnzWaende(); i++) {
				if (game.getWaende(i).getWelchesTeam() == 1) {
					if (game.getWaende(i).getY() > 300) {
						game.getWaende(i)
								.setY(game.getHeight() - game.getWaende(i).getY() - game.getWaende(i).getHoehe());
						game.getWaende(i)
								.setX(game.getWidth() - game.getWaende(i).getX() - game.getWaende(i).getBreite());
					}
				}
				
				if (game.getWaende(i).getWelchesTeam() == 2) {
					if (game.getWaende(i).getY() < 300) {
						game.getWaende(i)
								.setY(game.getHeight() - game.getWaende(i).getY() - game.getWaende(i).getHoehe());
						game.getWaende(i)
								.setX(game.getWidth() - game.getWaende(i).getX() - game.getWaende(i).getBreite());
					}
				}
			}

		}

		if (!Aoben) {
			if (game.getbot_team_A(0).getY() < 300) {
				for (int i = 0; i < game.getAnzBot(); i++) {
					game.getbot_team_A(i).setY(game.getHeight() - game.getbot_team_A(i).getY() + 20);
					game.getbot_team_A(i).setX(game.getWidth() - game.getbot_team_A(i).getX() - 20);
					game.getbot_team_A(i).setAverkehrt(false);
				}
			}
			if (game.getbot_team_B(0).getY() > 300) {
				for (int i = 0; i < game.getAnzBotB(); i++) {
					game.getbot_team_B(i).setY(game.getHeight() - game.getbot_team_B(i).getY() - 20);
					game.getbot_team_B(i).setX(game.getWidth() - game.getbot_team_B(i).getX() - 20);
					game.getbot_team_B(i).setBverkehrt(true);
				}
			}

			for (int i = 0; i < game.getAnzWaende(); i++) {
				if (game.getWaende(i).getWelchesTeam() == 2) {
					if (game.getWaende(i).getY() > 300) {
						game.getWaende(i)
								.setY(game.getHeight() - game.getWaende(i).getY() - game.getWaende(i).getHoehe());
						game.getWaende(i)
								.setX(game.getWidth() - game.getWaende(i).getX() - game.getWaende(i).getBreite());
					}
				}
			}
		}

	}
}
