package com.s5.javaback.service;

import com.s5.javaback.mapper.TurnMapper;
import com.s5.javaback.model.entity.HomeEvent;
import com.s5.javaback.model.entity.Turn;
import com.s5.javaback.model.request.TurnRequest;
import com.s5.javaback.model.response.TurnResponse;
import com.s5.javaback.repository.TurnRepository;
import com.s5.javaback.service.abstraction.HomeEventService;
import com.s5.javaback.service.abstraction.TurnService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class TurnServiceImpl implements TurnService {

    private final TurnRepository turnRepository;

    private final TurnMapper turnMapper;

    private final HomeEventService homeEventService;

    @Override
    public TurnResponse create(Long idHome, TurnRequest turnRequest) {
        HomeEvent event = homeEventService.getHomeBy(idHome);
        Turn turn = turnMapper.entityToDto(turnRequest);
        turn.setHomeEvent(event);
        Turn turnCreate = turnRepository.save(turn);
        event.addTurn(turnCreate);
        homeEventService.save(event);
        return turnMapper.dtoToEntity(turnCreate);
    }

    @Override
    public void deleted(Long idTurn) {
        Turn turn = getTurn(idTurn);
        turnRepository.delete(turn);
    }

    private Turn getTurn(Long idTurn) {
        Optional<Turn> turn = turnRepository.findById(idTurn);
        if(turn.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turno no encontrado");
        }
        return turn.get();
    }

    public void save(Turn entityToDto) {

        turnRepository.save(entityToDto);


    }
}
