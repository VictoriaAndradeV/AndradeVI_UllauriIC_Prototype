package com.mycompany.andradevi_ullauriic_prototype;

import java.util.ArrayList;
import prototipo.Segmento;

/**
 * @author victo
 */
public class Serpiente {
    //lista de segmentos de la serpiente
    private ArrayList<Segmento> segmentos;
    
    //coordenadas de posicion de la cabeza 
    //cabeza = segmentoBase
    private int cabezaX;
    private int cabezaY;

    public Serpiente(Segmento cabeza, int cabezaX, int cabezaY) {
        segmentos = new ArrayList<>();
        segmentos.add(cabeza);
        this.cabezaX = cabezaX;
        this.cabezaY = cabezaY;
    }
    
    public Segmento getCabeza(){
        //devolvemos lo que se encuentre en la lista de segmentos
        //posicion 0, que es en donde esta la cabeza
        return segmentos.get(0);
    }
    
    //se mueve paso a paso en la direccion indicada por las flechas
    public void moverSerpiente(PanelTableroSnake panelTablero, Direccion dir){
        //guardan posiciones de la cabeza
        int posicionAX = cabezaX;//posicion Actual X
        int posicionAY = cabezaY;
        
        //ajuste de posicion segun la direccion que da el usuario
        switch(dir){
            case ARRIBA: 
                posicionAY--; //arriba = una fila menos
                break;
            case ABAJO://va una fila abajo entonces +
                posicionAY++;
                break;
            case DERECHA:
                posicionAX++;
                break;
            case IZQUIERDA:
                posicionAX--;
                break;
        }
        
        if(posicionAX < 0 || (posicionAX >= 20) || (posicionAY < 0) || (posicionAY >=20)){
            return;
        }
        
        cabezaX = posicionAX;
        cabezaY = posicionAY;
        
        panelTablero.colocarElementoCelda(getCabeza(), cabezaX, cabezaY);
    } 
    
}
