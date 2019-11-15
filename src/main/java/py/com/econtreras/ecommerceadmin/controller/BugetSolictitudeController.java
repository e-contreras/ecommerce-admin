package py.com.econtreras.ecommerceadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import py.com.econtreras.api.beans.BugetSolicitudeModel;
import py.com.econtreras.api.beans.ProductBean;
import py.com.econtreras.ecommerceadmin.service.ProductService;

import java.util.ArrayList;

@Controller
@RequestMapping("/buget-solicitudes")
public class BugetSolictitudeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/form")
    public ModelAndView getForm(@ModelAttribute("buget") BugetSolicitudeModel buget){
        ModelAndView mav = new ModelAndView("/purchase/buget-solicitudes/buget-solicitudes-form");
        if(buget == null){
            buget = new BugetSolicitudeModel();
        }
        buget.setProductList(buget.getProductList() == null ? new ArrayList<>() : buget.getProductList());
        mav.addObject("buget", buget);
        mav.addObject("products", productService.findAll());
        return mav;
    }

    @PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addProduct(@RequestParam("product_id") Integer productId){
        return null;
    }

}
