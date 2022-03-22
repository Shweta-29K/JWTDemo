package com.example.JWTDemo.util;

import com.example.JWTDemo.error.JWTAuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    public static Claims prepareClaims(final Map<String, String> jwtProperties) {
        Claims claims = Jwts.claims();
        claims.putAll(jwtProperties);
        return claims;

    }

    public static Date preapreTokenExpiry(String expiry) {
        long nowMillis = System.currentTimeMillis();
        int ttlMillis;
        Date exp;
        try{
            ttlMillis = Integer.parseInt(expiry);
        }catch (NumberFormatException ex){
           throw new JWTAuthException("["+expiry+"] value for exp is invalid");
        }
        if (ttlMillis >= 0){
            long expMillis = ttlMillis + nowMillis ;
            exp = new Date(expMillis);
        }else {
            throw new JWTAuthException("["+expiry+"] value for exp is invalid");
        }
        return exp;
    }
}
