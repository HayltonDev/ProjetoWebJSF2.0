package br.com.edu.controle;

import br.com.edu.dao.PessoaFisicaDAO;
import br.com.edu.util.Util;
import br.com.modeljpa.modelo.Permissao;
import br.com.modeljpa.modelo.PessoaFisica;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Haylton
 */
@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable{

    private PessoaFisicaDAO<PessoaFisica> dao;
    private PessoaFisica usuarioLogado;
    private String usuario;
    private String senha;
    
    public ControleLogin(){
        dao = new PessoaFisicaDAO<>();
    }
    
    public String paginaLogin(){
        return "/login?faces-redirect=true";
    }
    
    public String efetuarLogin(){
        if(dao.login(usuario, senha)){
            usuarioLogado = dao.localizaPorNomeUsuario(usuario);
            Util.mensagemInformacao("Login realizado com sucesso!");
            usuario = "";
            senha = "";
            return "/index?faces-redirect=true";
        } else {
            Util.mensagemErro("Usuário ou senha inválidos!");
            return "/login?faces-redirect=true";
        }
    }
    
    public String efetuarLogout(){
        usuarioLogado = null;
        Util.mensagemInformacao("Logout realizado com sucesso!");
        return "/index?faces-redirect=true";
    }
    
     public boolean isAdministrador(){
        String perfil = dao.temPermissao().getNome();
        return usuarioLogado.getPermissoes().get(0).getNome().equals(perfil);
      

    }

    public PessoaFisicaDAO<PessoaFisica> getDao() {
        return dao;
    }

    public void setDao(PessoaFisicaDAO<PessoaFisica> dao) {
        this.dao = dao;
    }

    public PessoaFisica getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(PessoaFisica usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
