package prototipo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author victo
 */
public class Comida extends ElementoBase{
    
    @Override
    public void dibujar(Graphics g) {
        //dibujamos la comida 
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, tamanio, tamanio);
        //borde de la comida
        g.setColor(Color.ORANGE);
        g.drawOval(x, y, tamanio, tamanio);
        
        //dibujamos el id en el centro
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        
        String idComida = String.valueOf(id);
        FontMetrics fm = g.getFontMetrics();
        
        //calculo del ancho y alto del texto
        int textoAncho = fm.stringWidth(idComida);
        int textoAlto = fm.getAscent();//altura desde linea base hasta parte sup
        
        //centrado de texto
        int textoX = x + (tamanio - textoAncho) / 2;
        int textoY = y + (tamanio + textoAlto) / 2 - 2;
        
        g.drawString(idComida, textoX, textoY);
    }
    
}
