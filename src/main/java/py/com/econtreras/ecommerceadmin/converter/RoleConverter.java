package py.com.econtreras.ecommerceadmin.converter;

import org.springframework.stereotype.Component;
import py.com.econtreras.api.beans.RoleBean;
import py.com.econtreras.entity.Role;

@Component
public class RoleConverter {

    public RoleBean build(Role entity) {
        RoleBean bean = new RoleBean();
        bean.setId(entity.getId());
        bean.setRol(entity.getDescription());
        return bean;
    }

}
