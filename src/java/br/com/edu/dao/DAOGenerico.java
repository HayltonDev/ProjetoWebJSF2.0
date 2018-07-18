package br.com.edu.dao;

import br.com.edu.util.Util;
import br.com.modeljpa.jpa.EntityManagerUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

public class DAOGenerico<T> implements Serializable{
    private List<T> listaObjetos;
    private List<T> listaTodosObjetos;
    protected Class classePersistente;
    protected String menssagem = "";
    protected EntityManager em; //vai servir para acessar as configs dos obj do BD
    protected String ordem ="id"; //vai servir para na hora ordenar a classe por ou id, nome, etc
    protected String filtro = "";
    protected Integer maximoObjetos =8; //questão de paginação. Posteriormente irei criar um espaço na tela em que o user pode informar a quantidade de quantos objetos ele quer exibir
    protected Integer posicaoAtual =0; //serve para controlar em que página está, pois se tenho 50 registros e exibe no maximoObjetos 10, serão 5 páginas.
    protected Integer totalObjetos =0; //executar as operações em relação a paginação

    public DAOGenerico() {
        em = EntityManagerUtil.getEntityManager();
    }
    
    
    //método que retorna a consulta páginada
    public List<T> getListaObjetos() {
        String jpql = "from "+ classePersistente.getSimpleName();
        String where = "";
        filtro = filtro.replaceAll("[';-]", "");//essa limpeza aqui é contra ataques de injeção de sql direto pelo browser, sendo que o replaceAll vai pegar aqueles caracteres se o user informar e substituir pela a String vazia
        if(filtro.length() > 0){//se for maior que zero, quer dizer que o usuário informou
            if(ordem.equals("id")){
                try {
                    Integer.parseInt(filtro); //se realizar o parse, é pq o usuário realmente informou um inteiro
                    where += " where  " + ordem + " = '" + filtro +"' ";
                } catch (Exception e) {}
            }else{
                where += " where upper("+ ordem + ") like '%"+ filtro.toUpperCase() + "%' ";
            }
        }
        jpql +=where;
        jpql += " order by "+ordem;
        totalObjetos = em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoObjetos).getResultList(); //posição atual é qual a página atual, e maximo objeto a quantidade objeto a partir daquela página que irão ser retornados.
    }
    
    //método que altera a páginação, isso é para o atributo inteiro que vai ser utilizad na consulta do métopdo getListaObjetos
    
    public void paginaPrimeira(){
        posicaoAtual = 0;
    }
    
    public void paginaAnterior(){
        posicaoAtual -= maximoObjetos;
        if(posicaoAtual < 0){ //teste para evitar que a posição da página fica negativa caso a atula posição já seja a primeira
            posicaoAtual =0;
        }
    }
    
    public void paginaProxima(){
        if((posicaoAtual + maximoObjetos) < totalObjetos ){
            posicaoAtual +=maximoObjetos;
        }
    }
    
    public void paginaUltima(){
        //pode correr com que a divisão de objetos não dê uma divisão exata, por isso eu faço os testes abaixo
        int resto = totalObjetos % maximoObjetos; 
        if(resto > 0){
            posicaoAtual = totalObjetos - resto;
        }else{
            posicaoAtual = totalObjetos - maximoObjetos; 
        }
    }
    
    // metodo abaixo. é interessante o usuário saber em que parte da página o usuário está navegando
    public String getMensagemNavegacao(){
        int ate = posicaoAtual + maximoObjetos;
        if(ate > totalObjetos){
            ate = totalObjetos;
           
        }
         return "Listando de " + (posicaoAtual + 1)+ " até " + ate + " de "+ totalObjetos + " registros";
    }
    
    public void roolback(){
        if(em.getTransaction().isActive() ==false){
            em.getTransaction().begin();
        }   
        em.getTransaction().rollback();
    }
    
    public boolean persistGenerico(T obj){
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            menssagem = obj.getClass().getSimpleName() + " salvo(a) com sucesso!";
            return true;
        } catch (Exception e) {
            roolback();
            menssagem = "Erro ao salvar o(a) "+ obj.getClass().getSimpleName()+", sendo o erro "+  Util.getMensagemErro(e); //assim eu coloco o nome da classe e ainda utilizo o util para trazer até o ultimo nivel qual é realmente o erro
            return false;
        }
    }
    
    
    public boolean mergeGenerico(T obj){
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            menssagem = obj.getClass().getSimpleName() + " salvo(a) com sucesso!";
            return true;
        } catch (Exception e) {
            roolback();
            menssagem = "Erro ao salvar o(a) "+ obj.getClass().getSimpleName()+", sendo o erro "+  Util.getMensagemErro(e); //assim eu coloco o nome da classe e ainda utilizo o util para trazer até o ultimo nivel qual é realmente o erro
            return false;
        }
    }
    
    public boolean removeGenerico(T obj){
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            menssagem = obj.getClass().getSimpleName() + " removido com sucesso!";
            return true;
        } catch (Exception e) {
            roolback();
            menssagem = "Erro ao remover o(a) "+ obj.getClass().getSimpleName()+", sendo o erro "+  Util.getMensagemErro(e); //assim eu coloco o nome da classe e ainda utilizo o util para trazer até o ultimo nivel qual é realmente o erro
            return false;
        }
    }
    
    public T localizarGenerico(Integer id){
        roolback(); //faço o rollback pq pode acontecer que a página matenha algo na sessão de forma erra com o que está no banco. Com o rollback eu limpo a sessão e recupera o obj do BD 
        T obj = (T) em.find(classePersistente, id);
        return obj;
    }

    public void setListaObjetos(List<T> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public List<T> getListaTodosObjetos() {
        String jpql = "from "+ classePersistente.getSimpleName() + " order by "+ ordem; //assim eu pego o nome da classse e faço a consulta
        return em.createQuery(jpql).getResultList();
    }

    public void setListaTodosObjetos(List<T> listaTodosObjetos) {
        this.listaTodosObjetos = listaTodosObjetos;
    }

    public Class getClassePersistente() {
        return classePersistente;
    }

    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }

    public String getMenssagem() {
        return menssagem;
    }

    public void setMenssagem(String menssagem) {
        this.menssagem = menssagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Integer getMaximoObjetos() {
        return maximoObjetos;
    }

    public void setMaximoObjetos(Integer maximoObjetos) {
        this.maximoObjetos = maximoObjetos;
    }

    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Integer posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public Integer getTotalObjetos() {
        return totalObjetos;
    }

    public void setTotalObjetos(Integer totalObjetos) {
        this.totalObjetos = totalObjetos;
    }
    
    
}
