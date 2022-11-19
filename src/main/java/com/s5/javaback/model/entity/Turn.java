package com.s5.javaback.model.entity;

import com.s5.javaback.util.constants.DateFormatConstants;
import com.s5.javaback.util.enums.ConditionEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

    private LocalTime startDate; // hora inicio

    private LocalTime endDate; // hora fin

    private Double price;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = DateFormatConstants.DATE_FORMAT)
    private LocalDateTime days;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
