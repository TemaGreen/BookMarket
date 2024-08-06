package com.example.authenticationservice.controller;

import com.example.authenticationservice.exception.LoginException;
import com.example.authenticationservice.exception.RegistrationException;
import com.example.authenticationservice.response.ErrorResponse;
import com.example.authenticationservice.response.TokenResponse;
import com.example.authenticationservice.response.User;
import com.example.authenticationservice.service.TokenService;
import com.example.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody User user){
        userService.registration(user.getUsername(), user.getPassword());
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/token")
    public TokenResponse getToken(@RequestBody User user){
        userService.checkUser(user.getUsername(), user.getPassword());
        return new TokenResponse(tokenService.generateToken(user.getUsername()));
    }

    @ExceptionHandler({RegistrationException.class, LoginException.class})
    public ResponseEntity<ErrorResponse> handlerUserRegistrationException(RuntimeException ex){
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }
}
