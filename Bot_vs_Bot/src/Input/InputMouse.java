package Input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Main.Game;
import Map_bestaende.Wand;
import View.klick_anzeige;

public class InputMouse extends MouseAdapter {
	private Game game;
	private boolean klick = true;
	private int x;
	private int y;
	private int breite;
	private int hoehe;
	private int x2;
	private int y2;
	private int kurzspeicher;

	public InputMouse(Game game) {
		this.game = game;
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getButton() == 1) {
			if (!game.InputKey.isStart()) {
				if (klick == true) {
					//zeichnet viereck wo man hingeklickt hat
					x = arg0.getX() - 7;
					y = arg0.getY() - 31;
					for (int i = 0; game.getAnzWaende() > i; i++) {
						if (game.getWaende(i).getX() > x - 6 && game.getWaende(i).getX() < x + 6) {
							x = game.getWaende(i).getX();
						}
					}
					for (int i = 0; game.getAnzWaende() > i; i++) {
						if (game.getWaende(i).getY() > y - 6 && game.getWaende(i).getY() < y + 6) {
							y = game.getWaende(i).getY();
						}
					}
					klick_anzeige klick = new klick_anzeige(x, y);
					game.addklick(klick);
					System.out.println("x: " + x + "  y: " + y);
				} else {
					//erstellt Wand
					game.alleklicks.removeAllElements();
					x2 = arg0.getX() - 12;
					y2 = arg0.getY() - 36;

					breite = x - x2;
					hoehe = y - y2;

					if (y2 < y) {
						y = y2 + 5;
					}
					if (x2 < x) {
						x = x2 + 5;
					}

					if (breite < 0) {
						breite = arg0.getX() - 7 - x;
					}

					if (hoehe < 0) {
						hoehe = arg0.getY() - 30 - y;
					}

					for (int i = 0; game.getAnzWaende() > i; i++) {
						if (game.getWaende(i).getBreite() > breite - 6 && game.getWaende(i).getBreite() < breite + 6) {
							breite = game.getWaende(i).getBreite();
						}
					}
					for (int i = 0; game.getAnzWaende() > i; i++) {
						if (game.getWaende(i).getHoehe() > hoehe - 6 && game.getWaende(i).getHoehe() < hoehe + 6) {
							hoehe = game.getWaende(i).getHoehe();
						}
					}
					if (hoehe == 20) {
						hoehe = 15;
					}
					System.out.println("breite: " + breite + "  hoehe: " + hoehe);
					Wand wand = new Wand(x, y, breite + x, hoehe + y, breite, hoehe, 10, game);
					game.addWaende(wand);
				}

				if (klick == true) {
					klick = false;
				} else {
					klick = true;
				}
			} else {
				//fuer neuen Abot ziel setzen
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.InputKey.getAuswahl() == game.getbot_team_A(i).getNUMMER()) {
						game.getbot_team_A(i).setZielx(arg0.getX() - 7 - 10);
						game.getbot_team_A(i).setZiely(arg0.getY() - 31 - 10);
					}
				}
			}

		}

		if (arg0.getButton() == 2) {
			//koordinaten von maus
			System.out.println("x: " + (arg0.getX() - 7) + " y: " + (arg0.getY() - 31));
		}
		if (arg0.getButton() == 3) {
			//loeschen von Waenden oder bots
			x = arg0.getX() - 7;
			y = arg0.getY() - 31;
			for (int i = 0; game.getAnzWaende() > i; i++) {
				if (game.getWaende(i).getX() < x && game.getWaende(i).getX() + game.getWaende(i).getBreite() > x) {
					if (game.getWaende(i).getY() < y && game.getWaende(i).getY() + game.getWaende(i).getHoehe() > y) {
						game.alleWaende.removeElement(game.getWaende(i));
					}
				}

			}

			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).getX() < x && game.getbot_team_A(i).getX() + 20 > x) {
					if (game.getbot_team_A(i).getY() < y && game.getbot_team_A(i).getY() + 20 > y) {
						game.getbot_team_A(i).setY(game.getbot_team_A(i).getY() - 1);
						game.alleBots_Team_A.removeElement(game.getbot_team_A(i));
					}
				}
			}

			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).getX() < x && game.getbot_team_B(i).getX() + 20 > x) {
					if (game.getbot_team_B(i).getY() < y && game.getbot_team_B(i).getY() + 20 > y) {
						game.getbot_team_B(i).setY(game.getbot_team_B(i).getY() - 1);
						game.alleBots_Team_B.removeElement(game.getbot_team_B(i));
					}
				}
			}

			for (int i = 0; game.getAnzObjektiv() > i; i++) {
				if (game.getObjektiv(i).getX() < x
						&& game.getObjektiv(i).getX() + game.getObjektiv(i).getBreite() > x) {
					System.out.println("ok");
					if (game.getObjektiv(i).getY() < y
							&& game.getObjektiv(i).getY() + game.getObjektiv(i).getHoehe() > y) {
						game.alleObjektives.removeElement(game.getObjektiv(i));
					}
				}

			}

		}
	}
}
