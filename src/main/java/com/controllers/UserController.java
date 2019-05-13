package com.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dao.UserDAO;
import com.entity.ForumUser;

@Controller
@Transactional
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	
	@RequestMapping(value = "/addUser", method = GET)
	public String userForm(Model model, ForumUser user) {
		model.addAttribute(user);	
		
		
		
		return "addUser";	
		
	}
	
	@RequestMapping(value = "/userAdded", method = GET)
	public String userAddedPage() {		
		return "userAdded";	
		
	}
	
	@RequestMapping(value = "/addUser", method = POST)
	public String addUser(RedirectAttributes model, @Valid ForumUser user, Errors errors) {
		ForumUser duplicateUser = userDAO.findByUsername(user.getUsername());
		if (duplicateUser != null) {
			errors.rejectValue("username","error.forumUser", "There is already a person registered with the username provided");			
		}
		if (errors.hasErrors()) {			
			return "addUser";
			}
		user.setTimePlaced(new Date());	
		userDAO.addUser(user);		
		model.addFlashAttribute(user);
		
		List<GrantedAuthority> authorities =
				new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority("USER"));				
				PasswordEncoder encoder =
						PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserDetails userDetails = User.withUsername(user.getUsername())
		           .password(encoder.encode(user.getPassword()))
		           .roles("USER").build();	
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
	    SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/userAdded";
	}
	

}
