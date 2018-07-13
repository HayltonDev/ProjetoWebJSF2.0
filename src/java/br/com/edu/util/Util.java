
package br.com.edu.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Haylton
 */
public class Util {
    
    public static String getMensagemErro(Exception e){
        while(e.getCause() !=null){ //com esse laço eu estou acessando o nível mais baixo de excessão, pois uma cessão pode ter outras encapsuladas nela.
            e = (Exception) e.getCause();
        }
        String retorno = e.getMessage();
        if(retorno.contains("Viola restrição da chave estrangeira")){
            retorno = "Registro não pode ser exluído por possuir referencia no sistema";
        }
        
        return retorno;
    }
    
    public static void mensagemInformacao(String msg){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true); //as mensagens se perdem depois de um redirecionamento e se quero manter as mensagens mesmo depois de redirecionar as páginas
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
        facesContext.addMessage(null, mensagem);
    }
    
    public static void mensagemErro(String msg){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true); //as mensagens se perdem depois de um redirecionamento e se quero manter as mensagens mesmo depois de redirecionar as páginas
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
        facesContext.addMessage(null, mensagem);
    }
}
