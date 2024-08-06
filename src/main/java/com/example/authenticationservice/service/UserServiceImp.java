package com.example.authenticationservice.service;

import com.example.authenticationservice.entity.UserEntity;
import com.example.authenticationservice.exception.LoginException;
import com.example.authenticationservice.exception.RegistrationException;
import com.example.authenticationservice.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    @Override
    public void registration(String username, String password) {
        if(userRepository.findById(username).isPresent())
            throw new RegistrationException("A user named " + username + " has already been registered");
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        userRepository.save(new UserEntity(username, hash));
    }

    @Override
    public void checkUser(String username, String password) {
        Optional<UserEntity> optionalUser = userRepository.findById(username);
        if (optionalUser.isEmpty())
            throw new LoginException("A user named " + username + "is not found");
        UserEntity user = optionalUser.get();

        if(!BCrypt.checkpw(password, user.getHash()))
            throw new LoginException("Password is incorrect");
    }
}
