package py.com.econtreras.ecommerceadmin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import py.com.econtreras.entity.Product;


public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query("select p from Product  p where p.productName like %:name%")
    Iterable<Product> findByProductName(@Param("name") String name);

}
