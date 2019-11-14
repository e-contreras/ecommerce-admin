package py.com.econtreras.ecommerceadmin.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.com.econtreras.api.beans.UserBean;
import py.com.econtreras.ecommerceadmin.converter.UserConverter;
import py.com.econtreras.entity.Role;
import py.com.econtreras.ecommerceadmin.repository.UserRepository;
import py.com.econtreras.ecommerceadmin.service.UserService;

import java.util.*;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        py.com.econtreras.entity.User user = userRepository.findByUsername(username);
        Set<Role> roles = new HashSet<>();
        roles.add(user.getRole());
        List<GrantedAuthority> authorities = buildGrantedAutorities(roles);
        return buildUser(user, authorities);
    }

    private User buildUser(py.com.econtreras.entity.User entity, List<GrantedAuthority> grantedAuthorityList){
        return new User(entity.getUsername(), entity.getPassword(),entity.getActive() > 0, true, true, true,grantedAuthorityList);
    }

    private List<GrantedAuthority> buildGrantedAutorities(Set<Role> roles){
        Set<GrantedAuthority> auths = new HashSet<>();
        for (Role role: roles) {
            auths.add(new SimpleGrantedAuthority(role.getDescription()));

        }
        return new ArrayList<>(auths);
    }

    @Override
    public UserBean findByUserName(String username) {
        py.com.econtreras.entity.User user = userRepository.findByUsername(username);
        return userConverter.build(user);
    }

    @Override
    public void blockUser(Integer id) {
        try {
            userRepository.blockUserById(id);
        } catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void setTryLoginCount(Integer id, Integer count){
        try {
            userRepository.setTryLoginCount(id, count);
        } catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}
