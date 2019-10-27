package py.com.econtreras.ecommerceadmin.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import py.com.econtreras.ecommerceadmin.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
    @Modifying
    @Query("update User u set u.active = '0' where u.id = :id")
    void blockUserById(@Param("id") Integer id);

    @Modifying
    @Query("update User u set u.loginFailed = :count where u.id = :id")
    void setTryLoginCount(@Param("id") Integer id, Integer count);
}
