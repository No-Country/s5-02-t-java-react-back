package com.s5.javaback.model.entity;

import com.s5.javaback.model.response.ScheduleResponse;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private  String photograpy;


}