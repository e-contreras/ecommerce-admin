package py.com.econtreras.ecommerceadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import py.com.econtreras.api.beans.MenuSection;
import py.com.econtreras.api.beans.UserBean;
import py.com.econtreras.ecommerceadmin.service.MenuModuleSerivice;
import py.com.econtreras.ecommerceadmin.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuModuleSerivice service;
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<MenuSection>> getMenu(){
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}


	@GetMapping
	@RequestMapping("/user")
	public  ResponseEntity<UserBean> getUserAuthenticated(){
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new ResponseEntity<>(userService.findByUserName(principal.getUsername()), HttpStatus.OK);
	}

}
