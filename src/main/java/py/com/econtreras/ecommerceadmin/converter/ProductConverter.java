package py.com.econtreras.ecommerceadmin.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import py.com.econtreras.api.beans.ProductBean;
import py.com.econtreras.api.beans.ProductRequest;
import py.com.econtreras.entity.Brand;
import py.com.econtreras.entity.Category;
import py.com.econtreras.entity.Product;
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
        bean.setCode(entity.getCode());
        bean.setProductName(entity.getProductName());
        bean.setDescription(entity.getDescription());
        bean.setModel(entity.getModel());

        Brand brand = entity.getBrand();
        if(brand != null){
            bean.setBrand(brandConverter.buildBean(entity.getBrand()));
            bean.setBrandId(brand.getId());
        }

        Category category = entity.getCategory();
        if(category != null){
            bean.setCategory(categoryConverter.buildBean(entity.getCategory()));
            bean.setCategoryId(brand.getId());
        }

        return bean;
    }

    public Product buildEntity(ProductBean bean){
        Product entity = new Product();
        entity.setId(bean.getId());
        entity.setProductName(bean.getProductName());
        entity.setCode(bean.getCode());
        entity.setCategory(bean.getCategory() != null ? categoryConverter.buildEntity(bean.getCategory()) : null);
        entity.setBrand(bean.getBrand() != null ? brandConverter.buildEntity(bean.getBrand()) : null);
        entity.setCode(bean.getCode());
        entity.setDescription(bean.getDescription());
        entity.setModel(bean.getModel());
        return entity;
    }

}
