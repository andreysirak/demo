package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.Topic;

@Repository
@Transactional
public class TopicHibernateDAOImpl implements TopicDAO{
	
	@Autowired
	 private SessionFactory sessionFactory;

	@Override
	public List<Topic> findAll() {
		Session session = this.sessionFactory.getCurrentSession();		
		return session.createQuery("from Topic").list();
	}

	@Override
	public void addTopic(Topic topic) {
		Session session = this.sessionFactory.getCurrentSession();
		//session.beginTransaction();
        session.save(topic);
        //session.getTransaction().commit();	
		
	}

	@Override
	public Topic findById(long topicId) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Topic.class, topicId);
	}

}
