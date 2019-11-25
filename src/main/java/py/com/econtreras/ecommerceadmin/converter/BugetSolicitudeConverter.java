package py.com.econtreras.ecommerceadmin.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import py.com.econtreras.api.beans.BugetSolicitudeModel;
import py.com.econtreras.entity.BugetSolicitude;

@Component
public class BugetSolicitudeConverter {

    @Autowired
    private BudgetSolicitudeStatusConverter budgetSolicitudeStatusConverter;

    public BugetSolicitudeModel build(BugetSolicitude entity){
        BugetSolicitudeModel model = new BugetSolicitudeModel();
        model.setId(entity.getId());
        model.setDescription(entity.getDescription());
        model.setEndDate(entity.getEndDate());
        model.setInitDate(entity.getInitDate());
        model.setState(entity.getBudgetSolicitudeStatus() != null ? budgetSolicitudeStatusConverter.build(entity.getBudgetSolicitudeStatus()).getStatus() : null);
        return model;
    }


    public BugetSolicitude build(BugetSolicitudeModel model){
        BugetSolicitude entity = new BugetSolicitude();
        entity.setId(model.getId());
        entity.setDescription(model.getDescription());
        entity.setEndDate(model.getEndDate());
        entity.setInitDate(model.getInitDate());
        entity.setStatus(model.getState());
        return entity;
    }





}
