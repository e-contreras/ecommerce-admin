package py.com.econtreras.ecommerceadmin.service;

import py.com.econtreras.api.beans.ProductRequest;

import java.util.List;

public interface ProductService {

    ProductRequest save(ProductRequest productRequest);

    List<ProductRequest> findAll();

}
