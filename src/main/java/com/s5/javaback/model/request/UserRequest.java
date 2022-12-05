package com.s5.javaback.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {
    private final @NotBlank String name, username, email, password, repeatPassword;

    public boolean passwordsMatch() {
        return !password.isBlank() && !repeatPassword.isBlank() && repeatPassword.equals(password);
    }
}
