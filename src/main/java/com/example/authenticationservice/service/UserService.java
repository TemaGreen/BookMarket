package com.example.authenticationservice.service;

public interface UserService {

    void registration( String username, String password);

    void checkUser( String username, String password);
}
