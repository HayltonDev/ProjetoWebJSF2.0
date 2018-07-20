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
@SessionScoped
public class ControlePais implements Serializable {
    
    private PaisDAO<Pais> dao;
    private Pais objeto;
    
    public ControlePais(){
        dao = new PaisDAO<>();
    }
    
    public String listar(){
        return "/privado/pais/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Pais();
        return "formulario?faces-redirect=true";        
    }
    
    public String salvar(){
        boolean persistiu = false;
        if (objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remove(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public PaisDAO getDao() {
        return dao;
    }

    public void setDao(PaisDAO dao) {
        this.dao = dao;
    }

    public Pais getObjeto() {
        return objeto;
    }

    public void setObjeto(Pais objeto) {
        this.objeto = objeto;
    }    
}
