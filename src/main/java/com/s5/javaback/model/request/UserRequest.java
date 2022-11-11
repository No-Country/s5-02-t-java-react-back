package com.s5.javaback.model.request;

import lombok.Data;

@Data
public class UserRequest {
    private String name, username, email, password, repeatPassword;

    public boolean passwordsMatch() {
        return !password.isBlank() && !repeatPassword.isBlank() && repeatPassword.equals(password);
    }
}
