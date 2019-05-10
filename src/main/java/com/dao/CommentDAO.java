package com.dao;

import java.util.List;

import com.entity.Comment;
import com.entity.Reply;

public interface CommentDAO {
	
	List<Comment> findByTopicId(Long topicId);
	
	void addComment (Comment comment);
	
	void addReply(Reply reply);

}
