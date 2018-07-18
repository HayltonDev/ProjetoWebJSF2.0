package br.com.edu.dao;

import br.com.modeljpa.modelo.Estado;
import java.io.Serializable;

/**
 *
 * @author Haylton
 */
//DAO (Data access Object)uma classe que encapsula o acesso aos dados pela JPA
public class EstadoDAO<T> extends DAOGenerico<Estado> implements Serializable{

    public EstadoDAO() {
        super(); //chamo o construtor de DAOGenerico para inicializar a entityManager EM
        classePersistente = Estado.class; //classePersistence e ordem estão visivel pra mim pq são atributos protected e não preciso usar get ou set pois estão em nível de pacote e EstadoDAO é uma classe filha de DAOGenerico<>
        ordem = "nome"; //será usado para escolha de ordem inicial da consulta para ser montada na page
    }
    
}
