/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.edu.dao;

import br.com.modeljpa.modelo.Produto;
import java.io.Serializable;

/**
 *
 * @author Haylton
 */
public class ProdutoDAO<T> extends DAOGenerico<Produto> implements Serializable{

    public ProdutoDAO() {
        super();
        classePersistente = Produto.class;
        ordem = "nome";
        
    }
    
}
