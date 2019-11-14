package py.com.econtreras.ecommerceadmin.repository;

import org.springframework.data.repository.CrudRepository;
import py.com.econtreras.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
