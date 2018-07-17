package br.com.edu.controle;

import br.com.edu.dao.PaisDAO;
import br.com.edu.util.Util;
import br.com.modeljpa.modelo.Pais;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author t1076986
 */
@ManagedBean(name = "controlePais")
@SessionScoped //as classes controles @ManagedBean servem para responder os comandos da interface, ou seja, do usuário
public class ControlePais implements Serializable{
    private PaisDAO<Pais> DAO;//esse primeiro serve para interação com o banco de dados
    private Pais pais;//esse vai servir para receber a instancia que vou estar editando ou inserindo 

    public ControlePais() {
        DAO = new PaisDAO<>(); // no construtor de qualquer classe controle @Managedbean, é bom iniciar no construtor os objetos do tipo DAO
    }
    
    public String listar(){
        return "/privado/pais/listar?faces-redirect=true";
    }
    
    public String novo(){
        pais = new Pais();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        boolean persistiu = false;
        if(pais.getId() == null){
            persistiu = DAO.persistGenerico(pais);
        }else{
            persistiu = DAO.mergeGenerico(pais);
        }
        
        if(persistiu){
            Util.mensagemInformacao(DAO.getMenssagem());
            return "listar?faces-redirect=true";
        }else{
            Util.mensagemErro(DAO.getMenssagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelarEdicao(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        pais = DAO.localizarGenerico(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        pais = DAO.localizarGenerico(id);
        if(DAO.removeGenerico(pais)){
            Util.mensagemInformacao(DAO.getMenssagem());
        }else{
            Util.mensagemErro(DAO.getMenssagem());
        }
    }
    

    public PaisDAO<Pais> getDAO() {
        return DAO;
    }

    public void setDAO(PaisDAO<Pais> DAO) {
        this.DAO = DAO;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
    
    
}
