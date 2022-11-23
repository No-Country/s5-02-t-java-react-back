package com.s5.javaback.mapper;

import com.s5.javaback.model.entity.HomeEvent;
import com.s5.javaback.model.request.HomeEventRequest;
import com.s5.javaback.model.response.HomeEventResponse;
import org.springframework.stereotype.Component;

@Component
public class HomeEventMapper {
    public HomeEvent dtoToEntity(HomeEventRequest request){
        HomeEvent entertainamentHome = new HomeEvent();
        entertainamentHome.setCity(request.getCity());
        entertainamentHome.setName(request.getName());
        entertainamentHome.setCapacity(request.getCapacity());
        entertainamentHome.setDescription(request.getDescription());
        entertainamentHome.setPhone(request.getPhone());
        entertainamentHome.setAdress(request.getAdress());
        entertainamentHome.setPhotograpy(request.getPhotograpy());
        entertainamentHome.setState(request.getState());
        entertainamentHome.setTurnList(request.getTurnList());
        return entertainamentHome;
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
            response.setCity(request.getCity());
            response.setCapacity(request.getCapacity());
            response.setName(request.getName());
            response.setDescription(request.getDescription());
            response.setPhone(request.getPhone());
            response.setAdress(request.getAdress());
            response.setPhotograpy(request.getPhotograpy());
            response.setState(request.getState());
            return response;

        }


}

