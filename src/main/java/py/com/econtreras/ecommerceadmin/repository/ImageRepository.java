package py.com.econtreras.ecommerceadmin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import py.com.econtreras.ecommerceadmin.entity.Image;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Integer> {

    @Query("select i from Image i, ProductImage pi where i.id = pi.merImagenesPK.image and pi.merImagenesPK.product = :productId")
    List<Image> findByProductId(@Param("productId") Integer productId);

    @Query("select i from Image i, ProductImage pi where i.id = pi.merImagenesPK.image and pi.merImagenesPK.product = :productId and i.name = :imageName")
    Image findByProductIAndName(@Param("productId") Integer productId, @Param("imageName") String imageName);


}
