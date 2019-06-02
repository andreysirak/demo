package com.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.CommentHibernateDAOImpl;
import com.dao.UserHibernateDAOImp;
import com.entity.Comment;
import com.entity.ForumUser;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RestServiceController {
	
	@Autowired
	UserHibernateDAOImp userHibernateDAOImp;
	
	@Autowired
	CommentHibernateDAOImpl commentHibernateDAOImpl; 
	
//	@RequestMapping(method=POST, consumes="application/json")
//	public Object addUser(@RequestBody ForumUser forumUser) {
//		return userHibernateDAOImp.addUser(forumUser);
//	}
	
	
	
	@RequestMapping(value = "/restTopic/{topicId}", method=GET, produces="application/json")
	public ResponseEntity<List<Comment>> findCommentsByTopicId(@PathVariable long topicId){
		
		List<Comment> listOfComments = new ArrayList<Comment>();				
		listOfComments = commentHibernateDAOImpl.findByTopicId(topicId);
		HttpStatus status = listOfComments.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		
		return new ResponseEntity<List<Comment>>(listOfComments,status);
		
	}

}
