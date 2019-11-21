package py.com.econtreras.ecommerceadmin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import py.com.econtreras.api.beans.ProductBean;
import py.com.econtreras.ecommerceadmin.util.RequestConstant;

/**
 * @author
 */
@Controller
@RequestMapping(RequestConstant.PURCHASE_PROVAIDER)
@PreAuthorize("hasAuthority('compras')")
public class ProviderController {
    
    @GetMapping
    public ModelAndView getProducts(){
        ModelAndView mav = new ModelAndView("general");
        mav.addObject("piece", RequestConstant.PROVAIDER_MAIN_PATH_RESOURCE);
        return mav;
    }
    
    @GetMapping(RequestConstant.PURCHASE_PROVAIDER_FORM_NEW)
    public ModelAndView getFormViewProduct(){
        ModelAndView mav = new ModelAndView("general");
        mav.addObject("piece", RequestConstant.PURCHASE_PROVAIDER_FORM_PATH_RESOURCE);
        return mav;
    }
}
