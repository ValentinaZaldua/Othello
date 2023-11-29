package presentacion;

import java.awt.BasicStroke;
import java.net.URL;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import logica.Othello;
import logica.Ficha;
import logica.JugadorB;
import logica.JugadorN;

public class PPantalla extends JPanel implements MouseListener{
	private Othello othello;
	private boolean perdio;
	
	private static final String RUTA_IMAGEN_NEGRA = "imagenes/negra.png";
    private static final String RUTA_IMAGEN_BLANCA = "imagenes/blanca.png";

    private ImageIcon imagenNegra;
    private ImageIcon imagenBlanca;
	
	public PPantalla() {
		this.othello = new Othello();
		this.addMouseListener(this);
		this.perdio = false;
		this.imagenNegra = new ImageIcon(RUTA_IMAGEN_NEGRA);
        this.imagenBlanca = new ImageIcon(RUTA_IMAGEN_BLANCA);
        this.repaint();
	}
	
	public void setOthello(Othello othello) {
        this.othello = othello;
    }
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(30, 132, 73));
		g2d.fillRect( 0,  0,  this.getWidth(), this.getHeight());
		
		g2d.setColor(Color.BLACK);
	    g2d.setStroke(new BasicStroke(3));
	    
	    
		for(int i=this.getWidth()/8; i<this.getWidth(); i+=this.getWidth()/8) {
			g2d.drawLine(i, 0, i, this.getHeight());
		}
		for(int i=this.getHeight()/8; i<this.getHeight(); i+=this.getHeight()/8) {
			g2d.drawLine(0, i, this.getWidth(), i);
		}
		

		int ladoCelda = this.getWidth() / 8;

		for (int i = 0; i < 8; i++) {
		    for (int j = 0; j < 8; j++) {
		        int x = i * ladoCelda;
		        int y = j * ladoCelda;
		        Ficha ficha = othello.getTablero().get(i * 8 + j);

		        Image image = obtenerImagenFicha(ficha.getColor());
                g2d.drawImage(image, x, y, ladoCelda, ladoCelda, this);

                int fichaMoviendoFila = 0;
				int fichaMoviendoColumna = 0;
				if (i == fichaMoviendoFila && j == fichaMoviendoColumna) {
                    if (fichaMoviendoFila >= 0 && fichaMoviendoColumna >= 0) {
                       /* g2d.setColor(new Color(255, 255, 255, 128)); // Color transparente
                        g2d.fillRect(x, y, ladoCelda, ladoCelda);
                        g2d.drawImage(image, x, y, ladoCelda, ladoCelda, this);
                    */
                    	g2d.drawImage(image, x, y, this);	
                    }
                }
            
		    }
		}

	

		int xN = getWidth() / 2 - othello.getJugadorN().getTurno().size() * 60;
		int xB = getWidth() / 2 - othello.getJugadorB().getTurno().size() * 60;

		for (Ficha ficha : othello.getJugadorN().getTurno()) {
            Image image = Toolkit.getDefaultToolkit().getImage("imagenes/negra.png");
            g2d.drawImage(image, xN, getHeight() - 180, this);
            xN += 120;
        }

        for (Ficha ficha : othello.getJugadorB().getTurno()) {
            Image image = Toolkit.getDefaultToolkit().getImage("imagenes/blanca.png");
            g2d.drawImage(image, xB, 20, this);
            xB += 120;
        }
    }

    private Image obtenerImagenFicha(int color) {
        if (color == Ficha.COLOR_NEGRO) {
            return Toolkit.getDefaultToolkit().getImage("imagenes/negra.png");
        } else if (color == Ficha.COLOR_BLANCO) {
            return Toolkit.getDefaultToolkit().getImage("imagenes/blanca.png");
        } else {
            return null; 
        }
    }
	
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int fila = 8 - y / (getHeight() / 8 );
        int columna = 8 - x / (getWidth() / 8);

        int colorJugadorActual = othello.turnoJugadorActual();

        if (colorJugadorActual == JugadorN.COLOR_FICHA) {
            othello.realizarAccion(fila, columna);
            Ficha nuevaFicha = new Ficha();  // Crea una nueva ficha o ajusta según tu lógica
            othello.getJugadorN().agregarFicha(nuevaFicha);  // Agrega la ficha al conjunto del jugador
            System.out.println("Ficha agregada al JugadorN");
            System.out.println("JugadorN tiene " + othello.getJugadorN().getTurno().size() + " fichas.");
        } else if (colorJugadorActual == JugadorB.COLOR_FICHA) {
            othello.realizarAccionJugadorB(fila, columna);
            Ficha nuevaFicha = new Ficha();  // Crea una nueva ficha o ajusta según tu lógica
            othello.getJugadorB().agregarFicha(nuevaFicha);  // Agrega la ficha al conjunto del jugador
            System.out.println("Ficha agregada al JugadorB");
            System.out.println("JugadorB tiene " + othello.getJugadorB().getTurno().size() + " fichas.");
        }

        repaint();
    
    }


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public Othello getOthello() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
