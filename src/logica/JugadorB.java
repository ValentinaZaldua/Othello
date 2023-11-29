package logica;

import java.util.ArrayList;

public class JugadorB {
	private ArrayList<Ficha> turno;

	public ArrayList<Ficha> getTurno() {
		return turno;
	}

	public JugadorB() {
		this.turno = new ArrayList<Ficha>();
	}

	 public static final int COLOR_FICHA = Ficha.COLOR_BLANCO;
	 
	 public void agregarFicha(Ficha ficha) {
	        // Método para agregar una ficha al turno del jugador B
	        turno.add(ficha);
		}
}
