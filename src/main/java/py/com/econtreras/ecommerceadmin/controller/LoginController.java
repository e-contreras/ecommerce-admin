package py.com.econtreras.ecommerceadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Properties;

@Controller
public class LoginController {

    @Autowired
    @Qualifier("message")
    private Properties messages;

    @GetMapping("/login")
    public String showLoginForm(Model model,
                                @RequestParam(name="error", required = false) String error,
                                @RequestParam(name="logout", required = false) String logout) {
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        return "/login";
    }

    @GetMapping({"/", "/loginsuccess"})
    public String loginCheck(){
        return "redirect:/purchase/products";
    }

}
