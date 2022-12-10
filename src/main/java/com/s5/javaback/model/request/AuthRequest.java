package com.s5.javaback.model.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthRequest {
    private final @NotBlank String usernameOrEmail, password;
}
