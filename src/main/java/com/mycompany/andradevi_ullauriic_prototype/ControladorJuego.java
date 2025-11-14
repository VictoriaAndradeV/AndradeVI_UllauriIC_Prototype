package com.mycompany.andradevi_ullauriic_prototype;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import prototipo.ElementoBase;
import prototipo.RepositorioElementoBase;
import prototipo.Segmento;

/**
 *
 * @author victo
 */
public class ControladorJuego {
    private PanelTableroSnake panelTablero;
    private final ArrayList<ElementoBase> elementos;
    private int contComida = 1; //inicia en 1 porq el elemento base ya tiene el 0
    private Serpiente serpiente;
    private boolean juegoIniciado = false;

    public ControladorJuego(PanelTableroSnake panel) {
        this.panelTablero = panel;
        this.elementos = new ArrayList<>();
        this.panelTablero.setElementos(elementos);
    }

    //bton que inciia objetos
    public void iniciarObjetos(){
        //se crean elem base
        RepositorioElementoBase.crearElementoBase();
        
        //obtenemos los elem base
        ElementoBase comidaBase = RepositorioElementoBase.getElementoBase("comidaBase");
        ElementoBase segmentoBase = RepositorioElementoBase.getElementoBase("segmentoBase");
        
        int cabezaX = 10;
        int cabezaY = 10;
        
        panelTablero.colocarElementoCelda(segmentoBase, cabezaX, cabezaY);
        panelTablero.colocarElementoCelda(comidaBase, 5, 5);
        
        elementos.add(comidaBase);
        elementos.add(segmentoBase);
        
        serpiente = new Serpiente((Segmento) segmentoBase, cabezaX, cabezaY);
        //false porque aun no empieza el juego
        juegoIniciado = false;
        panelTablero.repaint();
    }
    
    //btn clonar comida
    public void clonarComida() {
        if (contComida >= 20){
            JOptionPane.showMessageDialog(panelTablero,
            "Máximo 20 comidas",
            "Límite de clones", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //crear el clon de la comida
        ElementoBase clonComida = RepositorioElementoBase.getElementoBaseClonado("comidaBase");
        
        if(clonComida == null) return;
        
        clonComida.setId(contComida);
        //localizamos la base
        //solo para validar si ya esta creada la base
        ElementoBase base = obtenerElementoBase();
        if(base == null){
            JOptionPane.showMessageDialog(panelTablero,
            "No se existe la comida base",
            "Clonar Comida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int baseX = panelTablero.getCeldaElementoX(base);
        int baseY = panelTablero.getCeldaElementoY(base);
        
        //Busca la primera celda libre debajo de la comida base
        //para ubicarse debajo de esta
        int[] celdaLibre = panelTablero.celdaLibreBase(baseX, baseY);
        if(celdaLibre == null){
            JOptionPane.showMessageDialog(panelTablero,
            "No hay espacio libre debajo de la base",
            "Clonar Comida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        panelTablero.colocarElementoCelda(clonComida, celdaLibre[0], celdaLibre[1]);
        elementos.add(clonComida);
        contComida++;
        panelTablero.repaint();
    }
    
    public void actualizarComida(int id, int x, int y){
        //validamos que el id ingresado sea correcto
        if((id <= 0) || (id >= contComida)){
            JOptionPane.showMessageDialog(panelTablero, 
            "ID NO valido, debe ingresar el id de un clon existente entre 1 y " + (contComida - 1),
            "Actualizar Comida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //validar que las coordenadas x sean correctas
        if((x < 1) || (x > 20) || (y < 1) || (y > 20)){
            JOptionPane.showMessageDialog(panelTablero, 
            "Coordenadas invalidas, ingrese un numero del 1 al 20",
            "Actualizar Comida", JOptionPane.WARNING_MESSAGE);
            return; 
        }
        
        //buscar comida con id ingresado
        ElementoBase comidaBuscada = null;
        for(ElementoBase e: elementos){
            if(e.getId() == id && "comidaBase".equals(e.getTipoElemento())){
                comidaBuscada = e;
                break;
            }
        }
        
        if (comidaBuscada == null){
            JOptionPane.showMessageDialog(panelTablero, 
            "Comida ingresada con el id -> " + id + "NO existe", 
            "Actualizar comida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int xFinal = x - 1;
        int yFinal = y - 1;
        
        if(panelTablero.celdaOcupada(xFinal, yFinal, comidaBuscada)){
            JOptionPane.showMessageDialog(panelTablero,
            "Celda ocupada, elige otras coordenadas",
            "Actualizar comida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        panelTablero.colocarElementoCelda(comidaBuscada, xFinal, yFinal);
        panelTablero.repaint();
    }
    
    public ElementoBase obtenerElementoBase(){
        for(ElementoBase e: elementos){
            if("comidaBase".equals(e.getTipoElemento()) && e.getId() == 0){
                return e;
            }
        }
        return null;
    }
    
        
    public void iniciarJuego(){
        if (serpiente == null){
            JOptionPane.showMessageDialog(panelTablero,
                "Antes aplastar 'Iniciar objetos'",
                "Iniciar Juego", JOptionPane.WARNING_MESSAGE);
            return;
        }
        juegoIniciado = true;
        panelTablero.requestFocusInWindow();
    }
    
    public void moverSerpiente(Direccion dir) {
        if (!juegoIniciado) {
            JOptionPane.showMessageDialog(panelTablero,
                    "Pulsa 'INICIAR JUEGO' para comenzar.",
                    "Mover Serpiente", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (serpiente == null) {
            JOptionPane.showMessageDialog(panelTablero,
                    "Pulsar el boton Iniciar Objetos para crear la serpiente y sus comidas",
                    "Mover Serpiente", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //mover
        serpiente.moverSerpiente(panelTablero, dir);

        //si cabeza sobre una comida
        int cx = serpiente.getCabezaX();
        int cy = serpiente.getCabezaY();
        
        ElementoBase comidaEnCelda = panelTablero.getElementoEnCelda(cx, cy, "comidaBase");
        if (comidaEnCelda != null) {
            //elimina comida del panel
            panelTablero.eliminarElemento(comidaEnCelda);

            //Clona segmento base y agrega al final
            Segmento segmentoClonado = (Segmento) RepositorioElementoBase.getElementoBaseClonado("segmentoBase");
            if (segmentoClonado != null) {
                elementos.add(segmentoClonado);
                serpiente.agregarSegmento(panelTablero, segmentoClonado);
            }
        }

        panelTablero.repaint();
    }
    
}
