package com.lcwd.user.service.services;

import java.util.List;

import com.lcwd.user.service.entities.User;

public interface UserService {
	
	//user operation
	
	//create
	User saveUser(User user) ;
	
	//get all user
	List<User> getAllUser();
	
	//get single user of given userId
	
	User getUser(String userId);
	
	//delete single user by userId
	
	//update single user by userId

}
