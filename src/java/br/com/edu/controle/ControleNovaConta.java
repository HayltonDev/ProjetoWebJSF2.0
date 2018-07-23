/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.edu.controle;

import br.com.modeljpa.modelo.Permissao;
import br.com.modeljpa.modelo.PessoaFisica;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author Haylton
 */
@ManagedBean(name = "controleNovaConta")
@ViewScoped
public class ControleNovaConta extends ControlePessoaFisica implements Serializable {

    private PessoaFisica pf;

    public ControleNovaConta() {
        pf = new PessoaFisica();
    }

    public String criarConta() {
        return "/criar_conta?faces-redirect=true";
    }

    public String salvarConta() {
        salvar(pf);
        pf = null;
        return "/login.xhtml?faces-redirect=true";
    }

    public PessoaFisica getPf() {
        return pf;
    }

    public void setPf(PessoaFisica pf) {
        this.pf = pf;
    }

}
