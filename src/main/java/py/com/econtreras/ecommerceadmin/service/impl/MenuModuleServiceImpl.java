package py.com.econtreras.ecommerceadmin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.econtreras.api.beans.MenuSection;
import py.com.econtreras.ecommerceadmin.converter.MenuModuloConverter;
import py.com.econtreras.entity.MenuModule;
import py.com.econtreras.ecommerceadmin.repository.MenuModuleRepository;
import py.com.econtreras.ecommerceadmin.service.MenuModuleSerivice;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuModuleServiceImpl implements MenuModuleSerivice {

    @Autowired
    private MenuModuleRepository repository;
    @Autowired
    private MenuModuloConverter menuModuloConverter;

    @Override
    public List<MenuSection> findAll() {

        List<MenuSection> menuSectionList = new ArrayList<>();
        List<MenuModule> menuRepository = (List<MenuModule>) repository.findAll();
        menuRepository.forEach(menuModule -> {
            menuSectionList.add(menuModuloConverter.buildBean(menuModule));
        });

        return menuSectionList;
    }
}
