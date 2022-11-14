package com.s5.javaback.model.response;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
public class EntertainamentHomeResponse implements Serializable {
    private final Long id;
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
    private final List<List<ScheduleResponse>> ScheduleResponseList;
}
