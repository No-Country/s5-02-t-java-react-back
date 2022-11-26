package com.s5.javaback.service.abstraction;

import com.s5.javaback.model.entity.HomeEvent;
import com.s5.javaback.model.request.HomeEventRequest;
import com.s5.javaback.model.response.HomeEventResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HomeEventService {

    ResponseEntity<?> create(HomeEventRequest request) throws Exception;
    ResponseEntity<HomeEventResponse> getById(Long id);
    ResponseEntity<HomeEventResponse> update(Long id, HomeEventRequest request) throws Exception;

    HomeEvent getHomeBy(Long idHome);

    void save(HomeEvent event);

    List<HomeEventResponse> getHomeByName(String name);
}
