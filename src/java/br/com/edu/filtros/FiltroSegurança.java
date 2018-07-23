package br.com.edu.filtros;


import br.com.edu.controle.ControleLogin;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Haylton
 */
//@WebFilter(urlPatterns = "/privado/*")// o que eu quero conttrolar aqui! Não deixar acessar o diretório privado caso não esteja logado. Entaõ todo o conteúdo dentro do privado, vai passar antes por esse filtro
public class FiltroSegurança implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //não precisa implementar nada aqui
    }

    @Override //esse método captura a requisição e gera a resposta
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request; //tenho que extrair o que vem de servletRequest
        HttpServletResponse httpResponse = (HttpServletResponse) response;//a mesma coisa
        //agora quero obter a sessão
        HttpSession sessao = httpRequest.getSession();
        String contextPath = httpRequest.getContextPath();
        ControleLogin controleLogin = (ControleLogin) sessao.getAttribute("controleLogin"); //esse getAttribute retorna um objeto e por isso tem que fazer um cast
        //agora só ovu verificar se no controle login eu tenho usuario logado
        if(controleLogin==null || controleLogin.getUsuarioLogado() == null){
            httpResponse.sendRedirect(contextPath + "/login.xhtml");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
       //não precisa implementar nada aqui
    }
    
}
