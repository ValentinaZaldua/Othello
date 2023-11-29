package logica;

import java.awt.Color;
import java.awt.Graphics;

public class Ficha {

    public static final int SIN_COLOR = 0;
    public static final int COLOR_NEGRO = 1;
    public static final int COLOR_BLANCO = 2;

    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Ficha() {
        this.color = SIN_COLOR;
    }

    public void dibujar(Graphics g, int x, int y, int lado) {
        if (color == COLOR_NEGRO) {
            g.setColor(Color.BLACK);
        } else if (color == COLOR_BLANCO) {
            g.setColor(Color.WHITE);
        }

        g.fillOval(x, y, lado, lado);
    }
}
