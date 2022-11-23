package com.s5.javaback.model.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserTurnResponse {

    private Long idUser;
    private  String name, username, email;
}
