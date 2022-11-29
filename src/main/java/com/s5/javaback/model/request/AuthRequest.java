package com.s5.javaback.model.request;


import lombok.Data;

@Data
public class AuthRequest {
    private  String usernameOrEmail, password;
}
