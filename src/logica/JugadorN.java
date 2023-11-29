package logica;

import java.util.ArrayList;

public class JugadorN {
		private ArrayList<Ficha> turno;

		public ArrayList<Ficha> getTurno() {
			return turno;
		}
		
		public JugadorN() {
			this.turno = new ArrayList<Ficha>();
		}

		public static final int COLOR_FICHA = Ficha.COLOR_NEGRO;
}
