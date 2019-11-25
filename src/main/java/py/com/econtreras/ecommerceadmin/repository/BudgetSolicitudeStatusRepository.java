package py.com.econtreras.ecommerceadmin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import py.com.econtreras.entity.BudgetSolicitudeStatus;

public interface BudgetSolicitudeStatusRepository extends CrudRepository<BudgetSolicitudeStatus, Integer> {

    BudgetSolicitudeStatus findByStatus(@Param("statud") String status);

}
