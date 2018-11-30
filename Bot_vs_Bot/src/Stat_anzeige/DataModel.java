package Stat_anzeige;

import java.util.*;
import javax.swing.JTextField;
import javax.swing.table.*;

import Main.Game;
import View.TableView;

public class DataModel extends AbstractTableModel {
		//diese Klasse zeigt die Stats von einem Bot in einer Tabelle an und laesst es zu,
		//dass man diese editiert
		protected Vector<DataClass> dataVector;
		TableView view;
		private String 	name;
		private int    	Leben;
		private int    	schaden;
		private int    	cooldown;
		private int 	speed;
		private int 	movespeed;
		private int 	Faehigkeit;
		private int 	Punkte;
		//leben, schaden, cooldown, speed, movespeed,Faehigkeit,
		private String[] title = { "Name","Leben", "Schaden", "Cooldown","speed", "movespeed", "Faehigkeit","Anz Punkt" };

		public DataModel(Game game) {
			super();
			dataVector = new Vector<DataClass>();

			for (int i = 0; game.getAnzBot() > i; i++) {
				name = game.getbot_team_A(i).getName();
				Leben = game.getbot_team_A(i).getLebenamanfang();
				schaden = game.getbot_team_A(i).getSchaden();
				cooldown = 17-game.getbot_team_A(i).getCooldown();
				speed = game.getbot_team_A(i).getSpeed();
				movespeed = game.getbot_team_A(i).getMovespeed();
				Faehigkeit = game.getbot_team_A(i).getFaehigkeit();
				Punkte		= Leben + schaden + cooldown +speed+ movespeed;
				DataClass data = new DataClass(name,Leben, schaden, cooldown, speed, movespeed, Faehigkeit, Punkte);
				dataVector.addElement(data);
			}
			for (int i = 0; game.getAnzBotB() > i; i++) {
				name = game.getbot_team_B(i).getName();
				Leben = game.getbot_team_B(i).getLebenamanfang();
				schaden = game.getbot_team_B(i).getSchaden();
				cooldown = 17-game.getbot_team_B(i).getCooldown();
				speed = game.getbot_team_B(i).getSpeed();
				movespeed = game.getbot_team_B(i).getMovespeed();
				Faehigkeit = game.getbot_team_B(i).getFaehigkeit();
				Punkte		= Leben + schaden + cooldown +speed+ movespeed;
				DataClass data = new DataClass(name,Leben, schaden, cooldown, speed, movespeed, Faehigkeit,Punkte);
				dataVector.addElement(data);
			}

		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return title.length;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return dataVector.size();
		}

		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			return title[column];
		}

		@Override
		public Object getValueAt(int arg0, int arg1) {
			// TODO Auto-generated method stub
			if (arg1 == 0) {
				return dataVector.elementAt(arg0).getName();
			}
			if (arg1 == 1) {
				return dataVector.elementAt(arg0).getLeben();
			}
			if (arg1 == 2) {
				return dataVector.elementAt(arg0).getschaden();
			} 
			if (arg1 == 3) {
				return dataVector.elementAt(arg0).getcooldown();
			}
			if (arg1 == 4) {
				return dataVector.elementAt(arg0).getSpeed();
			}
			if (arg1 == 5) {
				return dataVector.elementAt(arg0).getMovespeed();
			}
			if (arg1 == 6) {
				return dataVector.elementAt(arg0).getFaehigkeit();
			}
			 else {
				return dataVector.elementAt(arg0).getPunkte();
			}
		}

		public boolean isCellEditable(int rowIndex, int colunIndex) {
			if (colunIndex == 0 || colunIndex == 7) {
				return false;
			} else {
				return true;
			}
		}

		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			try{
			String str = aValue.toString();
			if (1 == columnIndex) {
				int in = Integer.parseInt(str);
				dataVector.get(rowIndex).setLeben(in);
				dataVector.get(rowIndex).setPunkte(in + schaden + cooldown +speed+ movespeed);
			}
			if (2 == columnIndex) {
				int in = Integer.parseInt(str);
				dataVector.get(rowIndex).setSchaden(in);
				dataVector.get(rowIndex).setPunkte(Leben + in + cooldown +speed+ movespeed);
			}
			if (3 == columnIndex) {
				int in = Integer.parseInt(str);
				dataVector.get(rowIndex).setcooldown(in);
				dataVector.get(rowIndex).setPunkte(Leben + schaden + in +speed+ movespeed);
			}
			if (4 == columnIndex) {
				int in = Integer.parseInt(str);
				dataVector.get(rowIndex).setSpeed(in);
				dataVector.get(rowIndex).setPunkte(Leben + schaden + cooldown +in+ movespeed);
			}
			if (5 == columnIndex) {
				int in = Integer.parseInt(str);
				dataVector.get(rowIndex).setMovespeed(in);
				dataVector.get(rowIndex).setPunkte(Leben + schaden + cooldown +speed+ in);
			}
			if (6 == columnIndex) {
				int in = Integer.parseInt(str);
				dataVector.get(rowIndex).setFaehigkeit(in);
			}
			fireTableCellUpdated(rowIndex, columnIndex);
			fireTableCellUpdated(rowIndex, 7);
			
			}catch(java.lang.NumberFormatException ex) {
				
			}
		}

		public void addRow(DataClass obj) {
			dataVector.addElement(obj);
			fireTableDataChanged();
		}
		
		
}
