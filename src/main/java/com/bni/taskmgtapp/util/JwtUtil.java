package com.bni.taskmgtapp.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Service
public class JwtUtil {
    // Super secret key - in production use a proper secret from config
    // jwt menentukan panjang secret key, jadi g bias terlalu pendek
    private static final String SECRET = "jhdfihasdjbfsaljdfblwshdgfuesgryfidfbsdskhbaskdfbaskdjfbasofdsjdfb";

    private static final Key SIGNING_KEY = new SecretKeySpec(
        Base64.getDecoder().decode(SECRET),
        SignatureAlgorithm.HS256.getJcaName());

    // private static final Key SIGNING_KEY = new SecretKeySpec(
    // "ThisIsASuperSecretKeyThatShouldBeLongEnough".getBytes(),
    // SignatureAlgorithm.HS256.getJcaName());

    
/** 
        @param username - the surname for which the token is created
        @return -> the generated jwt token
*/
        // untuk generate JWT Token
        
        public String createToken(String username){
            return Jwts.builder()
                    .setSubject(username)
                    .signWith(SIGNING_KEY)
                    .compact();
        }
        

        public String validateToken(String token){
            return Jwts.parserBuilder()
                    .setSigningKey(SIGNING_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }
}
