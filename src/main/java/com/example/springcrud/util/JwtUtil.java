package com.example.springcrud.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtil {

	private static final long JWT_TOKEN_VALDITY=24*60*60;
	
	@Value("${jwt.secret}")
	private String secret;
	
	public String generateToken(UserDetails userDetails) {
	  
		Map<String,Object>clamis = new HashMap<>();
		
		return Jwts.builder()
				.setClaims(clamis)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() +  JWT_TOKEN_VALDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
				
	}
	
    public String getUsernameFromToken(String jwtToken) {
    	return  getClaimFromToken(jwtToken,Claims::getSubject);
    }

    

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claimsResolver.apply(claims);
	}
	
	
	public boolean validateToken(String jwtToken,UserDetails userDetails) {
		final String username = getUsernameFromToken(jwtToken);
		return username.equals(userDetails.getUsername())&&!isTokenExpired(jwtToken);
	}

	
	private boolean isTokenExpired(String jwtToken) {
		final Date expiration = getExpirationDateFromToken(jwtToken);
		System.out.print("isTokenExpired"+expiration);
		return expiration.before(new Date());
	}
     

	private Date getExpirationDateFromToken(String jwtToken) {
		return getClaimFromToken(jwtToken, Claims::getExpiration);
	}
	
}
