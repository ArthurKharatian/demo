package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * This EncodingUtil class add an opportunity to work with the decoded and verified
 * username, received in refresh token
 */
public final class EncodingUtil {
	
	private EncodingUtil() {
		throw new UnsupportedOperationException();
	}
	
	public static String getRefreshToken(String authorizationHeader) {
		return authorizationHeader.substring("Bearer ".length());
	}
	
	public static String getDecodedUsername(String secret, String authorizationHeader) {
		JWTVerifier jwtVerifier = JWT.require(getAlgorithm(secret)).build();
		DecodedJWT decodedJWT = jwtVerifier.verify(getRefreshToken(authorizationHeader));
		return decodedJWT.getSubject();
	}
	
	public static Algorithm getAlgorithm(String secret) {
		return Algorithm.HMAC256(secret.getBytes());
	}
}
