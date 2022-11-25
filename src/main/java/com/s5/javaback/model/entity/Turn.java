package com.s5.javaback.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.s5.javaback.util.constants.DateFormatConstants;
import com.s5.javaback.util.enums.ConditionEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "turns")
public class Turn { // Turno
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ConditionEnum conditions; // estado

    @JsonFormat(pattern="HH:mm")
    private LocalTime startDate; // hora inicio

    @JsonFormat(pattern="HH:mm")
    private LocalTime endDate; // hora fin

    private Double price;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate days;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    private HomeEvent homeEvent;
}
