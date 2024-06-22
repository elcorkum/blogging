package com.elcorkum.post_api.entity;

import com.elcorkum.post_api.entity.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    public User() {
    }

    public User(Long id, String username, Gender gender) {
        this.id = id;
        this.username = username;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
