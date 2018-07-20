package br.com.edu.controle;

import br.com.edu.dao.DAOGenerico;
import br.com.edu.dao.MarcaDAO;
import br.com.edu.util.Util;
import br.com.modeljpa.modelo.Marca;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Haylton
 */
@ManagedBean(name = "controleMarca")
@SessionScoped
public class ControleMarca extends DAOGenerico<Marca> implements Serializable{
    private MarcaDAO<Marca> dao;
    private Marca marca;
    
    public ControleMarca(){
        dao = new MarcaDAO<>();
    }
    
    public String listar(){
        return "/privado/marca/listar?faces-redirect=true";
    }
    
    public String novo(){
        marca = new Marca();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        boolean persistiu = false;
        if(marca.getId() == null){
            persistiu = dao.persist(marca);
        }else{
            persistiu = dao.merge(marca);
        }
        if(persistiu){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        }else{
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
        
    }
    
    public String cancelarEdicao(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        marca = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        marca = dao.localizar(id);
        if(dao.remove(marca)){
            Util.mensagemInformacao(dao.getMensagem());
        }else{
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public MarcaDAO<Marca> getDao() {
        return dao;
    }

    public void setDao(MarcaDAO<Marca> dao) {
        this.dao = dao;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    
    
    
    
}
