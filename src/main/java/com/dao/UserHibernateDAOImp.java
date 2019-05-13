package com.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.ForumUser;

@Repository
@Transactional
public class UserHibernateDAOImp implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addUser(ForumUser user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(user);			
	}

	@Override
	public ForumUser findByUsername(String username) {		
		String hql="from ForumUser u where u.username = :username";
		 return sessionFactory.getCurrentSession()
		            .createQuery(hql, ForumUser.class)
		            .setParameter("username", username)
		            .uniqueResult();
	}

}
