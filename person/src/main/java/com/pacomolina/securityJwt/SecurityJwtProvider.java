package com.pacomolina.securityJwt;

import java.util.Date;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.pacomolina.entity.PrincipalUser;


@Component
public class SecurityJwtProvider {
	
	private final static Logger log = LoggerFactory.getLogger(SecurityJwtProvider.class);
	
	
	private String secretKey = "D#7kP&yZtL@5qS*9cM2vR%jXnFpW!6";
	
	
	private int expiration = 900;
	
	
	public String generateToke(Authentication authentication) {
		PrincipalUser principalPerson = (PrincipalUser) authentication.getPrincipal();
		
		return Jwts.builder().setSubject(principalPerson.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
	}
	

	public String getUserNameFromToke(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}
	

	public boolean validateToken(String token) {
		 try {
	            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
	            return true;
	        }catch (MalformedJwtException e){
	            log.error("malformed token");
	        }catch (UnsupportedJwtException e){
	        	log.error("token not supported");
	        }catch (ExpiredJwtException e){
	        	log.error("expired token");
	        }catch (IllegalArgumentException e){
	        	log.error("empty token");
	        }catch (SignatureException e){
	        	log.error("fail in signing");
	        }
	        return false;
	}
	
	

}
