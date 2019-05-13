package com.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.TopicDAO;
import com.entity.ForumUser;
import com.entity.Topic;

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
		ArrayList<Topic> topics = (ArrayList<Topic>) topicDAO.findAll();
		for (Topic topic : topics) {
System.out.println(topic.getForumUser().getUsername());
		}
		model.addAttribute(new ForumUser());
		return "home";
	}
	
	@RequestMapping(value = "/", method = POST)
	public String login() {
		return "home";
	}
	
	
	
}
