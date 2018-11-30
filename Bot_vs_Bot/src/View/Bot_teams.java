package View;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Main.Game;
import Map_bestaende.Spawn;
import Map_bestaende.Wand;

public class Bot_teams extends JFrame implements ActionListener {
	//hier wird ein fenster erstellt, das einem zeigt was es für vordefinierte
	//teams und maps gibt, die man auswahlen kann und starten kann
	Game game;
	Frame frame;
	JButton btn1 = new JButton("Ja");
	JButton btn2 = new JButton("Nein");
	JLabel textoben = new JLabel("TeamA Oben?");
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	String[] teamA = { "auswaehlen","Team A Map1","Test Team A"};
	String[] teamB = { "auswaehlen","Team B Map1","Test Team B (einfach)","Test Team B (einfach(2))"};
	JComboBox teamAList = new JComboBox(teamA);
	JComboBox teamBList = new JComboBox(teamB);
	JLabel textvs = new JLabel("Wer gegen Wen?");
	private int welchesteamA = 0;
	private int welchesteamB = 0;


	public Bot_teams(Game game, Frame frame, Infos infos) {
		this.game = game;
		this.frame = frame;
		setTitle("teams (T)");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel.setLayout(new GridLayout(3, 2, 0, 0));
		panel2.setLayout(new GridLayout(1, 2, 0, 0));
		panel.add(textoben);

		panel.add(btn1);
		panel.add(btn2);
		
		
		panel2.add(teamAList);
		panel2.add(teamBList);
		
		try {
		setSize(infos.getWidth(), 170);
		} catch (NullPointerException ex) {
			setSize(353, 170);
		}
		getContentPane().add(panel,BorderLayout.NORTH);
		getContentPane().add(panel2,BorderLayout.SOUTH);
		getContentPane().add(textvs,BorderLayout.CENTER);
		setVisible(true);
		
		try {
			setLocation(frame.getX() - getWidth() + 16, frame.getY() + infos.getHeight() - 7);
		} catch (NullPointerException ex) {
			setLocation(frame.getX() - getWidth() + 15, frame.getY());
		}
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		teamAList.addActionListener(this);
		teamBList.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btn1) {
			game.alleBots_Team_A.removeAllElements();
			game.alleBots_Team_B.removeAllElements();
			game.alleWaende.removeAllElements();
			game.alleObjektives.removeAllElements();
			if (welchesteamA > 0 && welchesteamB > 0) {
				new Spawn(game, true,welchesteamA,welchesteamB);
			}else {
				System.out.println("keine Teams ausgewaehlt");
			}
		}

		if (e.getSource() == btn2) {
			game.alleBots_Team_A.removeAllElements();
			game.alleBots_Team_B.removeAllElements();
			game.alleWaende.removeAllElements();
			game.alleObjektives.removeAllElements();
			if (welchesteamA > 0 && welchesteamB > 0) {
				new Spawn(game, false,welchesteamA,welchesteamB);
			}else {
				System.out.println("keine Teams ausgewaehlt");
			}
		}
		if (e.getSource() == teamAList) {
			JComboBox was = (JComboBox)e.getSource();
	        String name = (String)was.getSelectedItem();
	        if(name.equals("Test Team A")) {
	        	welchesteamA = 1;
	        }
	        if(name.equals("Team A Map1")) {
	        	welchesteamA = 2;
	        }
		}
		if (e.getSource() == teamBList) {
			JComboBox was = (JComboBox)e.getSource();
	        String name = (String)was.getSelectedItem();
	        if(name.equals("Test Team B (einfach)")) {
	        	welchesteamB = 1;
	        }
	        if(name.equals("Test Team B (einfach(2))")) {
	        	welchesteamB = 2;
	        }
	        if(name.equals("Team B Map1")) {
	        	welchesteamB = 3;
	        }
		}
		// arena umranden mit waenden
		Wand linkewand = new Wand(-20, 0, frame.getWidth(), frame.getHeight(), 20, frame.getHeight(), 10000, game);
		game.addWaende(linkewand);
		Wand rechtewand = new Wand(frame.getWidth() - 16, 0, frame.getWidth(), frame.getHeight(), 20, frame.getHeight(),
				10000, game);
		game.addWaende(rechtewand);
		Wand unterewand = new Wand(0, frame.getHeight() - 39, frame.getWidth(), frame.getHeight(), frame.getWidth(), 20,
				10000, game);
		game.addWaende(unterewand);
		Wand oberewand = new Wand(0, -20, frame.getWidth(), frame.getHeight(), frame.getWidth(), 20, 10000, game);
		game.addWaende(oberewand);
	}
}
