package com.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.TopicDAO;
import com.entity.User;

@Controller
public class HomeController {
	
	private TopicDAO topicDAO;
	
	
	@Autowired
	public HomeController(TopicDAO topicDAO) {
		this.topicDAO = topicDAO;
	}


	@RequestMapping(value = "/", method = GET)
	public String home(Model model) {
		model.addAttribute("topicsList", topicDAO.findAll());
		model.addAttribute(new User());
		return "home";
	}
	
	@RequestMapping(value = "/", method = POST)
	public String login(Model model) {
		model.addAttribute("topicsList", topicDAO.findAll());
		model.addAttribute(new User());
		return "home";
	}
	
	
	
}
