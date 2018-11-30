package bot_vs_bot;

public class DataClass {
		// Die Klasse enthaelt die Attribute des Datenobjekts
		private String 	name;
		private int    	Leben;
		private int    	schaden;
		private int    	cooldown;
		private int 	speed;
		private int 	movespeed;
		private int 	Faehigkeit;
		private int 	Punkte;
		
		/** 
		 * Leerer Konstrukter für ein Objekt der Daten-Klasse
		 */
		public DataClass(){
			
		}
		
		/**
		 * Parametrierter Konstruktor der Data-Klasse
		 * @param punkte 
		 */
		public DataClass(String name,int Leben, int schaden, int cooldown,int speed, int movespeed, int Faehigkeit, int punkte){
			this.name		= 	name;
			this.Leben 		= 	Leben;
			this.schaden    = 	schaden;
			this.cooldown   = 	cooldown;
			this.speed 		= 	speed;
			this.movespeed  =	movespeed;
			this.Faehigkeit =   Faehigkeit;
			this.Punkte 	=   punkte;
		}
		
		public void setLeben(int Leben){
			this.Leben = Leben;
		}
		
		public void setschaden(int schaden){
			this.schaden = schaden;
		}
		
		public void setcooldown(int cooldown){
			this.cooldown = cooldown;
		}
		
		public void setSchaden(int schaden) {
			this.schaden = schaden;
		}


		public void setSpeed(int speed) {
			this.speed = speed;
		}

		public void setMovespeed(int movespeed) {
			this.movespeed = movespeed;
		}

		public void setFaehigkeit(int faehigkeit) {
			Faehigkeit = faehigkeit;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public void setPunkte(int punkte) {
			Punkte = punkte;
		}
		

		public int getLeben(){
			return this.Leben;
		}
		
		public int getschaden(){
			return this.schaden;
		}
		
		public int getcooldown(){
			return this.cooldown;
		}

		public int getSchaden() {
			return schaden;
		}

		public int getSpeed() {
			return speed;
		}

		public int getMovespeed() {
			return movespeed;
		}

		public int getFaehigkeit() {
			return Faehigkeit;
		}

		public String getName() {
			return name;
		}

		public int getPunkte() {
			return Punkte;
		}
		
		
		
}
