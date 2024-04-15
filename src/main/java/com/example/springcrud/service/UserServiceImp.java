package com.example.springcrud.service;

import com.example.springcrud.entity.User;
import com.example.springcrud.entity.UserModel;

public interface UserServiceImp {

User createUser(UserModel user);
	
	User readUser(Long id);
	
	User updateUser(UserModel user, Long id);
	
	void deleteUser(Long id);
	
	User getLoggedInUser();
}
