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
    
    public int getCabezaX() {
        return cabezaX;
    }

    public int getCabezaY() {
        return cabezaY;
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
        //------------------------------
        //guarda las posiciones de todos los segmentos
        ArrayList<int[]> anterior = new ArrayList<>(segmentos.size());
        for (Segmento s : segmentos) {
            anterior.add(new int[]{panelTablero.getCeldaElementoX(s), panelTablero.getCeldaElementoY(s)});
        }
        
        cabezaX = posicionAX;
        cabezaY = posicionAY;
        panelTablero.colocarElementoCelda(getCabeza(), cabezaX, cabezaY);
        
        //-----------------------------------
        //mueve el resto del cuerpo siguiendo al anterior
        for (int i = 1; i < segmentos.size(); i++) {
            int[] pos = anterior.get(i - 1);
            panelTablero.colocarElementoCelda(segmentos.get(i), pos[0], pos[1]);
        }
    } 
    
    //-----------------------------------------
    // Agrega un segmento nuevo al final en la ultima posicion del cuerpo
    public void agregarSegmento(PanelTableroSnake panelTablero, Segmento nuevo) {

        //obtiene el último segmento actual de la serpiente
        int colaIndice = segmentos.size() - 1;
        Segmento cola = segmentos.get(colaIndice);

       //calcula en qué celda está actualmente la cola
        int cx = panelTablero.getCeldaElementoX(cola);
        int cy = panelTablero.getCeldaElementoY(cola);

        //pone el nuevo segmento en esa misma celda
        //en el siguiente movimiento se pone automaticamente al final
        panelTablero.colocarElementoCelda(nuevo, cx, cy);

        //pone el nuevo segmento en la lista de partes
        segmentos.add(nuevo);
    }

    
}
