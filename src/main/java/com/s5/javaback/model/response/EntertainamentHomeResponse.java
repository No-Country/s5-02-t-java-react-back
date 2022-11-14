package com.s5.javaback.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class EntertainamentHomeResponse implements Serializable {
    private  Long id;
    @NotBlank
    private  String name;
    @NotBlank
    private  String adress;
    @NotBlank
    private  String phone;
    @NotBlank
    private  String city;
    @NotBlank
    private  String state;
    @NotBlank
    private  String description;
    private  String photograpy;


}
