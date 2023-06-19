package com.bkdn.cntt.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.bkdn.cntt.configs.models.AccountModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	private final String secretKey = "7we4rfbhka9";

	private final long expiration = 24 * 60 * 60 * 1000l;

	public String generateToken(AccountModel accountModel) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expiration);
		return Jwts.builder().setSubject(accountModel.getAccount().id.toString()).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

	public String generateSpecificToken(String s, long expiration) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expiration);
		return Jwts.builder().setSubject(s).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

	public String getStringFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}
