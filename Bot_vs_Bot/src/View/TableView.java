package View;

import javax.swing.*;
import javax.swing.table.*;

import Main.Game;
import Stat_anzeige.DataModel;

import java.awt.*;
import java.awt.event.*;

public class TableView extends JFrame{
		// Deklaration der Attribute
		private JTextField cellEditor;
		private JTable table;
		DataModel model;
		private JPanel pnl;
		Game game;
		String wert_String;
		int wert_int;

		public TableView(TableModel m, Game game , Frame frame) {
			super("Stats");
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.model = (DataModel) m;
			this.game = game;
			//
			init();
			//
			this.pack();
			this.setSize(600,200);
			this.setLocation(frame.getX()+ frame.getWidth() - 16, frame.getY());
			this.setVisible(true);
		}

		private void init() {
			// Tabelle erstellen und in Contentpane ablegen
			pnl = new JPanel();
			pnl.setLayout(new BorderLayout(0, 0));
			table = new JTable(model);


			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			getContentPane().add(pnl, BorderLayout.SOUTH);

			//
			// Als editierbares Objekt für die Zelle wird eine JTextField-Komponente
			// festgelegt = es können nur String verarbeitete werden.
			// Dieser Komponente wird dann ein KeyListener angehängt, der die
			// Eingabe der ENTER-Taste abfängt.
			// Danach wird aus der Editor-Komponente (und NICHT der Tabellenzelle!!)
			// der neu eingegebene Wert gelesen und dem
			// Datenmodel zugwiesen.
			// Hinweis: in der Tabellen-Zelle steht noch immer der alte Wert.

			/*-- Code wenn der Benutzer die Eingabe selber kontrollieren will*/
			cellEditor = new JTextField();
			
			cellEditor.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
						wert_String = ((JTextField) table.getEditorComponent()).getText();
						wert_int    =  Integer.parseInt(wert_String);
						
						if(table.getEditingRow() < game.getAnzBot()) {
							System.out.println(game.getbot_team_A(table.getEditingRow()).getName()+" wurde bearbeitet");
							if(table.getEditingColumn() == 1) {
								game.getbot_team_A(table.getEditingRow()).setLebenamanfang(wert_int);
								game.getbot_team_A(table.getEditingRow()).setLeben(wert_int);
							}
							if(table.getEditingColumn() == 2) {
								game.getbot_team_A(table.getEditingRow()).setSchaden(wert_int);
							}
							if(table.getEditingColumn() == 3) {
								game.getbot_team_A(table.getEditingRow()).setCooldown(wert_int);
							}
							if(table.getEditingColumn() == 4) {
								game.getbot_team_A(table.getEditingRow()).setSpeed(wert_int);
							}
							if(table.getEditingColumn() == 5) {
								game.getbot_team_A(table.getEditingRow()).setMovespeed(wert_int);
							}
						}else {
							int änderung_bot_b = table.getEditingRow() - game.getAnzBot();
							System.out.println(änderung_bot_b);
							System.out.println(game.getbot_team_B(table.getEditingRow()-game.getAnzBotB() +1).getName()+" wurde bearbeitet");
							if(table.getEditingColumn() == 1) {
								game.getbot_team_B(änderung_bot_b).setLebenamanfang(wert_int);
								game.getbot_team_B(änderung_bot_b).setLeben(wert_int);
							}
							if(table.getEditingColumn() == 2) {
								game.getbot_team_B(änderung_bot_b).setSchaden(wert_int);
							}
							if(table.getEditingColumn() == 3) {
								game.getbot_team_B(änderung_bot_b).setCooldown(wert_int);
							}
							if(table.getEditingColumn() == 4) {
								game.getbot_team_B(änderung_bot_b).setSpeed(wert_int);
							}
							if(table.getEditingColumn() == 5) {
								game.getbot_team_B(änderung_bot_b).setMovespeed(wert_int);
							}
						}
						}catch(java.lang.NumberFormatException ex) {
							System.out.println("ungueltiger Wert");
						}
					}
				}
			});
			table.setDefaultEditor(Object.class, new DefaultCellEditor(cellEditor));
			// Schaltfläche


		}

	}

