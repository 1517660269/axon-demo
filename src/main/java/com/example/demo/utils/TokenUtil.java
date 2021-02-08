package com.example.demo.utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.exception.TokenException;

public final class TokenUtil {

	private static long EXPIRE_TIME = 10 * 60 * 60 * 1000;

	private static String TOKEN_SECRET = "AXON";

	public static String sing(String account) {
		return JWT.create().withIssuer("axon").withClaim("account", account)
					   .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
					   .sign(Algorithm.HMAC256(TOKEN_SECRET));
	}

	public static boolean verify(String token) {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("axon").build();
		try {
			verifier.verify(token);
		} catch (JWTVerificationException e) {
			return false;
		}
		return true;
	}

	public static String getAccountByJwt(String token) {
		try {
			return JWT.decode(token).getClaim("account").asString();
		} catch (JWTDecodeException e) {
			throw new TokenException("认证失败");
		}
	}
}
