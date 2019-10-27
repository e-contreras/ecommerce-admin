package py.com.econtreras.ecommerceadmin.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import py.com.econtreras.api.beans.UserBean;
import py.com.econtreras.ecommerceadmin.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final String HOME_PAGE = "home";

    @Autowired
    @Qualifier("userService")
    private UserServiceImpl userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserBean userBean = userService.findByUserName(authUser.getUsername());
        userService.setTryLoginCount(userBean.getId(), 0);
        response.sendRedirect(request.getContextPath() + HOME_PAGE);
    }
}
