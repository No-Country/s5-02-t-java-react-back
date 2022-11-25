package com.s5.javaback.controller;

import com.s5.javaback.model.request.TurnRequest;
import com.s5.javaback.model.response.TurnResponse;
import com.s5.javaback.service.abstraction.TurnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/turn")
public class TurnController {

    private final TurnService turnService;
    @PostMapping("/create/{idHome}")
    public ResponseEntity<TurnResponse> create(@PathVariable Long idHome,@Valid @RequestBody TurnRequest turnRequest){
        TurnResponse response = turnService.create(idHome,turnRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
