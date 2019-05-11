package com.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "topics")
public class Topic {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 1, max = 50)
	@Column(name="content", nullable = false)
	private String content;
	
	//@Column(name="timePlaced")	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat (pattern="MM/dd/yyyy HH:mm")
	private Date timePlaced;
	
	@Column(name="userId")
	private Long userId;
	
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public Date getTimePlaced() {
		return timePlaced;
	}

	public Topic(Long id, String content, Date timePlaced) {		
		this.id = id;
		this.content = content;
		this.timePlaced = timePlaced;
		
	}

	public Topic() {
	
		
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTimePlaced(Date timePlaced) {
		this.timePlaced = timePlaced;
	}

	public Topic(Long id, String content) {
		this.id = id;
		this.content = content;
			
	}
				
}
