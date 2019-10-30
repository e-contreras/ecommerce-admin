package py.com.econtreras.ecommerceadmin.service;

import py.com.econtreras.api.beans.ProductBean;
import py.com.econtreras.api.beans.ProductRequest;

import java.util.List;

public interface ProductService {

    ProductBean save(ProductBean productRequest);

    List<ProductBean> findAll();

    ProductBean findById(Integer id);

}
