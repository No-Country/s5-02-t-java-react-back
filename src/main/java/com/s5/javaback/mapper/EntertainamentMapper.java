package com.s5.javaback.mapper;

import com.s5.javaback.model.entity.EntertainamentHome;
import com.s5.javaback.model.request.EntertainamentHomeRequest;
import com.s5.javaback.model.response.EntertainamentHomeResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EntertainamentMapper {
    public EntertainamentHome dtoToEntity(EntertainamentHomeRequest request){
        EntertainamentHome entertainamentHome = new EntertainamentHome();
        entertainamentHome.setCity(request.getCity());
        entertainamentHome.setName(request.getName());
        entertainamentHome.setDescription(request.getDescription());
        entertainamentHome.setPhone(request.getPhone());
        entertainamentHome.setAdress(request.getAdress());
        entertainamentHome.setPhotograpy(request.getPhotograpy());
        entertainamentHome.setState(request.getState());
        return entertainamentHome;
    }
     public EntertainamentHome update( EntertainamentHome home , EntertainamentHomeRequest request){
         home.setCity(request.getCity());
         home.setName(request.getName());
         home.setDescription(request.getDescription());
         home.setPhone(request.getPhone());
         home.setAdress(request.getAdress());
         home.setPhotograpy(request.getPhotograpy());
         home.setState(request.getState());
         return home;
     }

        public EntertainamentHomeResponse entityToDto(EntertainamentHome request){
        EntertainamentHomeResponse response = new EntertainamentHomeResponse();
            response.setCity(request.getCity());
            response.setName(request.getName());
            response.setDescription(request.getDescription());
            response.setPhone(request.getPhone());
            response.setAdress(request.getAdress());
            response.setPhotograpy(request.getPhotograpy());
            response.setState(request.getState());
            return response;

        }


}

