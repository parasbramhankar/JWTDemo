package com.example.JWTDemo.controller;

import com.example.JWTDemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class JwtController {

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/generate-token")
    public String generateToken(@RequestParam String username, @RequestParam String pass){

        if("admin".equals(username) && "admin".equals(pass)){

            return jwtUtil.generateToken(username);

        }else{
            return " Invalid credentials";
        }
    }

        @GetMapping("/transfer")
    public String fundTransfer(@RequestHeader(value = "Authorization",required = false) String authorizationHeader){
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            String token=authorizationHeader.substring(7);

            if(jwtUtil.validateToken(token)){
                return "This is secured Api, token valid";
            }
            return "Token invalid";
        }
        else{
            return "Authorization header is missing";
        }

    }

}
