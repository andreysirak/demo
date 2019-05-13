package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;

import com.entity.ForumUser;

@Service
public class UserServiceHibernate implements UserDetailsService {
	
	@Autowired
	UserHibernateDAOImp userHibernateDAOImp;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ForumUser user = userHibernateDAOImp.findByUsername(username);
					
//		List<GrantedAuthority> authorities =
//				new ArrayList<GrantedAuthority>();
//				authorities.add(new SimpleGrantedAuthority("USER"));
				PasswordEncoder encoder =
					     PasswordEncoderFactories.createDelegatingPasswordEncoder();
			return User.withUsername(user.getUsername())
                    .password(encoder.encode(user.getPassword()))
                    .roles("USER").build();				
		
	}

}
