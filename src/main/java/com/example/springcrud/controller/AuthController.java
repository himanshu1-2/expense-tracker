package com.example.springcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcrud.entity.AuthModel;
import com.example.springcrud.entity.JwtResponse;
import com.example.springcrud.entity.User;
import com.example.springcrud.entity.UserModel;
import com.example.springcrud.security.CustomerDetailsService;
import com.example.springcrud.service.UserService;
import com.example.springcrud.util.JwtUtil;

import jakarta.validation.Valid;

@RestController
public class AuthController {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/register")
	public User createUser(@Valid @RequestBody UserModel user) {
		return userService.createUser(user);
	}
	
	

	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody AuthModel authModel) throws Exception {
		
		authenticate(authModel.getEmail(), authModel.getPassword());
		
		//we need to generate the jwt token
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authModel.getEmail());
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);
	}
	
	
private void authenticate(String email, String password) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("User disabled");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad Credentials");
		}
		
	}
}
