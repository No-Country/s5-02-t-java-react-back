package com.s5.javaback.model.request;

import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class EmailRequest {

    private String to;
    private String from;
    private String  subject;
    private String text;
    private String template;
    private String image;

    private Map<String, Object> properties;
}
