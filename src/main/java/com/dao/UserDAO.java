package com.dao;

import com.entity.ForumUser;

public interface UserDAO {
	
	void addUser(ForumUser user);
	
	ForumUser findByUsername(String username);
	
	
	

}
