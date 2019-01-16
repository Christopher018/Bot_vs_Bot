package Bots;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Rectangle;

import javax.swing.JFrame;

import Main.Game;
import Map_bestaende.Schilde;
import Projektile.Projektil;

public abstract class BOT extends JFrame {
	//jeder Bot erbt von dieser Klasse. es werden so grundregeln erschaffen
	protected int Leben;
	protected int Lebenamanfang;
	protected int x = 0;
	protected int y = 0;
	protected int getroffen = 0;
	protected int schaden;
	protected int cooldowncount;
	protected int cooldowncount2 = 0;
	protected boolean cooldownaktiv = false;
	protected int cooldownanzeige;
	protected int cooldown;
	protected int speed;
	protected int movespeed;
	protected boolean hoch = false;
	protected boolean runter = false;
	protected boolean rechts = false;
	protected boolean links = false;
	protected int DIAMETER = 20;
	private boolean darf_gehen = true;
	private String anfangsstats;
	private int repariercount = 0;
	private int heilcount = 0;
	private Integer welche_wand;
	private int schildcounter = 0;
	private int ok = -1;
	private int NUMMER = 0; // ID
	private int welcher_bot;
	private int faehigkeit; // 1 = reparieren 2 = heilen 3 = schild
	public Game game;
	private boolean Averkehrt = false;
	private boolean Bverkehrt = false;
	private String name;
	
	//variablen für eigene bewegung
	private int zielx = 0;
	private int ziely = 0;

	public BOT(String name,int x, int y, int leben, int schaden, int cooldown, int speed, int movespeed, int faehigkeit, Game game)
			throws HeadlessException {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
		this.game = game;
		this.Leben = leben;
		this.schaden = schaden;
		this.cooldown = 17 - cooldown;
		this.speed = speed;
		this.movespeed = movespeed;
		this.faehigkeit = faehigkeit;
		cooldowncount = cooldown;
		Lebenamanfang = Leben;

		anfangsstats = "Leben: " + leben + "  Schaden: " + schaden + "  Cooldown: " + cooldown + "  Speed: " + speed
				+ "  Movespeed: " + movespeed + "";

		for (int i = 0; i < game.getAnzBot(); i++) {
			NUMMER = NUMMER + 1;
		}
		for (int i = 0; i < game.getAnzBotB(); i++) {
			NUMMER = NUMMER + 1;
		}
		NUMMER++;
	}

	public abstract void paint_char(Graphics2D g);

	public abstract void bewegen();

	public void paint_schaden(Graphics2D g) {
		//schaden wird angezeigt
		g.setColor(Color.BLACK);
		g.fillRect(x, y + 21, 20, 3);

		g.setColor(Color.RED);
		g.fillRect(x + 2, y + 22, 16, 1);

		getroffen = Lebenamanfang - Leben;

		g.setColor(Color.GREEN);
		g.fillRect(x + 2, y + 22, 16 - getroffen * 16 / Lebenamanfang, 1);
	}

	public void paint_cooldown(Graphics2D g) {
		//cooldown wird angezeigt
		g.setColor(Color.BLACK);
		g.fillRect(x, y - 4, 20, 3);

		g.setColor(Color.WHITE);
		g.fillRect(x + 2, y - 3, 16, 1);
		
		try {
		cooldownanzeige = 16 / cooldown;
		}catch(java.lang.ArithmeticException ex) {
			game.InputKey.setStart(false);
			System.out.println("Error: cooldown muss zwischen 1 und 16 sein");
		}

		g.setColor(Color.BLUE);
		g.fillRect(x + 2, y - 3, cooldownanzeige * cooldowncount, 1);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}

	public int getLeben() {
		return Leben;
	}

	public int getLebenamanfang() {
		return Lebenamanfang;
	}

	public void setLeben(int leben) {
		Leben = leben;
	}

	public void cooldown() {
		cooldownaktiv = true;
		cooldowncount = 0;
		cooldowncount2 = 0;
	}

	public void cooldownaktiv() {
		cooldowncount2++;
		if (cooldowncount2 == 20) {
			cooldowncount2 = 0;
			cooldowncount++;
			if (cooldowncount == cooldown) {
				cooldowncount = 0;
				cooldownaktiv = false;
			}
		}
	}

