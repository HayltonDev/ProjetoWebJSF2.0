package br.com.edu.converters;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.Pais;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Haylton
 */
@FacesConverter(value = "converterPais")
public class ConverterPais implements Converter, Serializable{
    
    //esse método serve para converter da tela para o objeto. o Primeiro parametro é context sendo qual é o contexto que vocÊ tá chamando
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
       if(string == null || string.equals("Selecione um registro")){
           return null;
       }else{
           return EntityManagerUtil.getEntityManager().getReference(Pais.class, Integer.parseInt(string));
       }
    }

    //esse converte do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o ==null){
            return null;
        }else{//se não for nulo, vou extrair o pais via casting que veio por parÂmetro
            Pais obj = (Pais) o;
            return obj.getId().toString();
        }
    }
    
}
