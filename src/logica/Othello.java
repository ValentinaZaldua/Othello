package logica;

import java.util.ArrayList;
import java.util.Random;

public class Othello {
	private JugadorB jugadorB;
	private JugadorN jugadorN;
	private ArrayList<Ficha> tablero;
	
	public JugadorB getJugadorB() {
		return jugadorB;
	}
	
	public JugadorN getJugadorN() {
		return jugadorN;
	}
	
	public ArrayList<Ficha> getTablero() {
		return tablero;
	}

	public Othello() {
		super();
		this.jugadorB = new JugadorB();
		this.jugadorN = new JugadorN();
		this.tablero = new ArrayList<Ficha>();
	}
	
	
	
}
