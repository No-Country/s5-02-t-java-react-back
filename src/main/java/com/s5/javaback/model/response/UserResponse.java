package com.s5.javaback.model.response;

import lombok.Data;

@Data
public class UserResponse {
    private long id;
    private String name, username, email, createdAt;
    private  ImageResponse image;
}
