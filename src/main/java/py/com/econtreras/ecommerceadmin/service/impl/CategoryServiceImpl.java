package py.com.econtreras.ecommerceadmin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.econtreras.api.beans.CategoryBean;
import py.com.econtreras.ecommerceadmin.converter.CategoryConverter;
import py.com.econtreras.ecommerceadmin.repository.CategoryRepository;
import py.com.econtreras.ecommerceadmin.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public List<CategoryBean> findAll() {
        List<CategoryBean> list = new ArrayList<>();
        categoryRepository.findAll().forEach(category -> {
            list.add(categoryConverter.buildBean(category));
        });
        return list;
    }
}
