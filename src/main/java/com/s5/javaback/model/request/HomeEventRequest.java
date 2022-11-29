package com.s5.javaback.model.request;

import com.s5.javaback.model.entity.Turn;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomeEventRequest implements Serializable {
    @NotBlank
    private  String name;
    @NotBlank
    private  String address;
    @NotBlank
    private  String phone;
    @NotBlank
    private  String city;
    @NotBlank
    private  String state;
    @NotBlank
    private  String description;
    private  String photograpy;
    @NotBlank
    private String capacity;
    //List<Turn> turnList = new ArrayList<>();


}
