package bot_vs_bot;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Wand {
	Color SCHWARZ;
	private int x;
	private int y;
	private int y2;
	private int x2;
	private int breite;
	private int hoehe;
	private int Leben = 10;
	private int Lebenamanfang = Leben;
	private int farbe = 0;
	private int lebensanzeige;
	private int ok = -1;
	private int anfangsx;
	private int anfangsy;
	private int welchesTeam = 0;
	public Game game;

	public void paint(Graphics2D g) {
		lebensanzeige = Lebenamanfang - Leben;
		farbe = lebensanzeige * 22;
		if (Leben > 10) {
			SCHWARZ = new Color(0, 0, 0);
		} else {
			SCHWARZ = new Color(farbe, farbe, farbe);
		}
		g.setColor(SCHWARZ);
		g.fillRect(x, y, breite, hoehe);
		// g.drawImage(Toolkit.getDefaultToolkit().getImage("C:/Users/toffe/Desktop/Testing/Fireball.png"),
		// x, y, this);
	}

	public Wand(int x, int y, int x2, int y2, int breite, int hoehe, int Leben, Game game) {
		super();
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		this.breite = breite;
		this.hoehe = hoehe;
		this.game = game;
		this.Leben = Leben;
		anfangsx = x;
		anfangsy = y;
	}

	public Wand(int x, int y, int breite, int hoehe,int welchesTeam, Game game) {
		super();
		this.x = x;
		this.y = y;
		this.breite = breite;
		this.hoehe = hoehe;
		this.game = game;
		this.welchesTeam = welchesTeam;
		Leben = 10;
		anfangsx = x;
		anfangsy = y;
		//System.out.println("x: "+x+" y: "+y+" breite: "+breite+" hoehe: "+hoehe);
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

	public int getY2() {
		return y2;
	}

	public int getX2() {
		return x2;
	}

	public int getBreite() {
		return breite;
	}

	public int getHoehe() {
		return hoehe;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAnfangsx() {
		return anfangsx;
	}

	public int getAnfangsy() {
		return anfangsy;
	}

	public int getWelchesTeam() {
		return welchesTeam;
	}
	
	

}
