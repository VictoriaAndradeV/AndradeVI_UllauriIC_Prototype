package prototipo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author victo
 */
public class RepositorioElementoBase {
    
    private static final Map<String, ElementoBase> repositorioElementosBase = new HashMap<>();
    
    public static ElementoBase getElementoBaseClonado(String clave){
        ElementoBase elementoBase = repositorioElementosBase.get(clave);
        if(elementoBase != null){
            return elementoBase.clone();
        }
        return null;
    }
    //nos devuelve el elemento base CREADO 
    public static ElementoBase getElementoBase(String clave){
        return repositorioElementosBase.get(clave);
    }
    
    public static void crearElementoBase(){
        //repositorio que guarda los elementos base
        Comida comidaBase = new Comida();
        comidaBase.setTipoElemento("comidaBase");
        comidaBase.setId(0);
        repositorioElementosBase.put(comidaBase.getTipoElemento(), comidaBase);
        
        Segmento segmentoBase = new Segmento();
        segmentoBase.setTipoElemento("segmentoBase");
        repositorioElementosBase.put(segmentoBase.getTipoElemento(), segmentoBase);
    }
}
