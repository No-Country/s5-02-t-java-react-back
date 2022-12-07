package com.s5.javaback.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {
    private final @NotBlank String name, email;
}
