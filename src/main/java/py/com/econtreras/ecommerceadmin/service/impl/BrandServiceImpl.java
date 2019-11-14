package py.com.econtreras.ecommerceadmin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import py.com.econtreras.api.beans.BrandBean;
import py.com.econtreras.ecommerceadmin.converter.BrandConverter;
import py.com.econtreras.ecommerceadmin.repository.BrandRepository;
import py.com.econtreras.ecommerceadmin.service.BrandService;

import java.util.ArrayList;
import java.util.List;


@Service
@Qualifier("brandService")
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandConverter brandConverter;

    @Override
    public BrandBean getById(Integer id) {

        return null;
    }

    @Override
    public List<BrandBean> findAll() {
        List<BrandBean> list = new ArrayList<>();
        brandRepository.findAll().forEach(brand -> {
            list.add(brandConverter.buildBean(brand));
        });

        return list;
    }
}
