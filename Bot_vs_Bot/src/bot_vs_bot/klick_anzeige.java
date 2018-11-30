package bot_vs_bot;

import java.awt.Color;
import java.awt.Graphics2D;

public class klick_anzeige {
	private int x;
	private int y;
	
	
	public void paint(Graphics2D g) {
		//zeichnet viereck wo man hingeklickt hat
		g.setColor(Color.RED);
		g.fillRect(x, y, 5, 5);
	}


	public klick_anzeige(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
