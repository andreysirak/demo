package com.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dao.UserDAO;
import com.entity.User;

@Controller
@Transactional
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	
	@RequestMapping(value = "/addUser", method = GET)
	public String userForm(Model model, User user) {
		model.addAttribute(user);
		return "addUser";	
		
	}
	
	@RequestMapping(value = "/userAdded", method = GET)
	public String userAddedPage() {		
		return "userAdded";	
		
	}
	
	@RequestMapping(value = "/addUser", method = POST)
	public String addUser(RedirectAttributes model, @Valid User user, Errors errors) {
		if (errors.hasErrors()) {			
			return "addUser";
			}
		user.setTimePlaced(new Date());	
		userDAO.addUser(user);
		model.addFlashAttribute(user);
		return "redirect:/userAdded";
	}
	

}
