package com.s5.javaback.service;
import com.s5.javaback.mapper.EntertainamentMapper;
import com.s5.javaback.model.entity.EntertainamentHome;
import com.s5.javaback.model.request.EntertainamentHomeRequest;
import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.EntertainamentHomeResponse;
import com.s5.javaback.repository.EntertainamentHomeRepository;
import com.s5.javaback.service.abstraction.EntertainamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EntertainamentServiceImpl implements EntertainamentService {

    @Autowired
    EntertainamentMapper mapper ;
    @Autowired
    EntertainamentHomeRepository enterRepository;
    @Override


    public ResponseEntity<?> create(EntertainamentHomeRequest request){
        try {
                EntertainamentHome e =  mapper.dtoToEntity(request);
                enterRepository.save(e);
            return new ResponseEntity<>("CREATE", HttpStatus.CREATED);
        }
        catch ( Exception ex   ) {
            return new ResponseEntity<>("Not CREATE", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public Optional<EntertainamentHomeResponse> getById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<EntertainamentHomeResponse> update(long id, EntertainamentHomeRequest request) throws Exception {
        return Optional.empty();
    }
}
