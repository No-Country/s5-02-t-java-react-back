package com.s5.javaback.mapper;

import com.s5.javaback.model.entity.HomeEvent;
import com.s5.javaback.model.request.HomeEventRequest;
import com.s5.javaback.model.response.HomeEventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class HomeEventMapper {

    private final TurnMapper turnMapper;

    public HomeEvent dtoToEntity(HomeEventRequest request){
        HomeEvent homeEvent = new HomeEvent();
        homeEvent.setCity(request.getCity());
        homeEvent.setName(request.getName());
        homeEvent.setCapacity(request.getCapacity());
        homeEvent.setDescription(request.getDescription());
        homeEvent.setPhone(request.getPhone());
        homeEvent.setAdress(request.getAdress());
        homeEvent.setPhotograpy(request.getPhotograpy());
        homeEvent.setState(request.getState());
        homeEvent.setTurnList(request.getTurnList());
        return homeEvent;
    }
     public HomeEvent update(HomeEvent home , HomeEventRequest request){
         home.setCity(request.getCity());
         home.setName(request.getName());
         home.setCapacity(home.getCapacity());
         home.setDescription(request.getDescription());
         home.setPhone(request.getPhone());
         home.setAdress(request.getAdress());
         home.setPhotograpy(request.getPhotograpy());
         home.setState(request.getState());
         return home;
     }

        public HomeEventResponse entityToDto(HomeEvent request){
        HomeEventResponse response = new HomeEventResponse();
            response.setId(request.getId());
            response.setCity(request.getCity());
            response.setCapacity(request.getCapacity());
            response.setName(request.getName());
            response.setDescription(request.getDescription());
            response.setPhone(request.getPhone());
            response.setAdress(request.getAdress());
            response.setPhotograpy(request.getPhotograpy());
            response.setState(request.getState());
            response.setTurnResponseList(request.getTurnList().stream().map(
                    turnMapper::dtoToEntity).collect(Collectors.toList())
            );
            return response;

        }


    public List<HomeEventResponse> dtoToEntityList(List<HomeEvent> homeEvents) {
        List<HomeEventResponse> responses = new ArrayList<>();
        HomeEventResponse response;
        for (HomeEvent event: homeEvents){
            response = new HomeEventResponse();
            response.setId(event.getId());
            response.setName(event.getName());
            response.setCity(event.getCity());
            response.setAdress(event.getAdress());
            response.setDescription(event.getDescription());
            response.setCapacity(event.getCapacity());
            response.setPhone(event.getPhone());
            response.setState(event.getState());
            response.setTurnResponseList(event.getTurnList().stream().map(
                    turnMapper::dtoToEntity).collect(Collectors.toList())
            );
            responses.add(response);
        }
        return responses;
    }
}

