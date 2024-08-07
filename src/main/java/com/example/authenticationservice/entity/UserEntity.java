package com.example.authenticationservice.entity;

import jakarta.persistence.*;

@Entity
@Table( name = "users")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", allocationSize = 1)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String hash;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public UserEntity(int id, String username, String hash) {
        this.id = id;
        this.username = username;
        this.hash = hash;
    }

    public UserEntity(String username, String hash) {
        this.username = username;
        this.hash = hash;
    }

    public UserEntity() {
    }
}
