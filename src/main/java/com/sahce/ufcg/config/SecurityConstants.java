package com.sahce.ufcg.config;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 900000/*Long.MAX_VALUE*/; // 15 minutes
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORITIES_HEADER = "Authorities";
    public static final String SIGN_UP_URL = "/users/";
}
