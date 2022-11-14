package com.s5.javaback.service.abstraction;

import com.s5.javaback.model.request.EntertainamentHomeRequest;
import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.EntertainamentHomeResponse;
import com.s5.javaback.model.response.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface EntertainamentService {

    ResponseEntity<?> create(EntertainamentHomeRequest request) throws Exception;
    Optional< EntertainamentHomeResponse> getById(long id);
    Optional< EntertainamentHomeResponse> update(long id,  EntertainamentHomeRequest request) throws Exception;

}
