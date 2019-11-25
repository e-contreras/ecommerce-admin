package py.com.econtreras.ecommerceadmin.controller;

import java.io.ByteArrayOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import py.com.econtreras.ecommerceadmin.service.PdfGenerator;
import py.com.econtreras.ecommerceadmin.util.RequestConstant;

/**
 * @author
 */
@Controller
@RequestMapping(RequestConstant.PURCHASE_CREDIT_NOTE)
@PreAuthorize("hasAuthority('compras')")
public class CreditNoteController {
    
    @GetMapping
    public ModelAndView getCreditNoteView() {
        ModelAndView mav = new ModelAndView("general");
        mav.addObject("piece", RequestConstant.CREDIT_NOTE_MAIN_PATH_RESOURCE);
        return mav;
    }

    @GetMapping(RequestConstant.PURCHASE_CREDIT_NOTE_FORM_NEW)
    public ModelAndView getFormViewCreditNote(){
        ModelAndView mav = new ModelAndView("general");
        mav.addObject("piece", RequestConstant.PURCHASE_CREDIT_NOTE_FORM_PATH_RESOURCE);
        return mav;
    }
    
    @GetMapping(RequestConstant.PURCHASE_CREDIT_NOTE_FORM_EDIT)
    public ModelAndView getCreditNoteEdit(@PathVariable("id")Integer id){
        ModelAndView mav = new ModelAndView("general");
        mav.addObject("piece", RequestConstant.PURCHASE_CREDIT_NOTE_FORM_PATH_RESOURCE);
        mav.addObject("is", id);
        mav.addObject("mode", "edit");
        return mav;
    }
}