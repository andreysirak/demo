package com.dao;

import java.util.List;

import com.entity.Topic;

public interface TopicDAO {
	
	List<Topic> findAll();
	
	void addTopic(Topic topic);
	
	Topic findById(long topicId);

}
