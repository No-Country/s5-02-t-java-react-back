package com.s5.javaback.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.s5.javaback.model.entity.Turn;
import com.s5.javaback.util.constants.DateFormatConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TurnRequest {
    private static final String USERID_NOT_NULL_MESSAGE = "El id del usuario no puede estar vacio!";

    //@JsonFormat(pattern="HH:mm")
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime startDate; // hora inicio

    //@JsonFormat(pattern="HH:mm")
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime endDate; // hora fin

    private Double price;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate days;

    @NotNull(message = USERID_NOT_NULL_MESSAGE)
    private long userId;

}
