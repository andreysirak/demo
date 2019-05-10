package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.Comment;
import com.entity.Reply;

@Repository
@Transactional
public class CommentHibernateDAOImpl implements CommentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Comment> findByTopicId(Long topicId) {
		Session session = this.sessionFactory.getCurrentSession();	
		return session.createQuery("from Comment c where c.topicId = :topicId").setParameter("topicId", topicId).list();	
	}

	@Override
	public void addComment(Comment comment) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(comment);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addReply(Reply reply) {
		Session session = this.sessionFactory.getCurrentSession();
		
        session.save(reply);
		
		
	}

}
