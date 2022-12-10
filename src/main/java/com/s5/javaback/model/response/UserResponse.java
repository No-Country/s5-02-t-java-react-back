package com.s5.javaback.model.response;

import com.s5.javaback.util.enums.UserStatus;
import lombok.Data;

@Data
public class UserResponse {
    private final long id;
    private final String name, email, createdAt;
    private final ImageResponse image;
    private final UserStatus status;
}
