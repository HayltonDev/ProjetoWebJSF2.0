package br.com.edu.controle;

import br.com.edu.dao.DAOGenerico;
import br.com.modeljpa.modelo.Marca;
import java.io.Serializable;

/**
 *
 * @author Haylton
 */
//DAO (Data access Object)uma classe que encapsula o acesso aos dados pela JPA
public class MarcaDAO extends DAOGenerico<Marca> implements Serializable{

    public MarcaDAO() {
        super(); //chamo o contstrutor do pais (DAOGenerico<Pais>) para inicializar a entityManager
        classePersistente = Marca.class;  //classePersistence e ordem estão visivel pra mim pq são atributos protected e não preciso usar get ou set pois estão em nível de pacote e MarcaDAO é uma classe filha de DAOGenerico<>
        ordem = "nome";
    }
    
    
}
