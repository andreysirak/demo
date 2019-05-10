package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.entity.Topic;


//Component
public class TopicDAOImpl implements TopicDAO {
	
	static ArrayList<Topic> listOfTopics = new ArrayList<Topic>();
	
	static {
		listOfTopics.add(new Topic((long) 123, "why you think java is fun?"));
		listOfTopics.add(new Topic((long) 124, "anybody help. how do learn java?"));
	}

	@Override
	public List<Topic> findAll() {
		
		return listOfTopics;
	}

	@Override
	public Topic findById(long topicId) {
		for(Topic topic: listOfTopics) {
			if (topic.getId().equals(topicId)) {
				return topic;				
			}
		}
		return null;
	}
	
	@Override
	public void addTopic(Topic topic) {
		listOfTopics.add(topic);
	}

}
