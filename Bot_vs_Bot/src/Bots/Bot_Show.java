package Bots;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.util.Random;

import Main.Game;

public class Bot_Show extends BOT {

	public Bot_Show(String name, int x, int y, int leben, int schaden, int cooldown, int speed, int movespeed,
			int faehigkeit, Game game) throws HeadlessException {
		super(name, x, y, leben, schaden, cooldown, speed, movespeed, faehigkeit, game);
		
		//bot benennen: 
		setName("Test");
	}

	@Override
	public void paint_char(Graphics2D g) {
		Color BLUE = new Color(66, 134, 244);
		g.setColor(BLUE);
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
		
		
		if(darfhoch()) {
			hoch();
		}else {
			rechts();
		}
		
		
		
		
		
		
		
		
		

	}

}
