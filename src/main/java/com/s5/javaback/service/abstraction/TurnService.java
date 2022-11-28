package com.s5.javaback.service.abstraction;

import com.s5.javaback.model.request.TurnRequest;
import com.s5.javaback.model.response.TurnResponse;

public interface TurnService {
    TurnResponse create(Long idHome, TurnRequest turnRequest);

    void deleted(Long idTurn);
}
