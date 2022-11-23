package com.s5.javaback.model.request;

import com.s5.javaback.model.entity.Turn;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class HomeEventRequest implements Serializable {
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
    List<Turn> turnList = new ArrayList<>();


}
