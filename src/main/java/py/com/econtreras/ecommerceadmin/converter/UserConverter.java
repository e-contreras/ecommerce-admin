package py.com.econtreras.ecommerceadmin.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import py.com.econtreras.api.beans.PersonResponse;
import py.com.econtreras.api.beans.UserBean;
import py.com.econtreras.ecommerceadmin.entity.User;

@Component
public class UserConverter {

    @Autowired
    private RoleConverter roleConverter;
    @Autowired
    private PersonConverter personConverter;

    public UserBean build(User entity){
        UserBean bean = new UserBean();
        bean.setId(entity.getId());
        bean.setEnabled(entity.getActive());
        bean.setUsername(entity.getUsername());
        bean.setPassword(entity.getPassword());
        bean.setRol(roleConverter.build(entity.getRole()).getRol());
        PersonResponse p = personConverter.build(entity.getPerson());
        bean.setName(p.getName());
        bean.setLoginFailed(entity.getLoginFailed());
        bean.setLastName(p.getLastname());
        return bean;
    }


}
