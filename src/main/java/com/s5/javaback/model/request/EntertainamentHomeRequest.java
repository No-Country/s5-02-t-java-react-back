package com.s5.javaback.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class EntertainamentHomeRequest implements Serializable {
    @NotBlank
    private final String name;
    @NotBlank
    private final String direction;
    @NotBlank
    private final String phone;
    @NotBlank
    private final String city;
    @NotBlank
    private final String state;
    @NotBlank
    private final String description;
    private final String photograpy;
}
