package com.bni.taskmgtapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bni.taskmgtapp.util.JwtUtil;


// import com.bni.taskmgtapp.*;
// import com.bni.taskmgtapp.util.JwtUtil;

@RestController
public class AuthController {
    // manggil file jwt ke dalam controller
    private final JwtUtil jwtService;

    public AuthController(JwtUtil jwtService){
        this.jwtService = jwtService; 
    }
    /**
     * Generates a JWT token for the given username
     * @param username -> the username for which the token is generated
     * @return -> the generated JWT token
     * 
     */

     @GetMapping("/get-token/{username}")
     public String getToken(@PathVariable String username){
        return jwtService.createToken(username);
     }

     @PostMapping("/validate-token")
     public String validateToken(@RequestBody String Token) {
         try {
            return "Token is valid for user: " + jwtService.validateToken(Token);
         }
         catch(Exception e){
            return "Invalid token: " + e.getMessage();
         }
     }
     
}
