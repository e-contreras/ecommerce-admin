package py.com.econtreras.ecommerceadmin.repository;

import org.springframework.data.repository.CrudRepository;
import py.com.econtreras.entity.WorkOrder;

public interface WorkOrderRepository extends CrudRepository<WorkOrder, Integer> {
}
