package com.s5.javaback.service;

import com.s5.javaback.model.entity.HomeEvent;
import com.s5.javaback.model.request.TurnRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class TurnServiceImplTest {

    @InjectMocks
    private TurnServiceImpl turnService;


    @DisplayName("")
    @Test
    public void shouldThrowIncorrectExceptionWhenEventIdIsEmpty(){
        //setup
        TurnRequest request = new TurnRequest();
        request.setDays(LocalDate.parse("2022-06-18"));
        request.setPrice(2000.00);
        request.setEndDate(LocalTime.parse("19:24"));
        request.setStartDate(LocalTime.parse("22:12"));
        request.setUserId(2L);
        HomeEvent home = new HomeEvent();

        /*
        home.setId(1L);
        home.setAdress("address");
        home.setCity("city");
        home.setCapacity("10");
        home.setName("name");
        home.setDescription("description");
        home.setPhone("12345678");
        home.setPhotograpy("");
        home.setState("state");
        */
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class,
                () -> turnService.create(home.getId() ,request));

        Assertions.assertEquals("Esta casa de eventos no esta registrada", exception.getMessage());

    }

}