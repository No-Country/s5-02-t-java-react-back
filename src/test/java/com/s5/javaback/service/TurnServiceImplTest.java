package com.s5.javaback.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class TurnServiceImplTest {

    @InjectMocks
    private TurnServiceImpl turnService;

    @DisplayName("")
    @Test
    public void shouldThrowIncorrectExceptionWhenEventIdIsEmpty(){

    }

}