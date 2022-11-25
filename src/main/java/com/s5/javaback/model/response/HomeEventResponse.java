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
public class HomeEventResponse implements Serializable {
    private  Long id;

    private  String name;

    private  String adress;

    private  String phone;

    private  String city;

    private  String state;

    private  String description;
    private  String photograpy;

    private String capacity;

    private List<TurnResponse> turnResponseList;


}
