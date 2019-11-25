package py.com.econtreras.ecommerceadmin.service;

import py.com.econtreras.api.beans.BugetSolicitudeModel;
import py.com.econtreras.entity.BugetSolicitude;

import java.util.List;

public interface BugetSolicitudeService {

    BugetSolicitudeModel save(BugetSolicitudeModel bugetSolicitude);

    BugetSolicitudeModel findOne(Integer id);

    void delete(Integer id);

    List<BugetSolicitudeModel> findAll();


}
