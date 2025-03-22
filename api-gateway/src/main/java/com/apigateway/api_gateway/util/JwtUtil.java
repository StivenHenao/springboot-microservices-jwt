package com.apigateway.api_gateway.util;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Service
public class JwtUtil {

    public static final String SECRET = "9f4c3b47a8f2e1d6b293c4e5f6a7b8c9d0e1f2a3b4c5d6e7f8a9b0c1d2e3f4a5";

    public void validateToken(final String token){
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
}
