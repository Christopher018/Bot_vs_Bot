package Bots;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.util.Random;

import Main.Game;

public class Bot_Show2 extends BOT {

	public Bot_Show2(String name, int x, int y, int leben, int schaden, int cooldown, int speed, int movespeed,
			int faehigkeit, Game game) throws HeadlessException {
		super(name, x, y, leben, schaden, cooldown, speed, movespeed, faehigkeit, game);
		
		//bot benennen: 
		setName("Gegner");
	}

	@Override
	public void paint_char(Graphics2D g) {
		Color RED = new Color(173, 20, 20);
		g.setColor(RED);
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
