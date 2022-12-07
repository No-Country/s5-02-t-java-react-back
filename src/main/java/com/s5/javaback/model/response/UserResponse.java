package com.s5.javaback.model.response;

import lombok.Data;

@Data
public class UserResponse {
    private final long id;
    private final String name, email, createdAt;
    private final ImageResponse image;
}
