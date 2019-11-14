package py.com.econtreras.ecommerceadmin.service;

import py.com.econtreras.ecommerceadmin.model.RingingModel;

import java.util.List;

public interface RingningService {

    List<RingingModel> getAll();

    void save(RingingModel model);

    void delete(Integer id);

    RingingModel getById(Integer id);

    boolean checkExistByRingning(String ringing);


}
