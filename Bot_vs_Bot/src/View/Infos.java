package View;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Infos extends JFrame{
	JTextArea info = new JTextArea(5,10);
	public Infos(Frame frame) {
		//zeigt info fenster an
		setTitle("Infos (F1)");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		info.setEditable(false);
		info.setText("-Druecke T um die Bots zu Spawnen\n\n-Druecke ENTER um einen Match zu starten und zu stoppen \n\n-Druecke R um das Spiel zu Resetten \n\n-Druecke S um die Stats von den Bots zu sehen \n\n-links klick um Mauern zu platzieren \n\n-rechts klick um Mauern zu entfernen\n\n-Mausrad Druecken um x / y position zu erhalten von Maus\n\n-Druecke 1 um einen simplen A bot zu spawnen\n\n -Druecke 2 um einen simplen B bot zu spawnen\n\n -Druecke 3 um einen simples Objectiv zu spawnen\n\n -Druecke 4 um einen simples haus zu spawnen\n");
		getContentPane().add(info);
		pack();
		setVisible(true);
		setSize(353,386);
		
		setLocation(frame.getX()-getWidth()+16 ,frame.getY());
		
	}
}
