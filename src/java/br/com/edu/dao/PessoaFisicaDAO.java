/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.edu.dao;

import br.com.modeljpa.modelo.Permissao;
import br.com.modeljpa.modelo.PessoaFisica;
import java.io.Serializable;
import javax.persistence.Query;

/**
 *
 * @author Haylton
 */
public class PessoaFisicaDAO<T> extends DAOGenerico<PessoaFisica> implements Serializable {

    public PessoaFisicaDAO() {
        super();
        classePersistente = PessoaFisica.class;
        ordem = "nome";
    }
    
    public boolean login(String usuario, String senha){
        Query query = em.createQuery("from PessoaFisica where upper(nomeUsuario) = :usuario and "
                + " upper(senha) = :senha");
        query.setParameter("usuario", usuario.toUpperCase());
        query.setParameter("senha", senha.toUpperCase());
        if (!query.getResultList().isEmpty()){
            return true;
        } else {
            return false;
        }        
    }
    
    public PessoaFisica localizaPorNomeUsuario(String usuario){
        Query query = em.createQuery("from PessoaFisica where upper(nomeUsuario) = :usuario");
        query.setParameter("usuario", usuario.toUpperCase());
        return (PessoaFisica) query.getSingleResult();
    }
    
    public Permissao temPermissao(){
        
        Permissao permissao = em.getReference(Permissao.class, "Administrador");
        return permissao;
    }
}
