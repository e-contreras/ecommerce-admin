package py.com.econtreras.ecommerceadmin.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import py.com.econtreras.api.beans.ProductRequest;
import py.com.econtreras.ecommerceadmin.entity.Product;
import py.com.econtreras.ecommerceadmin.repository.BrandRepository;

@Component
public class ProductConverter {

    public ProductRequest build(Product entity){
        ProductRequest request = new ProductRequest();
        request.setBarCode(entity.getCode());
        return request;
    }

    public Product build(ProductRequest bean){
        Product entity = new Product();
        entity.setId(bean.getId());
        entity.setProductName(bean.getProductName());
        entity.setCode(bean.getCode());

        return entity;
    }

}
