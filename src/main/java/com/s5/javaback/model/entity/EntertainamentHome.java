package com.s5.javaback.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "entertainament_home")
public class EntertainamentHome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank
    private String name ;
    @NotBlank
     private String adress ;
    @NotBlank
    private String phone ;
    @NotBlank
    private String city ;
    @NotBlank
    private  String state ;
    @NotBlank
    private String description;
    @NotBlank
    private String capacity;
    private  String photograpy;


    @OneToMany(mappedBy = "entertainamentHome", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Image> images;

    public void addImages(List<Image> list){
        if ( images == null ) images = new ArrayList<Image>();
        list.forEach(image -> image.setEntertainamentHome(this));
    }

}