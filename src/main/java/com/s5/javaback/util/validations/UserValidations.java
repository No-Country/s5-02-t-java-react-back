package com.s5.javaback.util.validations;

import com.s5.javaback.advice.response.UserValidatorException;
import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserValidations {

    public  void checkUserRequest(UserRequest request){
        UserValidatorException validation=new UserValidatorException();
        if (!request.passwordsMatch()) {
            validation.Message = "Las contraseñas no coinciden";
            validation.hasError = true;
         return;
        }
        if(request.getName().isEmpty()|| request.getName().isBlank()||request.getName()==null){
            validation.Message = "El nombre no puede estar vacio";
            validation.hasError = true;
            return;
        }
        validation.hasError = false;
    }

}
