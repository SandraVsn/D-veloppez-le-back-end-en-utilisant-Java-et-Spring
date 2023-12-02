package com.openclassrooms.chatop.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.model.User;

@Service
public class JWTService {


	private JwtEncoder jwtEncoder;
	
	public JWTService(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}
	
	/**
	 * Generates a JWT (JSON Web Token) for the provided User.
	 * @param user The User for whom the JWT is generated.
	 * @return A string representing the JWT.
	 */
	public String generateToken(User user) {
		Instant now = Instant.now();
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("self") // L'issuer dans un JWT est une identification de l'entité qui a signé et émis le jeton, habituellement une chaîne de caractères servant à identifier de manière unique l'application ou le service à l'origine du jeton.
               	.issuedAt(now)
              	.expiresAt(now.plus(1, ChronoUnit.DAYS))
              	.subject(user.getEmail()) // Entity to which the token pertains, a string representing the unique identifier of the user.
              	.build();
		
	    // Build parameters for encoding the JWT
		JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
		
	    // Encode the JWT using the configured JwtEncoder
		return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
	}
	
}
