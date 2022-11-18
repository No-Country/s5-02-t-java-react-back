package com.s5.javaback.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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


}