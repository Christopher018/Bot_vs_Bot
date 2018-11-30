package Projektile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;

import Main.Game;

public class Projektil extends JFrame {
	Color DARK_RED = new Color(140, 0, 0);
	private int x;
	private int y;
	private int speed;
	private int schaden;
	private int DIAMETER = 10;
	private int ok = -1;
	private int BotNR;
	private int WandNR;
	private int SchildNR;
	private int richtung;
	private int besitzer;
	private boolean schraeg = true;
	public Game game;

	public void paint(Graphics2D g) {
		g.setColor(DARK_RED);
		g.fillOval(x, y, 10, 10);
		// g.drawImage(Toolkit.getDefaultToolkit().getImage("C:/Users/toffe/Desktop/Testing/Fireball.png"),
		// x, y, this);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}

	public void bewegen() {
		//legt fest wie es sich bewegt in dem es die richtung abfragt
		if (richtung == 1) {
			if (schraeg) {
				x = x - speed;
			} else {
				y = y - speed;
			}

			if (schraeg) {
				schraeg = false;
			} else {
				schraeg = true;
			}
		}
		if (richtung == 2) {
			y = y - speed;
		}
		if (richtung == 3) {
			if (schraeg) {
				x = x + speed;
			} else {
				y = y - speed;
			}

			if (schraeg) {
				schraeg = false;
			} else {
				schraeg = true;
			}
		}
		if (richtung == 4) {
			x = x - speed;
		}
		if (richtung == 5) {
			x = x + speed;
		}
		if (richtung == 6) {
			if (schraeg) {
				x = x - speed;
			} else {
				y = y + speed;
			}

			if (schraeg) {
				schraeg = false;
			} else {
				schraeg = true;
			}
		}
		if (richtung == 7) {
			y = y + speed;
		}
		if (richtung == 8) {
			if (schraeg) {
				x = x + speed;
			} else {
				y = y + speed;
			}

			if (schraeg) {
				schraeg = false;
			} else {
				schraeg = true;
			}
		}
	}

	public Projektil(int x, int y, int schaden, int richtung, int speed, int besitzer, Game game) throws HeadlessException {
		super();
		this.x = x;
		this.y = y;
		this.schaden = schaden;
		this.richtung = richtung;
		this.speed = speed;
		this.besitzer = besitzer;
		this.game = game;
	}

	public boolean collision() {
		ok = -1;
		for (int i = 0; i < game.getAnzBot(); i++) {
			if (game.getbot_team_A(i).getBounds().intersects(getBounds()) == true) {
				ok = 1;
				setBotNR(i);
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
				setBotNR(i);
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
	
	
	public boolean collisionW() {
		ok = -1;
		for (int i = 0; i < game.getAnzWaende(); i++) {
			if (game.getWaende(i).getBounds().intersects(getBounds()) == true) {
				ok = 1;
				setWandNR(i);
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
	
	public boolean collisionS() {
		ok = -1;
		for (int i = 0; i < game.getAnzSchilde(); i++) {
			if (game.getSchilde(i).getBounds().intersects(getBounds()) == true) {
				ok = 1;
				setSchildNR(i);
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

	public int getBotNR() {
		return BotNR;
	}
	
	public int getWandNR() {
		return WandNR;
	}
	
	public int getSchildNR() {
		return SchildNR;
	}

	public void setBotNR(int botNR) {
		BotNR = botNR;
	}
	
	public void setWandNR(int wandNR) {
		WandNR = wandNR;
	}
	
	public void setSchildNR(int SchildNR) {
		this.SchildNR = SchildNR;
	}

	public int getSchaden() {
		return schaden;
	}

	public int getBesitzer() {
		return besitzer;
	}

	public void setBesitzer(int besitzer) {
		this.besitzer = besitzer;
	}

	public int getRichtung() {
		// TODO Auto-generated method stub
		return richtung;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