	public void hoch() {
		//wenn man sich nach oben bewegen will
		//wird geprueft was oben fuer den bot ist(aus welcher sicht er geschrieben wurde)
		//und dann die steuerung ggf. invertiert
		if (game.getteammitNR(NUMMER, game) == 1 && Averkehrt == false
				|| game.getteammitNR(NUMMER, game) == 2 && Bverkehrt == false) {
			if (runter == false && rechts == false && links == false) {
				darf_gehen = true;
				y = y - movespeed;
				//wird auf kollision geprueft
				for (int i = 0; i < game.getAnzWaende(); i++) {
					if (game.getWaende(i).collision()) {
						y = y + movespeed;
						darf_gehen = false;
					}
					if (game.getWaende(i).collisionB()) {
						y = y + movespeed;
						darf_gehen = false;
					}
				}
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.getbot_team_A(i).collision()) {
						y = y + movespeed;
						darf_gehen = false;
					}
				}

				for (int i = 0; i < game.getAnzBotB(); i++) {
					if (game.getbot_team_B(i).collisionB()) {
						y = y + movespeed;
						darf_gehen = false;
					}
				}

				try {
					for (int i = 0; i < game.getAnzBot(); i++) {
						if (game.getbot_team_B(i).collision()) {
							y = y + movespeed;
							darf_gehen = false;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {

				} catch (NullPointerException ex) {

				}
				try {
					for (int i = 0; i < game.getAnzBotB(); i++) {
						if (game.getbot_team_A(i).collisionB()) {
							y = y + movespeed;
							darf_gehen = false;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {

				} catch (NullPointerException ex) {

				}
				

				if (darf_gehen == true) {
					hoch = false;
					runter = false;
					rechts = false;
					links = true;
				}
			}
		} else {
			//falls invertiert
			if (hoch == false && rechts == false && links == false) {
				darf_gehen = true;
				y = y + movespeed;
				//wird auf kollision geprueft
				for (int i = 0; i < game.getAnzWaende(); i++) {
					if (game.getWaende(i).collision()) {
						y = y - movespeed;
						darf_gehen = false;
					}
					if (game.getWaende(i).collisionB()) {
						y = y - movespeed;
						darf_gehen = false;
					}
				}
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.getbot_team_A(i).collision()) {
						y = y - movespeed;
						darf_gehen = false;
					}
				}

				for (int i = 0; i < game.getAnzBotB(); i++) {
					if (game.getbot_team_B(i).collisionB()) {
						y = y - movespeed;
						darf_gehen = false;
					}
				}

				try {
					for (int i = 0; i < game.getAnzBot(); i++) {
						if (game.getbot_team_B(i).collision()) {
							y = y - movespeed;
							darf_gehen = false;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {

				} catch (NullPointerException ex) {

				}
				try {
					for (int i = 0; i < game.getAnzBotB(); i++) {
						if (game.getbot_team_A(i).collisionB()) {
							y = y - movespeed;
							darf_gehen = false;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {

				} catch (NullPointerException ex) {

				}
				if (darf_gehen == true) {
					hoch = false;
					runter = false;
					rechts = false;
					links = true;
				}
			}
		}
	}

	public boolean darfhoch() {
		// diese methode prueft ob es eine wand oder einen bot direkt vor einem hat
		if (game.getteammitNR(NUMMER, game) == 1 && Averkehrt == false
				|| game.getteammitNR(NUMMER, game) == 2 && Bverkehrt == false) {

			y = y - movespeed;
			//wird auf kollision geprueft
			for (int i = 0; i < game.getAnzWaende(); i++) {
				if (game.getWaende(i).collision()) {
					y = y + movespeed;
					welche_wand = i;
					return false;
				}
				if (game.getWaende(i).collisionB()) {
					y = y + movespeed;
					welche_wand = i;
					return false;
				}
			}
			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).collision()) {
					y = y + movespeed;

					return false;
				}
			}

			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).collisionB()) {
					y = y + movespeed;

					return false;
				}
			}

			try {
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.getbot_team_B(i).collision()) {
						y = y + movespeed;

						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			} catch (NullPointerException ex) {

			}
			try {
				for (int i = 0; i < game.getAnzBotB(); i++) {
					if (game.getbot_team_A(i).collisionB()) {
						y = y + movespeed;

						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			} catch (NullPointerException ex) {

			}
			y = y + movespeed;
			return true;
		} else {
			//falls steuerung invertiert
			y = y + movespeed;
			//wird auf kollision geprueft
			for (int i = 0; i < game.getAnzWaende(); i++) {
				if (game.getWaende(i).collision()) {
					y = y - movespeed;
					welche_wand = i;
					return false;
				}
				if (game.getWaende(i).collisionB()) {
					y = y - movespeed;
					welche_wand = i;
					return false;
				}
			}
			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).collision()) {
					y = y - movespeed;

					return false;
				}
			}

			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).collisionB()) {
					y = y - movespeed;

					return false;
				}
			}

