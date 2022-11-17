package com.s5.javaback.model.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Role {

    @Id
    @Column(name = "roles_id")
    private Long id;

    @Column(nullable = false, unique = false)
    private String name;

}
