package Input;

import java.awt.MouseInfo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Bots.Bot_A;
import Bots.Bot_F;
import Main.Game;
import Map_bestaende.Objektiv;
import Map_bestaende.Wand;
import Stat_anzeige.DataModel;
import View.Bot_teams;
import View.Infos;
import View.Spielfeld;
import View.TableView;

public class InputKey extends KeyAdapter {
	private boolean start = false;
	private Game game;
	private JFrame frame;
	private Infos infos;
	private Bot_teams teams;
	private TableView view;
	private DataModel model;
	private boolean stop = false;
	private boolean del_infos = false;
	private boolean del_stats = false;
	private boolean del_teams = false;

	private boolean auswaehlen = false;
	private int auswahl = 0;
	// welche Nr vom Objekt
	private int schiebauswahl = 0;
	// 1 = wand/ 2 = Bot a / 3 = Bot b
	private int schiebauswahlobjekt = 0;
	// damit maus nicht ständig auf objekt sein muss
	private boolean selbesobjekt;

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		// alle möglichen eingaben

		//f1 = hilfe wird aufgerufen
		if (key == KeyEvent.VK_F1) {
			if (!del_infos) {
				infos = new Infos(frame);
			} else {
				infos.dispose();
			}

			if (del_infos) {
				del_infos = false;
			} else {
				del_infos = true;
			}
		}

		//enter = spiel startet/pausiert
		if (key == KeyEvent.VK_ENTER) {
			if (!stop) {
				start = true;
			} else {
				start = false;
			}

			if (!stop) {
				stop = true;
			} else {
				stop = false;
			}
		}

		//s = Stats werden angezeigt von Bots
		if (key == KeyEvent.VK_S) {
			if (!del_stats) {
				model = new DataModel(game);
				view = new TableView(model, game, frame);

			} else {
				view.dispose();
			}

			if (del_stats) {
				del_stats = false;
			} else {
				del_stats = true;
			}
		}

		//fenster wird geoeffnet in dem man vorgefertigte Teams waehlen kann
		if (key == KeyEvent.VK_T) {
			if (!del_teams) {
				teams = new Bot_teams(game, frame, infos);
			} else {
				teams.dispose();
			}

			if (del_teams) {
				del_teams = false;
			} else {
				del_teams = true;
			}
		}

		//alles loeschen/ groesse vom feld anpassen
		if (key == KeyEvent.VK_R) {
			game.alleBots_Team_A.removeAllElements();
			game.alleBots_Team_B.removeAllElements();
			game.alleProjektile.removeAllElements();
			game.alleWaende.removeAllElements();
			game.alleSchilde.removeAllElements();
			game.alleObjektives.removeAllElements();
			game.alleSpielfelder.removeAllElements();

			Spielfeld Spielfeld = new Spielfeld(frame);
			game.addSpielfeld(Spielfeld);

			stop = false;

			// arena umranden mit waenden
			Wand linkewand = new Wand(-20, 0, frame.getWidth(), frame.getHeight(), 20, frame.getHeight(), 10000, game);
			game.addWaende(linkewand);
			Wand rechtewand = new Wand(frame.getWidth() - 16, 0, frame.getWidth(), frame.getHeight(), 20,
					frame.getHeight(), 10000, game);
			game.addWaende(rechtewand);
			Wand unterewand = new Wand(0, frame.getHeight() - 39, frame.getWidth(), frame.getHeight(), frame.getWidth(),
					20, 10000, game);
			game.addWaende(unterewand);
			Wand oberewand = new Wand(0, -20, frame.getWidth(), frame.getHeight(), frame.getWidth(), 20, 10000, game);
			game.addWaende(oberewand);

			start = false;

		}
		