			try {
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.getbot_team_B(i).collision()) {
						y = y - movespeed;

						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			} catch (NullPointerException ex) {

			}
			try {
				for (int i = 0; i < game.getAnzBotB(); i++) {
					if (game.getbot_team_A(i).collisionB()) {
						y = y - movespeed;

						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			} catch (NullPointerException ex) {

			}
			y = y - movespeed;
			return true;
		}
	}

	public void runter() {
		//wenn man sich nach unten bewegen will
		//wird geprueft was oben fuer den bot ist(aus welcher sicht er geschrieben wurde)
		//und dann die steuerung ggf. invertiert
		if (game.getteammitNR(NUMMER, game) == 1 && Averkehrt == false
				|| game.getteammitNR(NUMMER, game) == 2 && Bverkehrt == false) {
			if (hoch == false && rechts == false && links == false) {
				darf_gehen = true;
				y = y + movespeed;
				//wird auf kollision geprueft
				for (int i = 0; i < game.getAnzWaende(); i++) {
					if (game.getWaende(i).collision()) {
						y = y - movespeed;
						darf_gehen = false;
					}
					if (game.getWaende(i).collisionB()) {
						y = y - movespeed;
						darf_gehen = false;
					}
				}
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.getbot_team_A(i).collision()) {
						y = y - movespeed;
						darf_gehen = false;
					}
				}

				for (int i = 0; i < game.getAnzBotB(); i++) {
					if (game.getbot_team_B(i).collisionB()) {
						y = y - movespeed;
						darf_gehen = false;
					}
				}

				try {
					for (int i = 0; i < game.getAnzBot(); i++) {
						if (game.getbot_team_B(i).collision()) {
							y = y - movespeed;
							darf_gehen = false;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {

				} catch (NullPointerException ex) {

				}
				try {
					for (int i = 0; i < game.getAnzBotB(); i++) {
						if (game.getbot_team_A(i).collisionB()) {
							y = y - movespeed;
							darf_gehen = false;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {

				} catch (NullPointerException ex) {

				}
				if (darf_gehen == true) {
					hoch = false;
					runter = false;
					rechts = false;
					links = true;
				}
			}
		} else {
			//falls steuerung invertiert
			if (runter == false && rechts == false && links == false) {
				darf_gehen = true;
				y = y - movespeed;
				//wird auf kollision geprueft
				for (int i = 0; i < game.getAnzWaende(); i++) {
					if (game.getWaende(i).collision()) {
						y = y + movespeed;
						darf_gehen = false;
					}
					if (game.getWaende(i).collisionB()) {
						y = y + movespeed;
						darf_gehen = false;
					}
				}
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.getbot_team_A(i).collision()) {
						y = y + movespeed;
						darf_gehen = false;
					}
				}

				for (int i = 0; i < game.getAnzBotB(); i++) {
					if (game.getbot_team_B(i).collisionB()) {
						y = y + movespeed;
						darf_gehen = false;
					}
				}

				try {
					for (int i = 0; i < game.getAnzBot(); i++) {
						if (game.getbot_team_B(i).collision()) {
							y = y + movespeed;
							darf_gehen = false;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {

				} catch (NullPointerException ex) {

				}
				try {
					for (int i = 0; i < game.getAnzBotB(); i++) {
						if (game.getbot_team_A(i).collisionB()) {
							y = y + movespeed;
							darf_gehen = false;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {

				} catch (NullPointerException ex) {

				}

				if (darf_gehen == true) {
					hoch = false;
					runter = false;
					rechts = false;
					links = true;
				}
			}
		}
	}

	public boolean darfrunter() {
		// diese methode prueft ob es eine wand oder einen bot direkt unter einem hat
		if (game.getteammitNR(NUMMER, game) == 1 && Averkehrt == false
				|| game.getteammitNR(NUMMER, game) == 2 && Bverkehrt == false) {
			y = y + movespeed;
			//wird auf kollision geprueft
			for (int i = 0; i < game.getAnzWaende(); i++) {
				if (game.getWaende(i).collision()) {
					y = y - movespeed;
					welche_wand = i;
					return false;
				}
				if (game.getWaende(i).collisionB()) {
					y = y - movespeed;
					welche_wand = i;
					return false;
				}
			}
			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).collision()) {
					y = y - movespeed;

					return false;
				}
			}

			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).collisionB()) {
					y = y - movespeed;

					return false;
				}
			}

			try {
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.getbot_team_B(i).collision()) {
						y = y - movespeed;

						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			} catch (NullPointerException ex) {

			}
			try {
				for (int i = 0; i < game.getAnzBotB(); i++) {
					if (game.getbot_team_A(i).collisionB()) {
						y = y - movespeed;

						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			} catch (NullPointerException ex) {

			}
			y = y - movespeed;
			return true;
		} else {
			//falls steuerung invertiert
			y = y - movespeed;
			//wird auf kollision geprueft
			for (int i = 0; i < game.getAnzWaende(); i++) {
				if (game.getWaende(i).collision()) {
					y = y + movespeed;
					welche_wand = i;
					return false;
				}
				if (game.getWaende(i).collisionB()) {
					y = y + movespeed;
					welche_wand = i;
					return false;
				}
			}
			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).collision()) {
					y = y + movespeed;

					return false;
				}
			}

			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).collisionB()) {
					y = y + movespeed;

					return false;
				}
			}

