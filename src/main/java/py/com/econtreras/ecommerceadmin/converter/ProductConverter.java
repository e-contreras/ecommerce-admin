package py.com.econtreras.ecommerceadmin.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import py.com.econtreras.api.beans.ProductBean;
import py.com.econtreras.api.beans.ProductRequest;
import py.com.econtreras.ecommerceadmin.entity.Product;
import py.com.econtreras.ecommerceadmin.repository.BrandRepository;

@Component
public class ProductConverter {

    @Autowired
    private BrandConverter brandConverter;
    @Autowired
    private CategoryConverter categoryConverter;


    public ProductBean buildBean(Product entity){
        ProductBean bean= new ProductBean();
        bean.setId(entity.getId());
        bean.setProductName(entity.getProductName());
        bean.setDescription(entity.getDescription());
        bean.setBrand(entity.getBrand() != null ? brandConverter.buildBean(entity.getBrand()) : null);
        bean.setCategory(entity.getCategory() != null ? categoryConverter.buildBean(entity.getCategory()) : null);
        //bean.setFile(entity);
        return bean;
    }

    public Product buildEntity(ProductBean bean){
        Product entity = new Product();
        entity.setId(bean.getId());
        entity.setProductName(bean.getProductName());
        entity.setCode(bean.getCode());
        entity.setCategory(bean.getCategory() != null ? categoryConverter.buildEntity(bean.getCategory()) : null);
        entity.setBrand(bean.getBrand() != null ? brandConverter.buildEntity(bean.getBrand()) : null);
        return entity;
    }

}
