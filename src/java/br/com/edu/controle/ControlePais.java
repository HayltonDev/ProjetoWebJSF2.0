package br.com.edu.controle;

import br.com.edu.dao.PaisDAO;
import br.com.edu.util.Util;
import br.com.modeljpa.modelo.Pais;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author t1076986
 */
@ManagedBean(name = "controlePais")
public class ControlePais implements Serializable{
    private PaisDAO<Pais> DAO;
    
    private Pais pais;

    public ControlePais() {
        DAO = new PaisDAO<>();
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
        if(pais.getId() ==null){
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
