package py.com.econtreras.ecommerceadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import py.com.econtreras.ecommerceadmin.entity.Ringing;
import py.com.econtreras.ecommerceadmin.model.RingingModel;
import py.com.econtreras.ecommerceadmin.model.Wrapper;
import py.com.econtreras.ecommerceadmin.service.RingningService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/general/ringing")
public class RinginController {

    @Autowired
    private RingningService ringningService;

    @GetMapping()
    private String ringingHome(){
        return "/general/ringing/ringing-list";
    }

    @RequestMapping(value="/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAll(){
        List<RingingModel> models = ringningService.getAll();
        if(models.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else{
            Wrapper w = new Wrapper<Ringing>();
            w.setData(models);
            return new ResponseEntity(w, HttpStatus.OK);
        }

    }

    @GetMapping("/ringing-form")
    public ModelAndView getProduct(@ModelAttribute("ringing") RingingModel ringing){
        ModelAndView mav = new ModelAndView("/general/ringing/ringing-form");
        mav.addObject("ringing", ringing);
        return mav;
    }


    @RequestMapping(value="/", method = RequestMethod.POST)
    public ModelAndView save(){
        return null;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView getById(){
        return null;
    }


    @PostMapping
    public ModelAndView productSave(@Valid @ModelAttribute("ringing") RingingModel model, BindingResult bindingResult){
        ModelAndView mav = new ModelAndView("/general/ringing/ringing-form");
        if(bindingResult.hasErrors() || ringningService.checkExistByRingning(model.getRinging())){
            mav.addObject("ringing", model);
            return mav;
        }

        mav.setViewName("/general/ringing/ringing-list");
        ringningService.save(model);
        return mav;

    }

}