		//1 = Team a BotA wird erstellt beim Mauszeiger
		if (key == KeyEvent.VK_1) {
			if (!auswaehlen) {
				int x = (int) (frame.getMousePosition().getX() - 7);
				int y = (int) (frame.getMousePosition().getY() - 31);
				Bot_A bot_a = new Bot_A("BotA:Sturm", x - 10, y - 10, 5, 1, 16, 4, 2, 3, game);
				game.addBot_Team_A(bot_a);
			} else {
				auswahl = 1;
			}
		}
		//2 = Team b Botf wird erstellt beim Mauszeiger
		if (key == KeyEvent.VK_2) {
			if (!auswaehlen) {
				int x = (int) (frame.getMousePosition().getX() - 7);
				int y = (int) (frame.getMousePosition().getY() - 31);
				Bot_F Bot_F = new Bot_F("BotF:Sturm", x - 10, y - 10, 5, 1, 16, 4, 2, 3, game);
				game.addBot_Team_B(Bot_F);
			} else {
				auswahl = 2;
			}
		}
		//3 = objektive wird erstellt beim Mauszeiger
		if (key == KeyEvent.VK_3) {
			if (!auswaehlen) {
				int x = (int) (frame.getMousePosition().getX() - 7);
				int y = (int) (frame.getMousePosition().getY() - 31);
				Objektiv objektiv = new Objektiv(game, x - 50, y - 50, 100, 100);
				game.addObjektiv(objektiv);
			} else {
				auswahl = 3;
			}
		}
		//4 = ein "haus" wird erstellt beim Mauszeiger
		if (key == KeyEvent.VK_4) {
			if (!auswaehlen) {
				int x = (int) (frame.getMousePosition().getX() - 7);
				int y = (int) (frame.getMousePosition().getY() - 31);
				Wand wand1 = new Wand(x - 75, y - 75, 15, 15, 1, game);
				game.addWaende(wand1);

				Wand wand2 = new Wand(x + 15 - 75, y - 75, 40, 15, 1, game);
				game.addWaende(wand2);

				Wand wand5 = new Wand(x - 75, y + 15 - 75, 15, 40, 1, game);
				game.addWaende(wand5);

				Wand wand6 = new Wand(x - 75, y + 95 - 75, 15, 40, 1, game);
				game.addWaende(wand6);

				Wand wand7 = new Wand(x - 75, y + 135 - 75, 15, 15, 1, game);
				game.addWaende(wand7);

				Wand wand8 = new Wand(x + 15 - 75, y + 135 - 75, 40, 15, 1, game);
				game.addWaende(wand8);

				Wand wand9 = new Wand(x + 95 - 75, y + 135 - 75, 40, 15, 1, game);
				game.addWaende(wand9);

				Wand wand10 = new Wand(x + 135 - 75, y + 135 - 75, 15, 15, 1, game);
				game.addWaende(wand10);

				Wand wand11 = new Wand(x + 135 - 75, y + 95 - 75, 15, 40, 1, game);
				game.addWaende(wand11);

				Wand wand12 = new Wand(x + 135 - 75, y + 15 - 75, 15, 40, 1, game);
				game.addWaende(wand12);

				Wand wand3 = new Wand(x + 95 - 75, y - 75, 40, 15, 1, game);
				game.addWaende(wand3);

				Wand wand4 = new Wand(x + 135 - 75, y - 75, 15, 15, 1, game);
				game.addWaende(wand4);
			} else {
				auswahl = 4;
			}
		}
		if (key == KeyEvent.VK_5) {
			if (!auswaehlen) {
			} else {
				auswahl = 5;
			}
		}

		if (key == KeyEvent.VK_6) {
			if (!auswaehlen) {
			} else {
				auswahl = 6;
			}
		}

		if (key == KeyEvent.VK_7) {
			if (!auswaehlen) {
			} else {
				auswahl = 7;
			}
		}

		if (key == KeyEvent.VK_8) {
			if (!auswaehlen) {
			} else {
				auswahl = 8;
			}
		}

		if (key == KeyEvent.VK_9) {
			if (!auswaehlen) {
			} else {
				auswahl = 9;
			}
		}

