/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.edu.controle;

import br.com.edu.dao.EstadoDAO;
import br.com.edu.dao.PaisDAO;
import br.com.edu.util.Util;
import br.com.modeljpa.modelo.Estado;
import br.com.modeljpa.modelo.Pais;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Haylton
 */
@ManagedBean(name = "controleEstado")
@ViewScoped
public class ControleEstado implements Serializable {
    
    private EstadoDAO<Estado> dao;
    private Estado objeto;
    private PaisDAO<Pais> daoPais;
    
    public ControleEstado(){
        dao = new EstadoDAO<>();
        daoPais = new PaisDAO<>();
    }
    
    public String listar(){
        return "/privado/estado/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Estado();        
    }
    
    public void salvar(){
        boolean persistiu = false;
        if (objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            Util.mensagemInformacao(dao.getMensagem());            
        } else {
            Util.mensagemErro(dao.getMensagem());            
        }
    }
    
    public void editar(Integer id){
        objeto = dao.localizar(id);        
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remove(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public EstadoDAO getDao() {
        return dao;
    }

    public void setDao(EstadoDAO dao) {
        this.dao = dao;
    }

    public Estado getObjeto() {
        return objeto;
    }

    public void setObjeto(Estado objeto) {
        this.objeto = objeto;
    }    

    public PaisDAO<Pais> getDaoPais() {
        return daoPais;
    }

    public void setDaoPais(PaisDAO<Pais> daoPais) {
        this.daoPais = daoPais;
    }
}
