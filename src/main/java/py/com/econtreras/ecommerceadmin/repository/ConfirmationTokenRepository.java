package py.com.econtreras.ecommerceadmin.repository;

import org.springframework.data.repository.CrudRepository;
import py.com.econtreras.ecommerceadmin.entity.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {

    ConfirmationToken findByConfirmationToken(String confirmationToken);

}
