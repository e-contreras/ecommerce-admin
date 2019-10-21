package py.com.econtreras.ecommerceadmin.repository;

import org.springframework.data.repository.CrudRepository;
import py.com.econtreras.ecommerceadmin.entity.ProductImage;
import py.com.econtreras.ecommerceadmin.entity.ProductImagePK;

public interface ProductImageRepository extends CrudRepository<ProductImage, ProductImagePK> {
}
