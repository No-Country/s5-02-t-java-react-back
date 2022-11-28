package com.s5.javaback.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.s5.javaback.util.enums.ConditionEnum;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TurnResponse {

    @Enumerated(EnumType.STRING)
    private ConditionEnum conditions; // estado

    @JsonFormat(pattern="HH:mm")
    private LocalTime startDate; // hora inicio

    @JsonFormat(pattern="HH:mm")
    private LocalTime endDate; // hora fin

    private Double price;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate days;

    private String name, email;
}
