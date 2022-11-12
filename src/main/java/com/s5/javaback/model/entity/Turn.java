package com.s5.javaback.model.entity;

import com.s5.javaback.util.constants.DateFormatConstants;
import com.s5.javaback.util.enums.ConditionEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = DateFormatConstants.DATE_TIME_FORMAT)
    private LocalDateTime startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = DateFormatConstants.DATE_TIME_FORMAT)
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
