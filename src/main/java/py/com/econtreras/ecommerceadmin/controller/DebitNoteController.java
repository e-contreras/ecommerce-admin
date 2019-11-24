package py.com.econtreras.ecommerceadmin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import py.com.econtreras.ecommerceadmin.util.RequestConstant;

/**
 * @author
 */
@Controller
@RequestMapping(RequestConstant.PURCHASE_DEBIT_NOTE)
@PreAuthorize("hasAuthority('compras')")
public class DebitNoteController {
    
    @GetMapping
    public ModelAndView getDebitNoteView(){
        ModelAndView mav = new ModelAndView("general");
        mav.addObject("piece", RequestConstant.DEBIT_NOTE_MAIN_PATH_RESOURCE);
        return mav;
    }
    
    @GetMapping(RequestConstant.PURCHASE_DEBIT_NOTE_FORM_NEW)
    public ModelAndView getFormViewCreditNote(){
        ModelAndView mav = new ModelAndView("general");
        mav.addObject("piece", RequestConstant.PURCHASE_DEBIT_NOTE_FORM_PATH_RESOURCE);
        return mav;
    }
    
    @GetMapping(RequestConstant.PURCHASE_DEBIT_NOTE_FORM_EDIT)
    public ModelAndView getCreditNoteEdit(@PathVariable("id")Integer id){
        ModelAndView mav = new ModelAndView("general");
        mav.addObject("piece", RequestConstant.PURCHASE_DEBIT_NOTE_FORM_PATH_RESOURCE);
        mav.addObject("is", id);
        mav.addObject("mode", "edit");
        return mav;
    }
    
}
