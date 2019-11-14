package py.com.econtreras.ecommerceadmin.repository;

import org.springframework.data.repository.CrudRepository;
import py.com.econtreras.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
