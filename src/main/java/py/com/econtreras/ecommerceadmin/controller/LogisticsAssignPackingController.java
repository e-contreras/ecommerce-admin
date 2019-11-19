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
@RequestMapping(RequestConstant.LOGISTICS_ASSIGN_PACKING)
@PreAuthorize("hasAuthority('compras')")
public class LogisticsAssignPackingController {
    
    @GetMapping
    public ModelAndView getLogisticsAssignPackingView(){
        ModelAndView mav = new ModelAndView("general");
        mav.addObject("piece", RequestConstant.LOGISTICS_ASSIGN_PACKING_MAIN_PATH_RESOURCE);
        return mav;
    }
}
