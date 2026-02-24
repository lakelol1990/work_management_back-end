package com.work.domain.user.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Long id;
    private String email;
    private String password;
    private String role;
}