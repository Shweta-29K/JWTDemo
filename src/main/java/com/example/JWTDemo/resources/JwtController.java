package com.example.JWTDemo.resources;

import com.example.JWTDemo.JwtGenerator;
import com.example.JWTDemo.util.KeyStoreUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/generate/token")
public class JwtController {

    @PostMapping
    public String createToken(){
        Map<String,String> jwtProperties = new HashMap<>();
        jwtProperties.put("sub","Jwt");
        jwtProperties.put("iss","ISSUER1");
        jwtProperties.put("exp","600000");
        jwtProperties.put("alg","HS256");
        JwtGenerator jwtGenerator = new JwtGenerator(jwtProperties);
        KeyStoreUtils.loadKeyStore("keystore.jks","test@1234");
        PrivateKey key = KeyStoreUtils.getPrivateKey("sample","test@1234");
        String jwtToken = jwtGenerator.generate(key);
        return jwtToken;
    }

}
