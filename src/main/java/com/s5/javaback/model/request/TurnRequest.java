package com.s5.javaback.model.request;

import com.s5.javaback.util.constants.DateFormatConstants;
import com.s5.javaback.util.enums.ConditionEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TurnRequest {

    @Enumerated(EnumType.STRING)
    private ConditionEnum conditions; // estado

    private LocalTime startDate; // hora inicio

    private LocalTime endDate; // hora fin

    private Double price;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = DateFormatConstants.DATE_FORMAT)
    private LocalDateTime days;


}
