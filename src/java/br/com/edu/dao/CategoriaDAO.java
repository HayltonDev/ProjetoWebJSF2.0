package br.com.edu.dao;

import br.com.edu.dao.DAOGenerico;
import br.com.modeljpa.modelo.Categoria;
import java.io.Serializable;

/**
 *
 * @author Haylton
 */
//DAO (Data Acess Object) uma classe que encapsula o acesso aos dados pela JPA
public class CategoriaDAO<T> extends DAOGenerico<Categoria> implements Serializable{

    public CategoriaDAO() {
        super(); //chamo a super ( DAOGenerico<Categoria> ) passando a categoria para inicializar a EntityManager
        classePersistente = Categoria.class;  //classePersistence e ordem estão visivel pra mim pq são atributos protected e não preciso usar get ou set pois estão em nível de pacote e MarcaDAO é uma classe filha de DAOGenerico<>
        ordem = "nome";
    }
    
}
