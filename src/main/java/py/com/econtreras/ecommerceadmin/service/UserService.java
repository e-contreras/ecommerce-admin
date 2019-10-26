package py.com.econtreras.ecommerceadmin.service;

import py.com.econtreras.api.beans.UserBean;

public interface UserService {

    UserBean findByUserName(String username);

    void blockUser(Integer id);

}
