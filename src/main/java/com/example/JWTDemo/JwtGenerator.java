package com.example.JWTDemo;

import com.example.JWTDemo.error.JWTAuthException;
import com.example.JWTDemo.util.JwtUtils;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Map;

public class JwtGenerator {
    private final Map<String,String> jwtProperties;
    private final Map<String,Object> jwtHeader;

    public JwtGenerator(Map<String, String> jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.jwtHeader = null;
    }

    public JwtGenerator(Map<String, String> jwtProperties, Map<String, Object> jwtHeader) {
        this.jwtProperties = jwtProperties;
        this.jwtHeader = jwtHeader;
    }

    public String generate(Key key){
        String token;
        try{
            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setClaims(JwtUtils.prepareClaims(jwtProperties));
            jwtBuilder.setExpiration(JwtUtils.preapreTokenExpiry(jwtProperties.get("exp")));
            if(jwtHeader != null){
                jwtBuilder.setHeader(jwtHeader);
            }
            token= jwtBuilder.compact();
        }catch (Exception e){
            throw new JWTAuthException(e);
        }
        return token;
    }
}
