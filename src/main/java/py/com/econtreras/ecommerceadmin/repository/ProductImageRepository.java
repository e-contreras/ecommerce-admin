package py.com.econtreras.ecommerceadmin.repository;

import org.springframework.data.repository.CrudRepository;
import py.com.econtreras.entity.ProductImage;
import py.com.econtreras.entity.ProductImagePK;

public interface ProductImageRepository extends CrudRepository<ProductImage, ProductImagePK> {
}
