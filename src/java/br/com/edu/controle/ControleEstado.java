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
@ManagedBean(name = "controleEstado") //a mioria das ações(métodos) tem que ser void para usar o ajax com o viewscoped, pois não navegarei entre telas com ajax, usarei modals
@ViewScoped//as classes controoles @ManagedBean servem para responder os comandos da interface, ou seja, do usuário. O suso de viewscoped é por causa da manutenção do estado irá funcionar com ajax
public class ControleEstado implements Serializable{
    private EstadoDAO<Estado> dao; //esse primeiro serve para interação com o banco de dados
    private Estado estado; //esse vai servir para receber a instancia que vou estar editando ou inserindo 
    private PaisDAO<Pais> paisDAO;//essa instanciação é pq ta tendo um relacionamento ManyToOne, pois são muitos estados para 1 pais

    public ControleEstado() {
        dao = new EstadoDAO<>(); // no construtor de qualquer classe controle @Managedbean, é bom iniciar no construtor os objetos do tipo DAO
        paisDAO = new PaisDAO<>(); //inicializando o pais aqui no construtor, eu consiguirei listar os paises nas telas de Estado
    }
    
    //é o único que vai continuar usando o retorno string para voltar a tela de listar
    public String listar(){
        return "/privado/estado/listar?faces-redirect=true";
    }
    
    public void novo(){
        estado = new Estado();
        
    }
    
    public void salvar(){
        boolean persistiu = false;
        if(estado.getId() == null){
            persistiu = dao.persistGenerico(estado);
        }else{
            persistiu = dao.mergeGenerico(estado);
        }
        
        if(persistiu){
            Util.mensagemInformacao(dao.getMenssagem());
            
        }else{
            Util.mensagemErro(dao.getMenssagem());
           
        }
    }
    
    
    
    public void editar(Integer id){
        estado = dao.localizarGenerico(id);

    }
    
    public void remover(Integer id){
        estado = dao.localizarGenerico(id);
        if(dao.removeGenerico(estado)){
            Util.mensagemInformacao(dao.getMenssagem());
        }else{
            Util.mensagemErro(dao.getMenssagem()); 
        }
    }

    public EstadoDAO getDao() {
        return dao;
    }

    public void setDao(EstadoDAO dao) {
        this.dao = dao;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public PaisDAO<Pais> getPaisDAO() {
        return paisDAO;
    }

    public void setPaisDAO(PaisDAO<Pais> paisDAO) {
        this.paisDAO = paisDAO;
    }
    
    
}
