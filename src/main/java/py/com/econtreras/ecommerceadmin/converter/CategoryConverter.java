package py.com.econtreras.ecommerceadmin.converter;

import org.springframework.stereotype.Component;
import py.com.econtreras.api.beans.CategoryBean;
import py.com.econtreras.ecommerceadmin.entity.Category;

@Component
public class CategoryConverter {

    public Category buildEntity(CategoryBean bean){
        Category entity = new Category();
        entity.setId(bean.getId());
        entity.setDescription(bean.getDescription());
        return entity;
    }


    public CategoryBean buildBean(Category entity){
        CategoryBean bean = new CategoryBean();
        bean.setId(entity.getId());
        bean.setDescription(entity.getDescription());
        return bean;
    }
}
