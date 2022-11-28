package com.s5.javaback.mapper;

import com.s5.javaback.model.entity.Turn;
import com.s5.javaback.model.request.TurnRequest;
import com.s5.javaback.model.response.TurnResponse;
import com.s5.javaback.service.abstraction.UserService;
import com.s5.javaback.util.enums.ConditionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TurnMapper {

    private final UserService userService;
    public Turn entityToDto(TurnRequest turnRequest) {
        return Turn.builder()
                .price(turnRequest.getPrice())
                .conditions(ConditionEnum.CONFIRMED)
                .startDate(turnRequest.getStartDate())
                .endDate(turnRequest.getEndDate())
                .days(turnRequest.getDays())
                .user(userService.getUserById(turnRequest.getUserId()))
                .build();
    }

    public TurnResponse dtoToEntity(Turn turnCreate) {
        return TurnResponse.builder()
                .conditions(turnCreate.getConditions())
                .days(turnCreate.getDays())
                .endDate(turnCreate.getEndDate())
                .price(turnCreate.getPrice())
                .name(turnCreate.getUser().getName())
                .email(turnCreate.getUser().getEmail())
                .startDate(turnCreate.getStartDate())
                .build();
    }
}
