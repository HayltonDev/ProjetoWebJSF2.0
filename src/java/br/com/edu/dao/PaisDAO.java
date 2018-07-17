package br.com.edu.dao;

import br.com.modeljpa.modelo.Pais;
import java.io.Serializable;

/**
 *
 * @author Haylton
 */
public class PaisDAO<T> extends DAOGenerico<Pais> implements Serializable{

    public PaisDAO() {
        super();
        classePersistente = Pais.class;
        ordem = "nome";
        
    }
      
}
