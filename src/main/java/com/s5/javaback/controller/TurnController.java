package com.s5.javaback.controller;

import com.s5.javaback.model.request.TurnRequest;
import com.s5.javaback.model.response.TurnResponse;
import com.s5.javaback.service.abstraction.TurnService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/turn")
public class TurnController {

    private final TurnService turnService;

    @ApiOperation(value ="Crear turno", notes="Crea un turno y lo asigna a una casa",
            response = ResponseEntity.class)
    @PostMapping("/create/{idHome}")
    public ResponseEntity<TurnResponse> create(@PathVariable Long idHome,@Valid @RequestBody TurnRequest turnRequest) throws Exception {
        TurnResponse response = turnService.create(idHome,turnRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value ="Borra un turno", notes="Borra un turno de la casa",
            response = ResponseEntity.class)
    @DeleteMapping("/{idTurn}")
    public ResponseEntity<Void> deleted(@PathVariable Long idTurn){
        turnService.deleted(idTurn);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
