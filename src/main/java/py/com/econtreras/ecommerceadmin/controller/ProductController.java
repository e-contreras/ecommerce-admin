package py.com.econtreras.ecommerceadmin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import py.com.econtreras.api.beans.ProductBean;
import py.com.econtreras.api.beans.ProductRequestWrapper;
import py.com.econtreras.api.beans.UserBean;
import py.com.econtreras.ecommerceadmin.service.BrandService;
import py.com.econtreras.ecommerceadmin.service.CategoryService;
import py.com.econtreras.ecommerceadmin.service.ProductService;
import py.com.econtreras.ecommerceadmin.service.UserService;
import py.com.econtreras.ecommerceadmin.util.RequestConstant;

@Controller
@RequestMapping(RequestConstant.PURCHASE_PRODUCT)
@PreAuthorize("hasAuthority('compras')")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

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
    public ModelAndView getProduct(@ModelAttribute("product") ProductBean product){
        ModelAndView mav = new ModelAndView(RequestConstant.PURCHASE_PRODUCT_FORM_VIEW);
        mav.addObject("product", product);
        mav.addObject("brands", brandService.findAll());
        mav.addObject("categories", categoryService.findAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserBean user = userService.findByUserName(currentPrincipalName);
        return mav;
    }

    @GetMapping(RequestConstant.PURCHASE_PRODUCT_FORM_EDIT)
    public ModelAndView edit(@PathVariable(value = "id") Integer id){
        ModelAndView mav = new ModelAndView(RequestConstant.PURCHASE_PRODUCT_FORM_VIEW);
        mav.addObject("mode", "edit");
        mav.addObject("is", id);
        return mav;
        
    }

    @PostMapping
    public ModelAndView productSave(@Valid @ModelAttribute("product") ProductBean product, BindingResult bindingResult){
        ModelAndView mav = new ModelAndView(RequestConstant.PURCHASE_PRODUCT_FORM_VIEW);
        if(bindingResult.hasErrors()){
            mav.addObject("brands", brandService.findAll());
            mav.addObject("categories", categoryService.findAll());
            return mav;
        }

        mav.setViewName(RequestConstant.PURCHASE_PRODUCT_VIEW);
        productService.save(product);
        return mav;

    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity productList(@RequestParam("name") String name){

        List<ProductBean> list = productService.findByProductName(name);
        if(list.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(new ProductRequestWrapper(list), HttpStatus.OK);
        }

    }

}