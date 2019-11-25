package py.com.econtreras.ecommerceadmin.converter;

import org.springframework.stereotype.Component;
import py.com.econtreras.api.beans.BudgetSolicitudeStatusBean;
import py.com.econtreras.entity.BudgetSolicitudeStatus;

@Component
public class BudgetSolicitudeStatusConverter {

    public BudgetSolicitudeStatus build(BudgetSolicitudeStatusBean bean){
        BudgetSolicitudeStatus entity = new BudgetSolicitudeStatus();
        entity.setId(bean.getId());
        entity.setStatus(bean.getStatus());
        entity.setErased(bean.isDeleted());
        return  entity;
    }

    public BudgetSolicitudeStatusBean build(BudgetSolicitudeStatus entity){
        BudgetSolicitudeStatusBean bean = new BudgetSolicitudeStatusBean();
        bean.setId(entity.getId());
        bean.setStatus(entity.getStatus());
        bean.setDeleted(entity.isErased());
        return bean;
    }

}
