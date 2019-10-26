package py.com.econtreras.ecommerceadmin.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import py.com.econtreras.api.beans.UserBean;
import py.com.econtreras.ecommerceadmin.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Value("${userTryLoginLimit}")
    private String userTryLoginLimit;

    private static final String USER_BLOCK = "Usuario bloqueado";
    private static final String INVALID_USER_OR_PASSWORD = "Usuario o contraseña inválida";

    @Autowired
    @Qualifier("userService")
    private UserServiceImpl userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if(exception instanceof BadCredentialsException) {
            String  username = request.getParameter("username");
            if(username != null){
                UserBean user = userService.findByUserName(username);
                Integer actualUserLoginFailed = user.getLoginFailed() +1;
                if(actualUserLoginFailed >= Integer.parseInt(userTryLoginLimit)){
                    if(user.getEnabled().equals(new Short("1")))
                        userService.blockUser(user.getId());
                    response.sendRedirect(request.getContextPath() + String.format("/login?error=%s", USER_BLOCK));
                }
            }
        } else if(exception instanceof DisabledException) {
            response.sendRedirect(request.getContextPath() + String.format("/login?error=%s", USER_BLOCK));

        } else {
            response.sendRedirect(request.getContextPath() + String.format("/login?error=%s", INVALID_USER_OR_PASSWORD));
        }
    }
}