			try {
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.getbot_team_B(i).collision()) {
						y = y + movespeed;

						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			} catch (NullPointerException ex) {

			}
			try {
				for (int i = 0; i < game.getAnzBotB(); i++) {
					if (game.getbot_team_A(i).collisionB()) {
						y = y + movespeed;

						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			} catch (NullPointerException ex) {

			}
			y = y + movespeed;
			return true;
		}
	}

	public void rechts() {
		//wenn man sich nach rechts bewegen will
		//wird geprueft was oben fuer den bot ist(aus welcher sicht er geschrieben wurde)
		//und dann die steuerung ggf. invertiert
		if (game.getteammitNR(NUMMER, game) == 1 && Averkehrt == false
				|| game.getteammitNR(NUMMER, game) == 2 && Bverkehrt == false) {
			if (hoch == false && runter == false && links == false) {
				darf_gehen = true;
				x = x + movespeed;
				//wird auf kollision geprueft
				for (int i = 0; i < game.getAnzWaende(); i++) {
					if (game.getWaende(i).collision()) {
						x = x - movespeed;
						darf_gehen = false;
					}
					if (game.getWaende(i).collisionB()) {
						x = x - movespeed;
						darf_gehen = false;
					}
				}
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.getbot_team_A(i).collision()) {
						x = x - movespeed;
						darf_gehen = false;
					}
				}

				for (int i = 0; i < game.getAnzBotB(); i++) {
					if (game.getbot_team_B(i).collisionB()) {
						x = x - movespeed;
						darf_gehen = false;
					}
				}

				try {
					for (int i = 0; i < game.getAnzBot(); i++) {
						if (game.getbot_team_B(i).collision()) {
							x = x - movespeed;
							darf_gehen = false;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {

				} catch (NullPointerException ex) {

				}
				try {
					for (int i = 0; i < game.getAnzBotB(); i++) {
						if (game.getbot_team_A(i).collisionB()) {
							x = x - movespeed;
							darf_gehen = false;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {

				} catch (NullPointerException ex) {

				}
				if (darf_gehen == true) {
					hoch = false;
					runter = false;
					rechts = false;
					links = true;
				}
			}
		} else {
			//falls steuerung invertiert
			if (hoch == false && runter == false && rechts == false) {
				darf_gehen = true;
				x = x - movespeed;
				//wird auf kollision geprueft
				for (int i = 0; i < game.getAnzWaende(); i++) {
					if (game.getWaende(i).collision()) {
						x = x + movespeed;
						darf_gehen = false;
					}
					if (game.getWaende(i).collisionB()) {
						x = x + movespeed;
						darf_gehen = false;
					}

				}
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.getbot_team_A(i).collision()) {
						x = x + movespeed;
						darf_gehen = false;
					}
				}

				for (int i = 0; i < game.getAnzBotB(); i++) {
					if (game.getbot_team_B(i).collisionB()) {
						x = x + movespeed;
						darf_gehen = false;
					}
				}

				try {
					for (int i = 0; i < game.getAnzBot(); i++) {
						if (game.getbot_team_B(i).collision()) {
							x = x + movespeed;
							darf_gehen = false;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {

				} catch (NullPointerException ex) {

				}
				try {
					for (int i = 0; i < game.getAnzBotB(); i++) {
						if (game.getbot_team_A(i).collisionB()) {
							x = x + movespeed;
							darf_gehen = false;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {

				} catch (NullPointerException ex) {

				}

				if (darf_gehen == true) {
					hoch = false;
					runter = false;
					rechts = false;
					links = true;
				}
			}
		}
	}

	public boolean darfrechts() {
		// diese methode prueft ob es eine wand oder einen bot direkt rechts von einem hat
		if (game.getteammitNR(NUMMER, game) == 1 && Averkehrt == false
				|| game.getteammitNR(NUMMER, game) == 2 && Bverkehrt == false) {
			x = x + movespeed;
			//wird auf kollision geprueft
			for (int i = 0; i < game.getAnzWaende(); i++) {
				if (game.getWaende(i).collision()) {
					x = x - movespeed;
					welche_wand = i;
					return false;
				}
				if (game.getWaende(i).collisionB()) {
					x = x - movespeed;
					welche_wand = i;
					return false;
				}
			}
			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).collision()) {
					x = x - movespeed;

					return false;
				}
			}

			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).collisionB()) {
					x = x - movespeed;

					return false;
				}
			}

			try {
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.getbot_team_B(i).collision()) {
						x = x - movespeed;

						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			} catch (NullPointerException ex) {

			}
			try {
				for (int i = 0; i < game.getAnzBotB(); i++) {
					if (game.getbot_team_A(i).collisionB()) {
						x = x - movespeed;

						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			} catch (NullPointerException ex) {

			}

			x = x - movespeed;
			return true;
		} else {
			//falls steuerung invertiert
			x = x - movespeed;
			//wird auf kollision geprueft
			for (int i = 0; i < game.getAnzWaende(); i++) {
				if (game.getWaende(i).collision()) {
					x = x + movespeed;
					welche_wand = i;
					return false;
				}
				if (game.getWaende(i).collisionB()) {
					x = x + movespeed;
					welche_wand = i;
					return false;
				}
			}
			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).collision()) {
					x = x + movespeed;

					return false;
				}
			}

			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).collisionB()) {
					x = x + movespeed;

					return false;
				}
			}

