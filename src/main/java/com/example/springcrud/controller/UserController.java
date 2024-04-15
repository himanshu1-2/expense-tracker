package com.example.springcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcrud.entity.Expense;
import com.example.springcrud.entity.User;
import com.example.springcrud.entity.UserModel;
import com.example.springcrud.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> readUser(@PathVariable Long id) {
		return new ResponseEntity<User>(userService.readUser(id), HttpStatus.OK);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@RequestBody UserModel user, @PathVariable Long id) {
		return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
}
