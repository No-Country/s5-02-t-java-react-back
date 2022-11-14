package com.s5.javaback.mapper;

import com.s5.javaback.model.entity.EntertainamentHome;
import com.s5.javaback.model.request.EntertainamentHomeRequest;
import org.springframework.stereotype.Component;

@Component
public class EntertainamentMapper {

    public EntertainamentHome dtoToEntity(EntertainamentHomeRequest request){
        EntertainamentHome entertainamentHome = new EntertainamentHome();
        entertainamentHome.setCity(request.getCity());
        entertainamentHome.setName(request.getName());
        entertainamentHome.setDescription(request.getDescription());
        entertainamentHome.setPhone(request.getPhone());
        entertainamentHome.setDirection(request.getDirection());
        entertainamentHome.setPhotograpy(request.getPhotograpy());
        return entertainamentHome;
    }


}
