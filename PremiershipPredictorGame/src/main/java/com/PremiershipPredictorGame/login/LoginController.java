package com.PremiershipPredictorGame.login;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/login")
@AllArgsConstructor
public class LoginController {
    private LoginService loginService;

    @GetMapping
    public Token LoginUser(@RequestHeader("username") String username, @RequestHeader("password") String password){

        LoginRequest loginRequest = new LoginRequest(username, password);
        System.out.println(loginRequest);
        boolean isCorrect = loginService.loginUser(loginRequest);
        if(!isCorrect){
            throw new IllegalStateException("incorrect details");
        }
        return new Token(loginRequest.getUsername(), loginRequest.getPassword());



    }

}