			try {
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.getbot_team_B(i).collision()) {
						x = x + movespeed;

						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			} catch (NullPointerException ex) {

			}
			try {
				for (int i = 0; i < game.getAnzBotB(); i++) {
					if (game.getbot_team_A(i).collisionB()) {
						x = x + movespeed;

						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			} catch (NullPointerException ex) {

			}

			x = x + movespeed;
			return true;
		}
	}

	public void links() {
		//wenn man sich nach links bewegen will
		//wird geprueft was oben fuer den bot ist(aus welcher sicht er geschrieben wurde)
		//und dann die steuerung ggf. invertiert
		if (game.getteammitNR(NUMMER, game) == 1 && Averkehrt == false
				|| game.getteammitNR(NUMMER, game) == 2 && Bverkehrt == false) {
			if (hoch == false && runter == false && rechts == false) {
				darf_gehen = true;
				x = x - movespeed;
				//wird auf kollision geprueft
				for (int i = 0; i < game.getAnzWaende(); i++) {
					if (game.getWaende(i).collision()) {
						x = x + movespeed;
						darf_gehen = false;
					}
					if (game.getWaende(i).collisionB()) {
						x = x + movespeed;
						darf_gehen = false;
					}

				}
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.getbot_team_A(i).collision()) {
						x = x + movespeed;
						darf_gehen = false;
					}
				}

				for (int i = 0; i < game.getAnzBotB(); i++) {
					if (game.getbot_team_B(i).collisionB()) {
						x = x + movespeed;
						darf_gehen = false;
					}
				}

				try {
					for (int i = 0; i < game.getAnzBot(); i++) {
						if (game.getbot_team_B(i).collision()) {
							x = x + movespeed;
							darf_gehen = false;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {

				} catch (NullPointerException ex) {

				}
				try {
					for (int i = 0; i < game.getAnzBotB(); i++) {
						if (game.getbot_team_A(i).collisionB()) {
							x = x + movespeed;
							darf_gehen = false;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {

				} catch (NullPointerException ex) {

				}

				if (darf_gehen == true) {
					hoch = false;
					runter = false;
					rechts = false;
					links = true;
				}
			}
		} else {
			//falls steuerung invertiert
			if (hoch == false && runter == false && links == false) {
				darf_gehen = true;
				x = x + movespeed;
				//wird auf kollision geprueft
				for (int i = 0; i < game.getAnzWaende(); i++) {
					if (game.getWaende(i).collision()) {
						x = x - movespeed;
						darf_gehen = false;
					}
					if (game.getWaende(i).collisionB()) {
						x = x - movespeed;
						darf_gehen = false;
					}
				}
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.getbot_team_A(i).collision()) {
						x = x - movespeed;
						darf_gehen = false;
					}
				}

				for (int i = 0; i < game.getAnzBotB(); i++) {
					if (game.getbot_team_B(i).collisionB()) {
						x = x - movespeed;
						darf_gehen = false;
					}
				}

				try {
					for (int i = 0; i < game.getAnzBot(); i++) {
						if (game.getbot_team_B(i).collision()) {
							x = x - movespeed;
							darf_gehen = false;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {

				} catch (NullPointerException ex) {

				}
				try {
					for (int i = 0; i < game.getAnzBotB(); i++) {
						if (game.getbot_team_A(i).collisionB()) {
							x = x - movespeed;
							darf_gehen = false;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {

				} catch (NullPointerException ex) {

				}
				if (darf_gehen == true) {
					hoch = false;
					runter = false;
					rechts = false;
					links = true;
				}
			}
		}
	}

	public boolean darflinks() {
		// diese methode prueft ob es eine wand oder einen bot direkt links von einem hat
		if (game.getteammitNR(NUMMER, game) == 1 && Averkehrt == false
				|| game.getteammitNR(NUMMER, game) == 2 && Bverkehrt == false) {
			x = x - movespeed;
			//wird auf kollision geprueft
			for (int i = 0; i < game.getAnzWaende(); i++) {
				if (game.getWaende(i).collision()) {
					x = x + movespeed;
					welche_wand = i;
					return false;
				}
				if (game.getWaende(i).collisionB()) {
					x = x + movespeed;
					welche_wand = i;
					return false;
				}
			}
			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).collision()) {
					x = x + movespeed;

					return false;
				}
			}

			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).collisionB()) {
					x = x + movespeed;

					return false;
				}
			}

			try {
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.getbot_team_B(i).collision()) {
						x = x + movespeed;

						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			} catch (NullPointerException ex) {

			}
			try {
				for (int i = 0; i < game.getAnzBotB(); i++) {
					if (game.getbot_team_A(i).collisionB()) {
						x = x + movespeed;

						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			} catch (NullPointerException ex) {

			}

			x = x + movespeed;
			return true;
		} else {
			//falls steuerung invertiert
			x = x + movespeed;
			//wird auf kollision geprueft
			for (int i = 0; i < game.getAnzWaende(); i++) {
				if (game.getWaende(i).collision()) {
					x = x - movespeed;
					welche_wand = i;
					return false;
				}
				if (game.getWaende(i).collisionB()) {
					x = x - movespeed;
					welche_wand = i;
					return false;
				}
			}
			for (int i = 0; i < game.getAnzBot(); i++) {
				if (game.getbot_team_A(i).collision()) {
					x = x - movespeed;

					return false;
				}
			}

			for (int i = 0; i < game.getAnzBotB(); i++) {
				if (game.getbot_team_B(i).collisionB()) {
					x = x - movespeed;

					return false;
				}
			}

			try {
				for (int i = 0; i < game.getAnzBot(); i++) {
					if (game.getbot_team_B(i).collision()) {
						x = x - movespeed;

						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			} catch (NullPointerException ex) {

			}
			try {
				for (int i = 0; i < game.getAnzBotB(); i++) {
					if (game.getbot_team_A(i).collisionB()) {
						x = x - movespeed;

						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			} catch (NullPointerException ex) {

			}

			x = x - movespeed;
			return true;
		}
	}

	public void still() {
		hoch = false;
		runter = false;
		rechts = false;
		links = false;
	}

	public void reparieren() {
		//wenn der bot diese Faehigkeit ausgeruestet hat kann er durch diese methode angeschossene
		//waende reparieren in dem er sich neben dran stellt
		if (faehigkeit == 1) {
			//pruefen ob man neben einer Wand steht
			if (!darflinks()) {
				repariercount = repariercount + 1;
			} else if (!darfrechts()) {
				repariercount = repariercount + 1;
			} else if (!darfhoch()) {
				repariercount = repariercount + 1;
			} else if (!darfrunter()) {
				repariercount = repariercount + 1;
			} else {
				repariercount = 0;
			}
			if (repariercount == 60) {
				//nach 60 ticks wird die Wand leicht repariert
				repariercount = 0;
				if (welche_wand != null) {
					if (game.getWaende(welche_wand).getLeben() < 10) {
						game.getWaende(welche_wand).setLeben(game.getWaende(welche_wand).getLeben() + 1);
					}
				}
			}
		}
	}

	public void heilen() {
		//wenn der bot diese Faehigkeit ausgeruestet hat kann er durch diese methode
		//bots heilen in dem er sich neben dran stellt
		if (faehigkeit == 2) {
			//pruefen ob man neben einen bot steht
			if (!darflinks()) {
				heilcount = heilcount + 1;
			} else if (!darfrechts()) {
				heilcount = heilcount + 1;
			} else if (!darfhoch()) {
				heilcount = heilcount + 1;
			} else if (!darfrunter()) {
				heilcount = heilcount + 1;
			} else {
				heilcount = 0;
			}
			if (heilcount == 60) {
				//nach 60 ticks wird der bot leicht geheilt
				heilcount = 0;
				//pruefen welcher bot
				int botnr = game.getbotmitNR(NUMMER, game);
				int botteam = game.getteammitNR(NUMMER, game);
				
				//bot mit der nummer aus dem Team wird geheilt
				if (botteam == 1) {
					if (game.getbot_team_A(botnr).getLeben() < game.getbot_team_A(botnr).getLebenamanfang()) {
						game.getbot_team_A(botnr).setLeben(game.getbot_team_A(botnr).getLeben() + 1);
					}
				}
				if (botteam == 2) {
					if (game.getbot_team_B(botnr).getLeben() < game.getbot_team_B(botnr).getLebenamanfang()) {
						game.getbot_team_B(botnr).setLeben(game.getbot_team_B(botnr).getLeben() + 1);
					}
				}
			}
		}
	}

	public void schild(int richtung) {
		//wenn der bot diese Faehigkeit ausgeruestet hat kann er durch diese methode
		//schilder erstellen. diese werden vor/ hinter/ neben oder um den bot erstellt.
		//hierbei kommt es darauf an welche richtung gewaehlt wurde
		if (faehigkeit == 3) {
			if (schildcounter < 2) {
				if (richtung == 1) {
					Schilde schild = new Schilde(x - 5, y - 10, 30, 5, richtung, game);
					game.addSchilde(schild);
				}
				if (richtung == 2) {
					Schilde schild = new Schilde(x - 17, y - 6, 5, 30, richtung, game);
					game.addSchilde(schild);
				}
				if (richtung == 3) {
					Schilde schild = new Schilde(x + 25, y - 6, 5, 30, richtung, game);
					game.addSchilde(schild);
				}
				if (richtung == 4) {
					Schilde schild = new Schilde(x - 6, y + 30, 30, 5, richtung, game);
					game.addSchilde(schild);
				}
				if (richtung == 5) {
					Schilde schild = new Schilde(x - 30, y - 30, 80, 80, richtung, game);
					game.addSchilde(schild);
				}
				schildcounter++;
			}
		}
	}

	public boolean collision() {
		ok = -1;
		for (int i = 0; i < game.getAnzBot(); i++) {
			if (game.getbot_team_A(i).getBounds().intersects(getBounds()) == true
					&& game.getbot_team_A(i).getNUMMER() != NUMMER) {
				ok = 1;
				welcher_bot = game.getbot_team_A(i).getNUMMER();
			}
			if (ok == -1) {
				ok = 0;
			}
		}

		if (ok == 1) {
			return true;
		} else {
			return false;
		}

	}
	

	public boolean collisionB() {
		ok = -1;
		for (int i = 0; i < game.getAnzBotB(); i++) {
			if (game.getbot_team_B(i).getBounds().intersects(getBounds()) == true
					&& game.getbot_team_B(i).getNUMMER() != NUMMER) {
				ok = 1;
				welcher_bot = game.getbot_team_B(i).getNUMMER();
			}
			if (ok == -1) {
				ok = 0;
			}
		}

		if (ok == 1) {
			return true;
		} else {
			return false;
		}

	}

	public void schuss(int richtung, boolean geprüft) {
		//hier werden die schuesse abgegeben von dem bot
		//man kann durch "geprüft" einstellen ob invertierte 
		//steuerung eine auswirkung haben soll oder nicht
		if (!isCooldownaktiv()) {
			Projektil projektil;
			if (geprüft == false) {
				if (game.getteammitNR(NUMMER, game) == 1 && Averkehrt == true
						|| game.getteammitNR(NUMMER, game) == 2 && Bverkehrt == true) {

					if (richtung == 1) {
						richtung = 8;
					} else if (richtung == 2) {
						richtung = 7;
					} else if (richtung == 3) {
						richtung = 6;
					} else if (richtung == 4) {
						richtung = 5;
					} else if (richtung == 5) {
						richtung = 4;
					} else if (richtung == 6) {
						richtung = 3;
					} else if (richtung == 7) {
						richtung = 2;
					} else if (richtung == 8) {
						richtung = 1;
					}
				}
			}
			if (richtung == 1) {
				projektil = new Projektil(x - 12, y - 12, schaden, richtung, speed, NUMMER, game);
			} else {
				if (richtung == 2) {
					projektil = new Projektil(x + 7, y - 12, schaden, richtung, speed, NUMMER, game);
				} else {
					if (richtung == 3) {
						projektil = new Projektil(x + 22, y - 12, schaden, richtung, speed, NUMMER, game);
					} else {
						if (richtung == 4) {
							projektil = new Projektil(x - 12, y + 8, schaden, richtung, speed, NUMMER, game);
						} else {
							if (richtung == 5) {
								projektil = new Projektil(x + 22, y + 8, schaden, richtung, speed, NUMMER, game);
							} else {
								if (richtung == 6) {
									projektil = new Projektil(x - 12, y + 22, schaden, richtung, speed, NUMMER, game);
								} else {
									if (richtung == 7) {
										projektil = new Projektil(x + 7, y + 22, schaden, richtung, speed, NUMMER,
												game);
									} else {
										projektil = new Projektil(x + 22, y + 22, schaden, richtung, speed, NUMMER,
												game);
									}
								}
							}
						}
					}
				}
			}

			game.addBProjektil(projektil);
			cooldown();
		}
	}

	public int getNUMMER() {
		return NUMMER;
	}

	public int getCooldowncount() {
		return cooldowncount;
	}

	public boolean isCooldownaktiv() {
		return cooldownaktiv;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isDarf_gehen() {
		return darf_gehen;
	}

	public String getAnfangsstats() {
		return anfangsstats;
	}

	public void setAverkehrt(boolean averkehrt) {
		Averkehrt = averkehrt;
	}

	public void setBverkehrt(boolean bverkehrt) {
		Bverkehrt = bverkehrt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSchaden() {
		return schaden;
	}

	public void setSchaden(int schaden) {
		this.schaden = schaden;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown =  17 - cooldown;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getFaehigkeit() {
		return faehigkeit;
	}

	public void setFaehigkeit(int faehigkeit) {
		this.faehigkeit = faehigkeit;
	}

	public void setLebenamanfang(int lebenamanfang) {
		Lebenamanfang = lebenamanfang;
	}

	public int getMovespeed() {
		return movespeed;
	}

	public void setMovespeed(int movespeed) {
		this.movespeed = movespeed;
	}

	public boolean isAverkehrt() {
		return Averkehrt;
	}
	
	public int getZielx() {
		return zielx;
	}

	public void setZielx(int zielx) {
		this.zielx = zielx;
	}

	public int getZiely() {
		return ziely;
	}

	public void setZiely(int ziely) {
		this.ziely = ziely;
	}

	public boolean isBverkehrt() {
		return Bverkehrt;
	}
	
	
	
}