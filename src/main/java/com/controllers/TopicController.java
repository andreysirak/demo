package com.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.apache.logging.log4j.message.Message;
import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dao.CommentDAO;
import com.dao.TopicDAO;
import com.dao.UserHibernateDAOImp;
import com.entity.Comment;
import com.entity.ForumUser;
import com.entity.Reply;
import com.entity.Topic;

@Controller
@RequestMapping("/topic")
public class TopicController {	
	
	@Autowired
	CommentDAO commentDAO;
	
	@Autowired
	TopicDAO topicDAO;
	
	@Autowired
	UserHibernateDAOImp userHibernateDAOImp;
		
	@RequestMapping(value = "/add", method = GET)
	public String topicPage(Model model) {
		model.addAttribute(new Topic());		
		return "addTopic";			
	}	

	@RequestMapping(value = "/add", method = POST)
	public String addTopic(@Valid Topic topic, Errors errors, Principal principal) {
		if (errors.hasErrors()) {
			return "addTopic";
			}
		//String username = principal.getName();
		ForumUser forumUser = userHibernateDAOImp.findByUsername(principal.getName());
		topic.setUserId(forumUser.getId());
		topic.setTimePlaced(new Date());
		topic.setId((long) (Math.random()*100000));
		topicDAO.addTopic(topic);
		return "redirect:/";
		
	}
	
	@RequestMapping(value = "/{topicId}", method = GET)
	public String seeTopic(@PathVariable long topicId, Model model) {
		model.addAttribute("listOfFoundComments", commentDAO.findByTopicId(topicId));
		model.addAttribute("topic", topicDAO.findById(topicId));
		model.addAttribute(new Comment());
		model.addAttribute(new Reply());
		return "topic";		
	
	}
	
	@RequestMapping(value = "/{topicId}/addComment", method = POST)
	public String addComment(@PathVariable long topicId, RedirectAttributes model, @Valid Comment comment, Errors errors, Principal principal) {
		if (errors.hasErrors()) {			
			return "redirect:/topic/"+topicId;
			}
		ForumUser forumUser = userHibernateDAOImp.findByUsername(principal.getName());
		comment.setUsername(forumUser.getUsername());
		comment.setTopicId(topicId);
		comment.setId((long) (Math.random()*100000));
		comment.setTimePlaced(new Date());
		comment.setListOfReplies(new ArrayList<Reply>());
		commentDAO.addComment(comment);	
		return "redirect:/topic/"+topicId;			
	}
	
	@RequestMapping(value = "/{topicId}/reply", method = POST)
	public String addReplyToComment(@PathVariable long topicId, @RequestParam long commentId, @Valid Reply reply, Errors errors, Principal principal) {
		if (errors.hasErrors()) {			
			return "redirect:/topic/"+topicId;
			}
		ForumUser forumUser = userHibernateDAOImp.findByUsername(principal.getName());
		reply.setUsername(forumUser.getUsername());
		reply.setTimePlaced(new Date());
		reply.setCommentId(commentId);
		commentDAO.addReply(reply);
		return "redirect:/topic/"+topicId;			
	}

}
