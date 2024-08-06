package com.example.authenticationservice.repositories;

import com.example.authenticationservice.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
