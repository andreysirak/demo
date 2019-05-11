package com.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Comment")
public class Comment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Long id;	
	
	@Column(name="topicId", nullable = false)
	private  Long topicId;
	
	@Size(min = 1, max = 200)
	@Column(name="message", nullable = false)
	private  String message;
	
	@Column(name="timePlaced")
	private  Date timePlaced;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "commentId")
	private  List<Reply> reply;

	@Column(name="username")
	private String username;
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getTopicId() {
		return topicId;
	}
	
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Date getTimePlaced() {
		return timePlaced;
	}
	
	public void setTimePlaced(Date timePlaced) {
		this.timePlaced = timePlaced;
	}
	
	public List<Reply> getListOfReplies() {
		return reply;
	}
	
	
	public void setListOfReplies(List<Reply> reply) {
		this.reply = reply;
	}

	public Comment(Long topicId, Long id, String message, List<Reply> reply) {		
		this.topicId = topicId;
		this.id = id;
		this.message = message;
		this.reply = reply;
	}

	public Comment() {
		
	}
	
	
	
	
	
	
	
	

}
