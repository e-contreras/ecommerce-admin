package py.com.econtreras.ecommerceadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import py.com.econtreras.api.beans.ProductRequest;
import py.com.econtreras.api.beans.ProductRequestWrapper;
import py.com.econtreras.ecommerceadmin.service.ProductService;
import py.com.econtreras.ecommerceadmin.util.RequestConstant;

import javax.validation.Valid;

@Controller
@RequestMapping(RequestConstant.PURCHASE_PRODUCT)
@PreAuthorize("hasAuthority('compras')")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ModelAndView getProducts(){
        ModelAndView mav = new ModelAndView(RequestConstant.PURCHASE_PRODUCT_VIEW);
        mav.addObject("products", productService.findAll());
        return mav;
    }

    @GetMapping("/")
    public ResponseEntity<ProductRequestWrapper> getProductList(){
        return new ResponseEntity<>(new ProductRequestWrapper(productService.findAll()), HttpStatus.OK);
    }


    @GetMapping(RequestConstant.PURCHASE_PRODUCT_FORM)
    public ModelAndView getProduct(@ModelAttribute("product") ProductRequest product){
        ModelAndView mav = new ModelAndView(RequestConstant.PURCHASE_PRODUCT_FORM_VIEW);
        mav.addObject("product", product);
        return mav;
    }

    @PostMapping
    public ModelAndView productSave(@Valid @ModelAttribute("product") ProductRequest product){
        ModelAndView mav = new ModelAndView(RequestConstant.PURCHASE_PRODUCT_FORM_VIEW);
        product = productService.save(product);
        mav.addObject("product", product);
        return mav;
    }


}