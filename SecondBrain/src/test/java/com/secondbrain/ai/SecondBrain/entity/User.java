package com.secondbrain.ai.SecondBrain.entity;

import jakarta.persistence.Entity;

@Entity
public class User extends BaseEntity{
    private String username;

    private String email;

    private String password;
}
