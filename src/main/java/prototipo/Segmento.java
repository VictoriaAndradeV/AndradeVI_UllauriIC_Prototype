package prototipo;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author victo
 */
public class Segmento extends ElementoBase{

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, tamanio, tamanio);  

    }
    
}
