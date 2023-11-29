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

        // Agregar 64 fichas al tablero
        for (int i = 0; i < 64; i++) {
            this.tablero.add(new Ficha()); // Agrega fichas con color 0 (o cualquier valor inicial)
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

        // Verificar si la posición está vacía
        if (tablero.get(fila * 8 + columna).getColor() == Ficha.SIN_COLOR) {

            // Verificar si hay fichas del oponente que se pueden voltear
            if (verificarMovimientoValido(fila, columna, colorJugadorActual)) {
                // Colocar la nueva ficha del jugador
                tablero.get(fila * 8 + columna).setColor(colorJugadorActual);

                // Voltear las fichas del oponente atrapadas
                voltearFichasAtrapadas(fila, columna, colorJugadorActual);

               
            } else {
                // El movimiento no es válido, puedes manejarlo según tus necesidades
                System.out.println("Movimiento no válido. Intenta nuevamente.");
            }
        } else {
            // La posición ya está ocupada, puedes manejarlo según tus necesidades
            System.out.println("La posición ya está ocupada. Intenta nuevamente.");
        }
   
        cambiarTurno(colorJugadorActual);
    }

    private void cambiarTurno(int colorJugadorActual) {
    	 turno++;

    	 if (colorJugadorActual == JugadorN.COLOR_FICHA) {
    	        turnoJugadorN++;
    	    } else if (colorJugadorActual == JugadorB.COLOR_FICHA) {
    	        turnoJugadorB++;
    	    }
		
	}

	public void realizarAccionJugadorB(int fila, int columna) {
		int colorJugadorActual = JugadorB.COLOR_FICHA;

	    // Resto de tu método existente...

	    // Cambiar el turno al siguiente jugador
		cambiarTurno(JugadorB.COLOR_FICHA);
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
    	                // No verifica en la misma posición
    	                continue;
    	            }

    	            voltearEnDireccion(fila, columna, dirFila, dirColumna, colorJugadorActual);
    	        }
    	    }
    	}

    	private void voltearEnDireccion(int fila, int columna, int dirFila, int dirColumna, int colorJugadorActual) {
    	    int x = fila + dirFila;
    	    int y = columna + dirColumna;

    	    // Busca la ficha del jugador actual en esa dirección
    	    while (x >= 0 && x < 8 && y >= 0 && y < 8 && tablero.get(x * 8 + y).getColor() == obtenerColorOpuesto(colorJugadorActual)) {
    	        x += dirFila;
    	        y += dirColumna;
    	    }

    	    // Si hay una ficha del jugador actual al final, entonces se deben voltear las fichas en esa dirección
    	    if (x >= 0 && x < 8 && y >= 0 && y < 8 && tablero.get(x * 8 + y).getColor() == colorJugadorActual) {
    	        // Retrocede y cambia el color de las fichas del oponente atrapadas
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
    	            // La posición no está vacía, movimiento no válido
    	            return false;
    	        }

    	        // Verifica en todas las direcciones posibles si hay fichas del oponente para atrapar
    	        for (int dirFila = -1; dirFila <= 1; dirFila++) {
    	            for (int dirColumna = -1; dirColumna <= 1; dirColumna++) {
    	                if (dirFila == 0 && dirColumna == 0) {
    	                    // No verifica en la misma posición
    	                    continue;
    	                }

    	                if (verificarDireccion(fila, columna, dirFila, dirColumna, colorJugadorActual)) {
    	                    // Se encontraron fichas del oponente que se pueden atrapar en esta dirección
    	                    return true;
    	                }
    	            }
    	        }

    	        return false;
    	    }

    	    private boolean verificarDireccion(int fila, int columna, int dirFila, int dirColumna, int colorJugadorActual) {
    	        // Verifica en una dirección específica si hay fichas del oponente para atrapar
    	        int x = fila + dirFila;
    	        int y = columna + dirColumna;

    	        // Debe haber al menos una ficha del oponente adyacente
    	        if (x < 0 || x >= 8 || y < 0 || y >= 8 || tablero.get(x * 8 + y).getColor() != obtenerColorOpuesto(colorJugadorActual)) {
    	            return false;
    	        }

    	        // Busca la ficha del jugador actual en esa dirección
    	        while (x >= 0 && x < 8 && y >= 0 && y < 8 && tablero.get(x * 8 + y).getColor() == obtenerColorOpuesto(colorJugadorActual)) {
    	            x += dirFila;
    	            y += dirColumna;
    	        }

    	        // Si hay una ficha del jugador actual al final, entonces es un movimiento válido
    	        return x >= 0 && x < 8 && y >= 0 && y < 8 && tablero.get(x * 8 + y).getColor() == colorJugadorActual;
    	    }

    	    private int obtenerColorOpuesto(int color) {
    	        return (color == Ficha.COLOR_NEGRO) ? Ficha.COLOR_BLANCO : Ficha.COLOR_NEGRO;
    	    }

    }

