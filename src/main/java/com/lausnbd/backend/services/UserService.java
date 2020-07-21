package com.lausnbd.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lausnbd.backend.beans.User;
import com.lausnbd.backend.repositories.UserRepository;

@Service
public class UserService {

	
	@Autowired private  UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}

}
