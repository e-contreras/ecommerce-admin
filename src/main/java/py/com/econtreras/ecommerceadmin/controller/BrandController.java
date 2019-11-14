package py.com.econtreras.ecommerceadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import py.com.econtreras.api.beans.BrandBean;
import py.com.econtreras.ecommerceadmin.service.BrandService;

import java.util.List;

@Controller
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BrandBean>> getBrandList(){
        return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
    }

}
