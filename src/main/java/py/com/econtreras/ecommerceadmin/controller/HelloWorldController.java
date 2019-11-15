package py.com.econtreras.ecommerceadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HelloWorldController {
    
    @GetMapping
    public ModelAndView helloWorld(){
        ModelAndView mav = new ModelAndView("general");
        mav.addObject("piece", "home");
        return mav;
    }
    
}