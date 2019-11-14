package py.com.econtreras.ecommerceadmin.converter;

import org.springframework.stereotype.Component;
import py.com.econtreras.ecommerceadmin.entity.Ringing;
import py.com.econtreras.ecommerceadmin.model.RingingModel;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class RingingConverter {

    public Ringing build(RingingModel model){
        Ringing entity = new Ringing();
        entity.setId(model.getId());
        entity.setDocumentType(model.getDocumentType());
        entity.setRinging(model.getRinging());
        entity.setDeleted(model.isDeleted());
//        entity.setValidityDate(Date.from(model.getValidityDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        entity.setValidityDate(model.getValidityDate());
        return entity;
    }


    public RingingModel build(Ringing entity){
        RingingModel model = new RingingModel();
        model.setId(entity.getId());
        model.setDocumentType(entity.getDocumentType());
        model.setRinging(entity.getRinging());
        model.setDeleted(entity.isDeleted());
        //model.setValidityDate(entity.getValidityDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        model.setValidityDate(entity.getValidityDate());
        return model;
    }

}
