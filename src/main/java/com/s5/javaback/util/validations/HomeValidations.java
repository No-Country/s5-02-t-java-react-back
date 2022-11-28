package com.s5.javaback.util.validations;

import com.amazonaws.services.applicationdiscovery.model.ResourceNotFoundException;
import com.s5.javaback.model.request.HomeEventRequest;
import org.springframework.stereotype.Component;

@Component
public class HomeValidations {

    public void checkListDateHome(HomeEventRequest requerst) throws ResourceNotFoundException {
        if(requerst.getName()== null || requerst.getName().isEmpty()) {
            throw new ResourceNotFoundException("Nombre no puede ser Null o estar vacio");
        }
        if(requerst.getAddress()==null|| requerst.getAddress().isEmpty()){
            throw new ResourceNotFoundException("Dirección no puede ser Null o estar vacio");
        }
        if(requerst.getPhone()==null|| requerst.getPhone().isEmpty()){
            throw new ResourceNotFoundException("Telefono no puede ser Null o estar vacio");
        }
    }
}
