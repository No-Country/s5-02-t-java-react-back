package com.s5.javaback.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class HomeEventResponse implements Serializable {
    private  Long id;

    private  String name;

    private  String address;

    private  String phone;

    private  String city;

    private  String state;

    private  String description;
    private  String photograpy;

    private String capacity;

    private Double price;

    private List<TurnResponse> turnResponseList;
    private List<ImageResponse> imageResponses;


}
