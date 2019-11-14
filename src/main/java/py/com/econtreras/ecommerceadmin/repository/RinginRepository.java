package py.com.econtreras.ecommerceadmin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import py.com.econtreras.ecommerceadmin.entity.Ringing;

import java.util.Optional;

public interface RinginRepository extends CrudRepository<Ringing, Integer> {

    @Query("UPDATE Ringing r set r.deleted = false where r.id = :id")
    void deleteById(@Param("id") Integer id);

    Optional<Ringing> findByRinging(@Param("ringing")  String ringing);

}
