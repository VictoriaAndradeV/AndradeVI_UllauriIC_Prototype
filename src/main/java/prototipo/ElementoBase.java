package prototipo;

import java.awt.Graphics;

/**
 *
 * @author victo
 */
public abstract class ElementoBase implements Cloneable{
    
    private String tipoElemento; //si es segmentoBase o comidaBase
    protected int id; // identificador para la comida
    protected int x, y; // es para la posicion en el tablero de cada elemento
    protected int tamanio = 25; //tamanio de los elementoss
    
    public abstract void dibujar(Graphics g);

    public String getTipoElemento() {
        return tipoElemento;
    }

    public void setTipoElemento(String tipoElemento) {
        this.tipoElemento = tipoElemento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
    
    //redefinimos el codigo que tiene el metodo clone
    //es el corazon del patron PROTOTIPO
    @Override
    public ElementoBase clone()
    {
        try{
            //el super.clone me devuelve la copia del objeto
            return (ElementoBase)super.clone(); 
        }catch (CloneNotSupportedException ex){
            return null;
        }
       
    }
    
}
