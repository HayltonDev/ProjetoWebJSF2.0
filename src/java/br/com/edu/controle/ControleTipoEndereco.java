/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.edu.controle;

import br.com.edu.dao.TipoEnderecoDAO;
import br.com.edu.util.Util;
import br.com.modeljpa.modelo.TipoEndereco;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Haylton
 */
@ManagedBean(name = "controleTipoEndereco")
@ViewScoped
public class ControleTipoEndereco implements Serializable{
    private TipoEnderecoDAO<TipoEndereco> dao;
    private TipoEndereco tipoEndereco;

    public ControleTipoEndereco() {
        dao = new TipoEnderecoDAO<>();
    }
    
    public String listar(){
        return "/privado/tipoendereco/listar.xhtml";
    }
    
    public void novo(){
        tipoEndereco = new TipoEndereco();
    }
    
    public void salvar(){
         boolean persistiu = false;
         if(tipoEndereco.getId() == null){
             persistiu = dao.persist(tipoEndereco);
         }else{
             persistiu = dao.merge(tipoEndereco);
         }
         if(persistiu){
             Util.mensagemInformacao(dao.getMensagem());
         }else{
             Util.mensagemErro(dao.getMensagem());
         }
         
    }
    public void editar(Integer id){
       tipoEndereco = dao.localizar(id);
    }
    
    public void remover(Integer id){
        tipoEndereco = dao.localizar(id);
        
        if(dao.remove(tipoEndereco)){
            Util.mensagemInformacao(dao.getMensagem());
        }else{
            Util.mensagemErro(dao.getMensagem());
        }
        
    }

    public TipoEnderecoDAO<TipoEndereco> getDao() {
        return dao;
    }

    public void setDao(TipoEnderecoDAO<TipoEndereco> dao) {
        this.dao = dao;
    }

    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }
    
    
    
    
}
