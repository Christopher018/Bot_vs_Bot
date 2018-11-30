package bot_vs_bot;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Spielfeld {
	Frame frame;
	public Spielfeld(JFrame frame) {
		this.frame = frame;
	}

	public void paint(Graphics2D g,int x, int y) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, (frame.getHeight()-50 )/2 -1, frame.getWidth(), 1);
	}
	
}
