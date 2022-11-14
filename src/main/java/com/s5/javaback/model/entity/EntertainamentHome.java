package com.s5.javaback.model.entity;

import com.s5.javaback.model.response.ScheduleResponse;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @Column(name = "id", nullable = false) private Long id;
    @NotBlank
    private String name ;
    @NotBlank
     private String direction ;
    @NotBlank
    private String phone ;
    @NotBlank
    private String city ;
    @NotBlank
    private  String state ;
    @NotBlank
    private String description;
    private  String photograpy;
    private List<ScheduleResponse> ScheduleResponseList;





}