package com.s5.javaback.model.request;

import com.s5.javaback.model.entity.Schedule;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class EntertainamentHomeRequest implements Serializable {
    @NotBlank
    private final String name;
    @NotBlank
    private final String adress;
    @NotBlank
    private final String phone;
    @NotBlank
    private final String city;
    @NotBlank
    private final String state;
    @NotBlank
    private final String description;
    private final String photograpy;
    @NotBlank
    private String capacity;


}
