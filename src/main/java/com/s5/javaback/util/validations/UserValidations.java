package com.s5.javaback.util.validations;

public class UserValidations {
   /* public static ClientsValidationDTO checkFields(ClientsModel clientModel){
        ClientsValidationDTO response = new ClientsValidationDTO();
        if ((clientModel.getDni().isBlank()) || (clientModel.getDni() == null) || (clientModel.getDni().isEmpty())){
            response.Message = "Campo Dni vacío, por favor completar";
            response.hasError = true;
            return response;
        }
        if ((clientModel.getName().isBlank()) || (clientModel.getName() == null) || (clientModel.getName().isEmpty())){
            response.Message = "Campo Name vacío, por favor completar";
            response.hasError = true;
            return response;
        }
        if ((clientModel.getLastname().isBlank()) || (clientModel.getLastname() == null) || (clientModel.getLastname().isEmpty())){
            response.Message = "Campo Lastname vacío, por favor completar";
            response.hasError = true;
            return response;
        }
        if (clientModel.getBirth_date() == null) {
            response.Message = "Campo Birth date vacío, por favor completar";
            response.hasError = true;
            return response;
        }
        response.hasError = false;
        return response;
    }*/
}
