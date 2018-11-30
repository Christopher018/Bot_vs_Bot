package bot_vs_bot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Game extends JPanel {
	static JFrame frame = new JFrame("bot vs bot");
	// Alle Vectore
	protected Vector<Wand> alleWaende = new Vector<Wand>(20);
	protected Vector<Schilde> alleSchilde = new Vector<Schilde>(20);
	protected Vector<klick_anzeige> alleklicks = new Vector<klick_anzeige>(5);
	protected Vector<BOT> alleBots_Team_A = new Vector<BOT>(5);
	protected Vector<BOT> alleBots_Team_B = new Vector<BOT>(5);
	protected Vector<Projektil> alleProjektile = new Vector<Projektil>(100);
	protected Vector<Spielfeld> alleSpielfelder = new Vector<Spielfeld>(1);
	protected Vector<Objektiv> alleObjektives = new Vector<Objektiv>(10);
	private int Sieger = 0;
	private int Objectivzaehler = 0;
	private boolean alleObjectivesfertig;
	InputKey InputKey;

	public void paint(Graphics g) {
		super.paint(g);
		try {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			// zeichnet Spielfeld
			if (!InputKey.isStart()) {
				for (int i = 0; i < alleSpielfelder.size(); i++) {
					getSpielfeld(i).paint(g2d, 300, 300);
				}
			}
			// zeichnet projektile
			for (int i = 0; i < alleProjektile.size(); i++) {
				getProjektil(i).paint(g2d);
			}

			// zeichnet klicks
			for (int i = 0; i < alleklicks.size(); i++) {
				getklick(i).paint(g2d);
			}
			// zeichnet Waende
			for (int i = 0; i < alleWaende.size(); i++) {
				getWaende(i).paint(g2d);
			}
			// zeichnet Schilde
			for (int i = 0; i < alleSchilde.size(); i++) {
				getSchilde(i).paint(g2d);
			}
			// zeichnet Objektiv
			for (int i = 0; i < alleObjektives.size(); i++) {
				getObjektiv(i).paint(g2d);
			}

			// zeichnet bots (Team A)
			for (int i = 0; i < alleBots_Team_A.size(); i++) {
				// zeichnet lebensanzeige
				if (getbot_team_A(i).getLebenamanfang() != getbot_team_A(i).getLeben()) {
					getbot_team_A(i).paint_schaden(g2d);
				}
				// zeichnet char
				getbot_team_A(i).paint_char(g2d);
				// zeichnet cooldowns
				if (getbot_team_A(i).isCooldownaktiv()) {
					getbot_team_A(i).paint_cooldown(g2d);
					getbot_team_A(i).cooldownaktiv();
				}
			}
			// zeichnet bots (Team B)
			for (int i = 0; i < alleBots_Team_B.size(); i++) {
				// zeichnet lebensanzeige
				if (getbot_team_B(i).getLebenamanfang() != getbot_team_B(i).getLeben()) {
					getbot_team_B(i).paint_schaden(g2d);
				}
				// zeichnet char
				getbot_team_B(i).paint_char(g2d);
				// zeichnet cooldowns
				if (getbot_team_B(i).isCooldownaktiv()) {
					getbot_team_B(i).paint_cooldown(g2d);
					getbot_team_B(i).cooldownaktiv();
				}
			}
		} catch (NullPointerException ex) {

		}

	}

	public void schaden(Game game, InputKey inputKey) {
		for (int i = 0; i < alleProjektile.size(); i++) {
			if (getProjektil(i).collision()) {
				// schaden bot team A
				int ii = game.getProjektil(i).getBotNR();
				getbot_team_A(ii).setLeben(getbot_team_A(ii).getLeben() - getProjektil(i).getSchaden());
				alleProjektile.removeElement(getProjektil(i));
				if (getbot_team_A(ii).getLeben() < 1) {
					alleBots_Team_A.removeElement(getbot_team_A(ii));
				}
			}
		}

		for (int i = 0; i < alleProjektile.size(); i++) {
			if (getProjektil(i).collisionB()) {
				// schaden bot team B
				int ii = game.getProjektil(i).getBotNR();
				getbot_team_B(ii).setLeben(getbot_team_B(ii).getLeben() - getProjektil(i).getSchaden());
				alleProjektile.removeElement(getProjektil(i));
				if (getbot_team_B(ii).getLeben() < 1) {
					alleBots_Team_B.removeElement(getbot_team_B(ii));
				}
			}
		}

		// wenn projektile eine Wand beruehren
		for (int i = 0; i < alleProjektile.size(); i++) {
			if (getProjektil(i).collisionW()) {
				int ii = game.getProjektil(i).getWandNR();
				getWaende(ii).setLeben(getWaende(ii).getLeben() - getProjektil(i).getSchaden());
				alleProjektile.removeElement(getProjektil(i));
				if (getWaende(ii).getLeben() < 1) {
					alleWaende.removeElement(getWaende(ii));
				}
			}
		}

		// wenn projektile ein Schild beruehren
		for (int i = 0; i < alleProjektile.size(); i++) {
			if (getProjektil(i).collisionS()) {
				int ii = game.getProjektil(i).getSchildNR();
				getSchilde(ii).setLeben(getSchilde(ii).getLeben() - getProjektil(i).getSchaden());
				alleProjektile.removeElement(getProjektil(i));
				if (getSchilde(ii).getLeben() < 1) {
					alleSchilde.removeElement(getSchilde(ii));
				}
			}
		}

		// Sieger feststellen
		if (game.getAnzBotB() < 1 && game.getAnzBot() < 1) {
			System.out.println("Unentschieden");
			InputKey.setStart(false);
		} else {
			if (game.getAnzBotB() < 1) {
				System.out.println("Sieger: Team A");
				InputKey.setStart(false);
			}
			if (game.getAnzBot() < 1) {
				System.out.println("Sieger: Team B");
				InputKey.setStart(false);
			}
		}
		if (game.getAnzObjektiv() > 0) {
			for (int i = 0; i < alleObjektives.size(); i++) {
				if (getObjektiv(i).collision()) {
					Objectivzaehler = Objectivzaehler + 1;
					if (Objectivzaehler == 2) {
						if (getObjektiv(i).getWieweit() < 256) {
							getObjektiv(i).setWieweit(getObjektiv(i).getWieweit() + 1);
						}
						if (getObjektiv(i).getWieweit() > 255) {
							alleObjectivesfertig = true;
							for (int ii = 0; ii < alleObjektives.size(); ii++) {
								if (getObjektiv(ii).abgeschlossen() != 1) {
									alleObjectivesfertig = false;
								}
							}
							if (alleObjectivesfertig) {
								System.out.println("Sieger: Team A");
								InputKey.setStart(false);
							}
						}
						Objectivzaehler = 0;
					}
				}
			}
			for (int i = 0; i < alleObjektives.size(); i++) {
				if (getObjektiv(i).collisionB()) {
					Objectivzaehler = Objectivzaehler + 1;
					if (Objectivzaehler == 2) {
						if (getObjektiv(i).getWieweit() > -256) {
							getObjektiv(i).setWieweit(getObjektiv(i).getWieweit() - 1);
						}
						if (getObjektiv(i).getWieweit() < -255) {
							alleObjectivesfertig = true;
							for (int ii = 0; ii < alleObjektives.size(); ii++) {
								if (getObjektiv(ii).abgeschlossen() != -1) {
									alleObjectivesfertig = false;
								}
							}
							if (alleObjectivesfertig) {
								System.out.println("Sieger: Team B");
								InputKey.setStart(false);
							}
						}
						Objectivzaehler = 0;
					}
				}
			}
		}

	}

	public void bewegen() {
		// projektile
		for (int i = 0; i < alleProjektile.size(); i++) {
			getProjektil(i).bewegen();
		}
		// bots team A
		for (int i = 0; i < alleBots_Team_A.size(); i++) {
			getbot_team_A(i).bewegen();
		}
		// bots team B
		for (int i = 0; i < alleBots_Team_B.size(); i++) {
			getbot_team_B(i).bewegen();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Game game = new Game();
		frame.add(game, BorderLayout.CENTER);
		frame.setSize(650, 650);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		InputMouse InputMouse = new InputMouse(game);
		game.InputKey = new InputKey(game, frame);
		frame.addMouseListener(InputMouse);
		frame.addKeyListener(game.InputKey);
		frame.setBackground(Color.WHITE);
		frame.setAlwaysOnTop(true);

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

		// add Mittellinie
		Spielfeld Spielfeld = new Spielfeld(frame);
		game.addSpielfeld(Spielfeld);

		while (true) {
			game.repaint();
			if (game.InputKey.isStart()) {
				game.schaden(game, game.InputKey);
				game.bewegen();
			}
			Thread.sleep(10);
		}

	}

	public void addBot_Team_A(BOT bot_a) {
		alleBots_Team_A.addElement(bot_a);
	}

	public BOT getbot_team_A(int index) {
		return alleBots_Team_A.get(index);
	}

	public void addBot_Team_B(BOT bot_b) {
		alleBots_Team_B.addElement(bot_b);
	}

	public BOT getbot_team_B(int index) {
		return alleBots_Team_B.get(index);
	}

	public void addBProjektil(Projektil projektil) {
		alleProjektile.addElement(projektil);
	}

	public Projektil getProjektil(int index) {
		return alleProjektile.get(index);
	}

	public void addWaende(Wand wand) {
		alleWaende.addElement(wand);
	}

	public Wand getWaende(int index) {
		return alleWaende.get(index);
	}

	public void addSchilde(Schilde Schilde) {
		alleSchilde.addElement(Schilde);
	}

	public Schilde getSchilde(int index) {
		return alleSchilde.get(index);
	}

	public void addklick(klick_anzeige klick) {
		alleklicks.addElement(klick);
	}

	private klick_anzeige getklick(int index) {
		return alleklicks.get(index);
	}

	public void addSpielfeld(Spielfeld Spielfeld) {
		alleSpielfelder.addElement(Spielfeld);
	}

	private Spielfeld getSpielfeld(int index) {
		return alleSpielfelder.get(index);
	}

	public void addObjektiv(Objektiv Objektiv) {
		alleObjektives.addElement(Objektiv);
	}

	public Objektiv getObjektiv(int index) {
		return alleObjektives.get(index);
	}

	public int getAnzObjektiv() {
		return alleObjektives.size();
	}

	public int getAnzProjektil() {
		return alleProjektile.size();
	}

	public int getAnzBot() {
		return alleBots_Team_A.size();
	}

	public int getAnzBotB() {
		return alleBots_Team_B.size();
	}

	public int getAnzWaende() {
		return alleWaende.size();
	}

	public int getAnzSchilde() {
		return alleSchilde.size();
	}

	public Integer getbotmitNR(int NUMMER, Game game) {
		for (int i = 0; i < getAnzBot(); i++) {
			if (NUMMER == game.getbot_team_A(i).getNUMMER()) {
				return i;
			}
		}
		for (int i = 0; i < getAnzBotB(); i++) {
			if (NUMMER == game.getbot_team_B(i).getNUMMER()) {
				return i;
			}
		}
		return null;
	}

	public Integer getteammitNR(int NUMMER, Game game) {
		for (int i = 0; i < getAnzBot(); i++) {
			if (NUMMER == game.getbot_team_A(i).getNUMMER()) {
				return 1;
			}
		}
		for (int i = 0; i < getAnzBotB(); i++) {
			if (NUMMER == game.getbot_team_B(i).getNUMMER()) {
				return 2;
			}
		}
		return null;
	}

}
