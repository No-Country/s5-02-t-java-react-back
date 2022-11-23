package com.s5.javaback.model.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserTurnRequest {

    private String name, username, email;
}
