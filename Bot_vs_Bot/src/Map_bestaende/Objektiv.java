package Map_bestaende;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Rectangle;

import javax.swing.JFrame;

import Main.Game;

public class Objektiv extends JFrame {
	Color farbe;
	private int x;
	private int y;
	private int sizex;
	private int sizey;
	private int wieweit = 0;
	private int ok;
	Game game;

	public Objektiv(Game game, int x, int y, int sizex, int sizey) throws HeadlessException {
		super();
		this.x = x;
		this.y = y;
		this.sizex = sizex;
		this.sizey = sizey;
		this.game = game;
	}

	public void paint(Graphics2D g) {
		if (wieweit < 256 && wieweit > -1) {
			farbe = new Color(0, 0, wieweit, 70);
		} else {
			if (wieweit < 0 && wieweit > -256) {
				farbe = new Color(-wieweit, 0, 0, 70);
			}
		}
		g.setColor(farbe);
		// g.drawImage(Toolkit.getDefaultToolkit().getImage("C:/Users/toffe/Desktop/Testing/Fireball.png"),
		// x, y, this);
		g.fillRect(x, y, sizex, sizey);
	}

	public boolean collision() {
		//ob sich ein bot von Team a hier befindet
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
		//ob sich ein bot von Team b hier befindet
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
	
	public int abgeschlossen() {
		//ob es eingenommen wurde
		if(getWieweit() == 256) {
			return 1;
			//von team A eingenommen
		}
		else if ( getWieweit() == -256) {
			return -1;
			//von team B eingenommen
		}
		else {
			return 0;
			//keins von beidem
		}
	}

	public int getWieweit() {
		return wieweit;
	}

	public void setWieweit(int wieweit) {
		this.wieweit = wieweit;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, sizex, sizey);
	}

	public int getSizex() {
		return sizex;
	}

	public void setSizex(int sizex) {
		this.sizex = sizex;
	}

	public int getSizey() {
		return sizey;
	}

	public void setSizey(int sizey) {
		this.sizey = sizey;
	}

	public int getHoehe() {
		// TODO Auto-generated method stub
		return sizey;
	}

	public int getBreite() {
		// TODO Auto-generated method stub
		return sizex;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	
}
