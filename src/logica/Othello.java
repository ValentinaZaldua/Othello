package logica;

import java.util.ArrayList;

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

    private int turnoJugadorN = 0; 
    private int turnoJugadorB = 0;
    
    public Othello() {
        super();
        this.jugadorB = new JugadorB();
        this.jugadorN = new JugadorN();
        this.tablero = new ArrayList<Ficha>();

        
        for (int i = 0; i < 64; i++) {
            this.tablero.add(new Ficha()); 
        }
    }

    public void inicializarTablero() {
        
         tablero.get(3 * 8 + 3).setColor(Ficha.COLOR_NEGRO);
         tablero.get(3 * 8 + 4).setColor(Ficha.COLOR_BLANCO);
         tablero.get(4 * 8 + 3).setColor(Ficha.COLOR_BLANCO);
         tablero.get(4 * 8 + 4).setColor(Ficha.COLOR_NEGRO);
         
         turnoJugadorN = 0;
         turnoJugadorB = 0;
    }
    	
    public void realizarAccion(int fila, int columna) {
        int colorJugadorActual = turnoJugadorActual();

        if (tablero.get(fila * 8 + columna).getColor() == Ficha.SIN_COLOR) {

            if (verificarMovimientoValido(fila, columna, colorJugadorActual)) {
                tablero.get(fila * 8 + columna).setColor(colorJugadorActual);

                voltearFichasAtrapadas(fila, columna, colorJugadorActual);

               
            } else {
                System.out.println("Movimiento no válido. Intenta nuevamente.");
            }
        } else {
            System.out.println("La posición ya está ocupada. Intenta nuevamente.");
        }
   
        cambiarTurno(colorJugadorActual);
    }

    private void cambiarTurno(int colorJugadorActual) {
    	turno = turnoJugadorN + turnoJugadorB;

    	 if (colorJugadorActual == JugadorN.COLOR_FICHA) {
    	        turnoJugadorN++;
    	    } else if (colorJugadorActual == JugadorB.COLOR_FICHA) {
    	        turnoJugadorB++;
    	    }
		
	}

	public void realizarAccionJugadorB(int fila, int columna) {
		int colorJugadorActual = JugadorB.COLOR_FICHA;

		cambiarTurno(colorJugadorActual);
    }
    	
    	private int turno;

    	public int turnoJugadorActual() {
    		if ((turnoJugadorN + turnoJugadorB) % 2 == 0) {
    	        return JugadorN.COLOR_FICHA;
    	    } else {
    	        return JugadorB.COLOR_FICHA;
    	    }
    	}

    	
    	private void voltearFichasAtrapadas(int fila, int columna, int colorJugadorActual) {
    	    for (int dirFila = -1; dirFila <= 1; dirFila++) {
    	        for (int dirColumna = -1; dirColumna <= 1; dirColumna++) {
    	            if (dirFila == 0 && dirColumna == 0) {
    	                continue;
    	            }

    	            voltearEnDireccion(fila, columna, dirFila, dirColumna, colorJugadorActual);
    	        }
    	    }
    	}

    	private void voltearEnDireccion(int fila, int columna, int dirFila, int dirColumna, int colorJugadorActual) {
    	    int x = fila + dirFila;
    	    int y = columna + dirColumna;

    	    while (x >= 0 && x < 8 && y >= 0 && y < 8 && tablero.get(x * 8 + y).getColor() == obtenerColorOpuesto(colorJugadorActual)) {
    	        x += dirFila;
    	        y += dirColumna;
    	    }

    	    if (x >= 0 && x < 8 && y >= 0 && y < 8 && tablero.get(x * 8 + y).getColor() == colorJugadorActual) {
    	        x -= dirFila;
    	        y -= dirColumna;
    	        while (x != fila || y != columna) {
    	            tablero.get(x * 8 + y).setColor(colorJugadorActual);
    	            x -= dirFila;
    	            y -= dirColumna;
    	        }
    	    }
    	}


    	    private boolean verificarMovimientoValido(int fila, int columna, int colorJugadorActual) {
    	        if (tablero.get(fila * 8 + columna).getColor() != Ficha.SIN_COLOR) {
    	            return false;
    	        }

    	        for (int dirFila = -1; dirFila <= 1; dirFila++) {
    	            for (int dirColumna = -1; dirColumna <= 1; dirColumna++) {
    	                if (dirFila == 0 && dirColumna == 0) {
    	                    continue;
    	                }

    	                if (verificarDireccion(fila, columna, dirFila, dirColumna, colorJugadorActual)) {
    	                    return true;
    	                }
    	            }
    	        }

    	        return false;
    	    }

    	    private boolean verificarDireccion(int fila, int columna, int dirFila, int dirColumna, int colorJugadorActual) {
    	        int x = fila + dirFila;
    	        int y = columna + dirColumna;

    	        if (x < 0 || x >= 8 || y < 0 || y >= 8 || tablero.get(x * 8 + y).getColor() != obtenerColorOpuesto(colorJugadorActual)) {
    	            return false;
    	        }

    	        while (x >= 0 && x < 8 && y >= 0 && y < 8 && tablero.get(x * 8 + y).getColor() == obtenerColorOpuesto(colorJugadorActual)) {
    	            x += dirFila;
    	            y += dirColumna;
    	        }

    	        return x >= 0 && x < 8 && y >= 0 && y < 8 && tablero.get(x * 8 + y).getColor() == colorJugadorActual;
    	    }

    	    private int obtenerColorOpuesto(int color) {
    	        return (color == Ficha.COLOR_NEGRO) ? Ficha.COLOR_BLANCO : Ficha.COLOR_NEGRO;
    	    }

    }

