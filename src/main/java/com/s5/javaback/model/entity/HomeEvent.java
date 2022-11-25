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
@Table(name = "home_event")
public class HomeEvent {
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

    @OneToMany(mappedBy = "homeEvent" ,cascade = CascadeType.ALL)
    private List<Turn> turnList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    public void addTurn(Turn turn){
        turnList.add(turn);
    }

}