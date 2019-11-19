package py.com.econtreras.ecommerceadmin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import py.com.econtreras.ecommerceadmin.util.RequestConstant;

/**
 * @author
 */
@Controller
@RequestMapping(RequestConstant.SALES_CREDIT_NOTE)
@PreAuthorize("hasAuthority('compras')")
public class CreditSalesController {
    
    @GetMapping
    public ModelAndView getCreditSalesView(){
        ModelAndView mav = new ModelAndView("general");
        mav.addObject("piece", RequestConstant.SALES_CREDIT_NOTE_MAIN_PATH_RESOURCE);
        return mav;
    }
}
