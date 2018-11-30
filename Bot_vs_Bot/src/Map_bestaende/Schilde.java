package Map_bestaende;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Main.Game;

public class Schilde {
	Color SCHWARZ;
	private int x;
	private int y;
	private int breite;
	private int hoehe;
	private int typ;
	private int Leben = 10;
	private int ok = -1;
	public Game game;
	Color BLAU = new Color(0,0,200,80);
	Color HELL_BLAU = new Color(0,80,225,50);
	
	public void paint(Graphics2D g) {
		// schild oben
		if (typ == 1) {
			g.setColor(Color.BLUE);
			g.fillOval(x, y - 2, breite, hoehe);
			g.setColor(Color.WHITE);
			g.fillOval(x, y, breite + 1, hoehe + 2);
		}
		// schilde links
		if (typ == 2) {
			g.setColor(Color.BLUE);
			g.fillOval(x - 2, y, breite, hoehe);
			g.setColor(Color.WHITE);
			g.fillOval(x, y, breite - 2, hoehe - 1);
		}
		// schild rechts
		if (typ == 3) {
			g.setColor(Color.BLUE);
			g.fillOval(x + 3, y, breite, hoehe);
			g.setColor(Color.WHITE);
			g.fillOval(x, y, breite + 2, hoehe + 1);
		}
		// schild unten
		if (typ == 4) {
			g.setColor(Color.BLUE);
			g.fillOval(x, y + 2, breite, hoehe);
			g.setColor(Color.WHITE);
			g.fillOval(x, y, breite - 1, hoehe);
		}
		// schild überall
		if (typ == 5) {
			g.setColor(BLAU);
			g.fillOval(x, y, breite, breite);
			g.setColor(HELL_BLAU);
			g.fillOval(x+2, y+2, breite - 4, breite - 4);
		}
	}

	public Schilde(int x, int y, int breite, int hoehe, int typ, Game game) {
		super();
		this.x = x;
		this.y = y;
		this.breite = breite;
		this.hoehe = hoehe;
		this.typ = typ;
		this.game = game;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, breite, hoehe);
	}

	public boolean collision() {
		ok = -1;
		for (int i = 0; i < game.getAnzBot(); i++) {
			if (game.getbot_team_A(i).getBounds().intersects(getBounds()) == true) {
				ok = 1;
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
			if (game.getbot_team_B(i).getBounds().intersects(getBounds()) == true) {
				ok = 1;
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

	public int getLeben() {
		return Leben;
	}

	public void setLeben(int leben) {
		Leben = leben;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
