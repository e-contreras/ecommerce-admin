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
@RequestMapping(RequestConstant.SALES_BOOK)
@PreAuthorize("hasAuthority('compras')")
public class SalesBookController {
    
    @GetMapping
    public ModelAndView getBookSalesView(){
        ModelAndView mav = new ModelAndView("general");
        mav.addObject("piece", RequestConstant.SALES_BOOK_MAIN_PATH_RESOURCE);
        return mav;
    }
}
