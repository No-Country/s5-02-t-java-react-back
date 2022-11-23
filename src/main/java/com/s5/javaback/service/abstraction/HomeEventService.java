package com.s5.javaback.service.abstraction;

import com.s5.javaback.model.request.HomeEventRequest;
import com.s5.javaback.model.response.HomeEventResponse;
import org.springframework.http.ResponseEntity;

public interface HomeEventService {

    ResponseEntity<?> create(HomeEventRequest request) throws Exception;
    ResponseEntity<HomeEventResponse> getById(Long id);
    ResponseEntity<HomeEventResponse> update(Long id, HomeEventRequest request) throws Exception;

}
