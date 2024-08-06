package com.example.authenticationservice.entity;

import jakarta.persistence.*;

@Entity
@Table( name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name")
    private String username;

    private String password;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity() {
    }
}
