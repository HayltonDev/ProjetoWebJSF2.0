package br.com.edu.converters;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.TipoEndereco;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Haylton
 */
@FacesConverter(value = "converterTipoEndereco")
public class ConverterTipoEndereco implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")) {
            return null;
        } else {
            return EntityManagerUtil.getEntityManager().find(TipoEndereco.class, Integer.parseInt(string));
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return null;
        } else { //se não for nulo, vou extrair o TipoEndereco via casting que veio por parÂmetro

            TipoEndereco obj = (TipoEndereco) o;
            return obj.getId().toString();

        }
    }
}
