package py.com.econtreras.ecommerceadmin.service;

import py.com.econtreras.api.beans.BrandBean;

import java.util.List;

public interface BrandService {

    BrandBean getById(Integer id);
    List<BrandBean> findAll();
}