		if (key == KeyEvent.VK_0) {
			if (!auswaehlen) {
			} else {
				auswahl = 0;
			}
		}
		//wechselt zwischen den beiden modi
		if (key == KeyEvent.VK_CAPS_LOCK) {
			if (!auswaehlen) {
				auswaehlen = true;
			} else {
				auswaehlen = false;
			}

		}
		// alles folgende ist zum verschieben ausgewaehlter Objekte indem man den Mauszeiger auf das Objekt tut
		if (key == KeyEvent.VK_LEFT) {
			selbesobjekt = true;
			int x = (int) (frame.getMousePosition().getX() - 7);
			int y = (int) (frame.getMousePosition().getY() - 31);
			for (int i = 0; game.getAnzWaende() > i; i++) {
				if (game.getWaende(i).getX() < x && game.getWaende(i).getX() + game.getWaende(i).getBreite() > x) {
					if (game.getWaende(i).getY() < y && game.getWaende(i).getY() + game.getWaende(i).getHoehe() > y) {
						game.getWaende(i).setX(game.getWaende(i).getX() - 1);
						schiebauswahl = i;
						schiebauswahlobjekt = 1;
						selbesobjekt = false;
					}
				}
			}

			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).getX() < x && game.getbot_team_A(i).getX() + 20 > x) {
					if (game.getbot_team_A(i).getY() < y && game.getbot_team_A(i).getY() + 20 > y) {
						game.getbot_team_A(i).setX(game.getbot_team_A(i).getX() - 1);
						schiebauswahl = i;
						schiebauswahlobjekt = 2;
						selbesobjekt = false;
					}
				}
			}

			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).getX() < x && game.getbot_team_B(i).getX() + 20 > x) {
					if (game.getbot_team_B(i).getY() < y && game.getbot_team_B(i).getY() + 20 > y) {
						game.getbot_team_B(i).setX(game.getbot_team_B(i).getX() - 1);
						schiebauswahl = i;
						schiebauswahlobjekt = 3;
						selbesobjekt = false;
					}
				}
			}
			
			for (int i = 0; i < game.getAnzObjektiv(); i++) {
				if (game.getObjektiv(i).getX() < x && game.getObjektiv(i).getX() + 100 > x) {
					if (game.getObjektiv(i).getY() < y && game.getObjektiv(i).getY() + 100 > y) {
						game.getObjektiv(i).setX(game.getObjektiv(i).getX() - 1);
						schiebauswahl = i;
						schiebauswahlobjekt = 4;
						selbesobjekt = false;
					}
				}
			}

			if (selbesobjekt) {
				if (schiebauswahlobjekt == 1) {
					game.getWaende(schiebauswahl).setX(game.getWaende(schiebauswahl).getX() - 1);
				}
				if (schiebauswahlobjekt == 2) {
					game.getbot_team_A(schiebauswahl).setX(game.getbot_team_A(schiebauswahl).getX() - 1);
				}
				if (schiebauswahlobjekt == 3) {
					game.getbot_team_B(schiebauswahl).setX(game.getbot_team_B(schiebauswahl).getX() - 1);
				}
				if (schiebauswahlobjekt == 4) {
					game.getObjektiv(schiebauswahl).setX(game.getObjektiv(schiebauswahl).getX() - 1);
				}
			}

		}
		
		if (key == KeyEvent.VK_RIGHT) {
			selbesobjekt = true;
			int x = (int) (frame.getMousePosition().getX() - 7);
			int y = (int) (frame.getMousePosition().getY() - 31);
			for (int i = 0; game.getAnzWaende() > i; i++) {
				if (game.getWaende(i).getX() < x && game.getWaende(i).getX() + game.getWaende(i).getBreite() > x) {
					if (game.getWaende(i).getY() < y && game.getWaende(i).getY() + game.getWaende(i).getHoehe() > y) {
						game.getWaende(i).setX(game.getWaende(i).getX() + 1);
						schiebauswahl = i;
						schiebauswahlobjekt = 1;
						selbesobjekt = false;
					}
				}
			}

			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).getX() < x && game.getbot_team_A(i).getX() + 20 > x) {
					if (game.getbot_team_A(i).getY() < y && game.getbot_team_A(i).getY() + 20 > y) {
						game.getbot_team_A(i).setX(game.getbot_team_A(i).getX() + 1);
						schiebauswahl = i;
						schiebauswahlobjekt = 2;
						selbesobjekt = false;
					}
				}
			}

			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).getX() < x && game.getbot_team_B(i).getX() + 20 > x) {
					if (game.getbot_team_B(i).getY() < y && game.getbot_team_B(i).getY() + 20 > y) {
						game.getbot_team_B(i).setX(game.getbot_team_B(i).getX() + 1);
						schiebauswahl = i;
						schiebauswahlobjekt = 3;
						selbesobjekt = false;
					}
				}
			}
			
			for (int i = 0; i < game.getAnzObjektiv(); i++) {
				if (game.getObjektiv(i).getX() < x && game.getObjektiv(i).getX() + 100 > x) {
					if (game.getObjektiv(i).getY() < y && game.getObjektiv(i).getY() + 100 > y) {
						game.getObjektiv(i).setX(game.getObjektiv(i).getX() + 1);
						schiebauswahl = i;
						schiebauswahlobjekt = 4;
						selbesobjekt = false;
					}
				}
			}

			if (selbesobjekt) {
				if (schiebauswahlobjekt == 1) {
					game.getWaende(schiebauswahl).setX(game.getWaende(schiebauswahl).getX() + 1);
				}
				if (schiebauswahlobjekt == 2) {
					game.getbot_team_A(schiebauswahl).setX(game.getbot_team_A(schiebauswahl).getX() + 1);
				}
				if (schiebauswahlobjekt == 3) {
					game.getbot_team_B(schiebauswahl).setX(game.getbot_team_B(schiebauswahl).getX() + 1);
				}
				if (schiebauswahlobjekt == 4) {
					game.getObjektiv(schiebauswahl).setX(game.getObjektiv(schiebauswahl).getX() + 1);
				}
			}

		}
		
		if (key == KeyEvent.VK_UP) {
			selbesobjekt = true;
			int x = (int) (frame.getMousePosition().getX() - 7);
			int y = (int) (frame.getMousePosition().getY() - 31);
			for (int i = 0; game.getAnzWaende() > i; i++) {
				if (game.getWaende(i).getX() < x && game.getWaende(i).getX() + game.getWaende(i).getBreite() > x) {
					if (game.getWaende(i).getY() < y && game.getWaende(i).getY() + game.getWaende(i).getHoehe() > y) {
						game.getWaende(i).setY(game.getWaende(i).getY() - 1);
						schiebauswahl = i;
						schiebauswahlobjekt = 1;
						selbesobjekt = false;
					}
				}
			}

			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).getX() < x && game.getbot_team_A(i).getX() + 20 > x) {
					if (game.getbot_team_A(i).getY() < y && game.getbot_team_A(i).getY() + 20 > y) {
						game.getbot_team_A(i).setY(game.getbot_team_A(i).getY() - 1);
						schiebauswahl = i;
						schiebauswahlobjekt = 2;
						selbesobjekt = false;
					}
				}
			}

			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).getX() < x && game.getbot_team_B(i).getX() + 20 > x) {
					if (game.getbot_team_B(i).getY() < y && game.getbot_team_B(i).getY() + 20 > y) {
						game.getbot_team_B(i).setY(game.getbot_team_B(i).getY() - 1);
						schiebauswahl = i;
						schiebauswahlobjekt = 3;
						selbesobjekt = false;
					}
				}
			}
			
			for (int i = 0; i < game.getAnzObjektiv(); i++) {
				if (game.getObjektiv(i).getX() < x && game.getObjektiv(i).getX() + 100 > x) {
					if (game.getObjektiv(i).getY() < y && game.getObjektiv(i).getY() + 100 > y) {
						game.getObjektiv(i).setY(game.getObjektiv(i).getY() - 1);
						schiebauswahl = i;
						schiebauswahlobjekt = 4;
						selbesobjekt = false;
					}
				}
			}

			if (selbesobjekt) {
				if (schiebauswahlobjekt == 1) {
					game.getWaende(schiebauswahl).setY(game.getWaende(schiebauswahl).getY() - 1);
				}
				if (schiebauswahlobjekt == 2) {
					game.getbot_team_A(schiebauswahl).setY(game.getbot_team_A(schiebauswahl).getY() - 1);
				}
				if (schiebauswahlobjekt == 3) {
					game.getbot_team_B(schiebauswahl).setY(game.getbot_team_B(schiebauswahl).getY() - 1);
				}
				if (schiebauswahlobjekt == 4) {
					game.getObjektiv(schiebauswahl).setY(game.getObjektiv(schiebauswahl).getY() - 1);
				}
			}

		}
		
		if (key == KeyEvent.VK_DOWN) {
			selbesobjekt = true;
			int x = (int) (frame.getMousePosition().getX() - 7);
			int y = (int) (frame.getMousePosition().getY() - 31);
			for (int i = 0; game.getAnzWaende() > i; i++) {
				if (game.getWaende(i).getX() < x && game.getWaende(i).getX() + game.getWaende(i).getBreite() > x) {
					if (game.getWaende(i).getY() < y && game.getWaende(i).getY() + game.getWaende(i).getHoehe() > y) {
						game.getWaende(i).setY(game.getWaende(i).getY() + 1);
						schiebauswahl = i;
						schiebauswahlobjekt = 1;
						selbesobjekt = false;
					}
				}
			}

			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).getX() < x && game.getbot_team_A(i).getX() + 20 > x) {
					if (game.getbot_team_A(i).getY() < y && game.getbot_team_A(i).getY() + 20 > y) {
						game.getbot_team_A(i).setY(game.getbot_team_A(i).getY() + 1);
						schiebauswahl = i;
						schiebauswahlobjekt = 2;
						selbesobjekt = false;
					}
				}
			}

			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).getX() < x && game.getbot_team_B(i).getX() + 20 > x) {
					if (game.getbot_team_B(i).getY() < y && game.getbot_team_B(i).getY() + 20 > y) {
						game.getbot_team_B(i).setY(game.getbot_team_B(i).getY() + 1);
						schiebauswahl = i;
						schiebauswahlobjekt = 3;
						selbesobjekt = false;
					}
				}
			}
			
			for (int i = 0; i < game.getAnzObjektiv(); i++) {
				if (game.getObjektiv(i).getX() < x && game.getObjektiv(i).getX() + 100 > x) {
					if (game.getObjektiv(i).getY() < y && game.getObjektiv(i).getY() + 100 > y) {
						game.getObjektiv(i).setY(game.getObjektiv(i).getY() + 1);
						schiebauswahl = i;
						schiebauswahlobjekt = 4;
						selbesobjekt = false;
					}
				}
			}

			if (selbesobjekt) {
				if (schiebauswahlobjekt == 1) {
					game.getWaende(schiebauswahl).setY(game.getWaende(schiebauswahl).getY() + 1);
				}
				if (schiebauswahlobjekt == 2) {
					game.getbot_team_A(schiebauswahl).setY(game.getbot_team_A(schiebauswahl).getY() + 1);
				}
				if (schiebauswahlobjekt == 3) {
					game.getbot_team_B(schiebauswahl).setY(game.getbot_team_B(schiebauswahl).getY() + 1);
				}
				if (schiebauswahlobjekt == 4) {
					game.getObjektiv(schiebauswahl).setY(game.getObjektiv(schiebauswahl).getY() + 1);
				}
			}

		}

		frame.requestFocus();
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public InputKey(Game game, JFrame frame) {
		super();
		this.game = game;
		this.frame = frame;

	}

	public int getAuswahl() {
		return auswahl;
	}

}
