package br.com.edu.controle;

import br.com.edu.dao.CategoriaDAO;
import br.com.edu.dao.PaisDAO;
import br.com.edu.util.Util;
import br.com.modeljpa.modelo.Categoria;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Haylton
 */
@ManagedBean(name = "controleCategoria")
@SessionScoped
public class ControleCategoria implements Serializable{
    private CategoriaDAO<Categoria> DAO;//esse primeiro serve para interação com o banco de dados
    private Categoria categoria; //esse vai servir para receber a instancia que vou estar editando ou inserindo 

    public ControleCategoria() {
        DAO = new CategoriaDAO<>(); // no construtor de qualquer classe controle @Managedbean, é bom iniciar no construtor os objetos do tipo DAO
    }
    
    public String listar(){
        return "/privado/categoria/listar?faces-redirect=true";
    }
    
    public String novo(){
        categoria = new Categoria();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        boolean persistiu = false;
        if(categoria.getId() ==null){
            persistiu = DAO.persistGenerico(categoria);
        }else{
            persistiu = DAO.mergeGenerico(categoria);
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
        categoria = DAO.localizarGenerico(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        categoria = DAO.localizarGenerico(id);
        if(DAO.removeGenerico(categoria)){
            Util.mensagemInformacao(DAO.getMenssagem());
        }else{
            Util.mensagemErro(DAO.getMenssagem());
        }
    }

    public CategoriaDAO<Categoria> getDAO() {
        return DAO;
    }

    public void setDAO(CategoriaDAO<Categoria> DAO) {
        this.DAO = DAO;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
    
}
