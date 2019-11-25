package py.com.econtreras.ecommerceadmin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import py.com.econtreras.entity.BugetSolicitude;

public interface BudgetSolicitudeRepository extends CrudRepository<BugetSolicitude, Integer> {

}
