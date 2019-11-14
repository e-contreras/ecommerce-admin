package py.com.econtreras.ecommerceadmin.converter;

import org.springframework.stereotype.Component;
import py.com.econtreras.api.beans.MenuResponse;
import py.com.econtreras.api.beans.MenuSection;
import py.com.econtreras.entity.Menu;
import py.com.econtreras.entity.MenuModule;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuModuloConverter {

    public MenuSection buildBean(MenuModule entity) {
        MenuSection bean = new MenuSection();
        bean.setName(entity.getName());
        bean.setIcon(entity.getIcon());
        bean.setMenues(this.buildMenu(entity.getMenuList()));
        return bean;
    }

    public List<MenuResponse> buildMenu(List<Menu> menuList) {
        List<MenuResponse> menuResponseList = new ArrayList<>();
        if(menuList != null || !menuList.isEmpty()){

            menuList.forEach(menu -> {
                MenuResponse menuResponse = new MenuResponse();
                menuResponse.setId(menu.getId().toString());
                menuResponse.setName(menu.getName());
                menuResponse.setUrl(menu.getUrl());
                menuResponseList.add(menuResponse);
            });

        }
        return menuResponseList;
    }
}
