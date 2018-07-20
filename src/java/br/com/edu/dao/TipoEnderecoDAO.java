package br.com.edu.dao;

import br.com.modeljpa.modelo.TipoEndereco;
import java.io.Serializable;

/**
 *
 * @author Haylton
 */
public class TipoEnderecoDAO<T> extends DAOGenerico<TipoEndereco> implements Serializable{

    public TipoEnderecoDAO() {
        super();
        classePersistente = TipoEndereco.class;
        ordem = "descricao";
    }
    
    
    
}
