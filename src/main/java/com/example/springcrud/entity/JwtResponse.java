package com.example.springcrud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {
	
	private  String jwtToken;

//	public JwtResponse(String jwtToken) {
//		super();
//		this.jwtToken = jwtToken;
//	}

	public String getJwtToken() {
		return jwtToken;
	}
	
	
}

