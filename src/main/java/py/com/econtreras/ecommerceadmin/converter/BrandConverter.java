package py.com.econtreras.ecommerceadmin.converter;

import py.com.econtreras.entity.Brand;
import org.springframework.stereotype.Component;
import py.com.econtreras.api.beans.BrandBean;


@Component
public class BrandConverter {

    public BrandBean buildBean(Brand entity){
        BrandBean bean = new BrandBean();
        bean.setId(entity.getId());
        bean.setDescription(entity.getDescription());
        return bean;
    }


    public Brand buildEntity(BrandBean bean){
        Brand entity = new Brand();
        entity.setId(bean.getId());
        entity.setDescription(bean.getDescription());
        return entity;
    }

}
