package com.s5.javaback.service;
import com.s5.javaback.mapper.EntertainamentMapper;
import com.s5.javaback.model.entity.EntertainamentHome;
import com.s5.javaback.model.request.EntertainamentHomeRequest;
import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.EntertainamentHomeResponse;
import com.s5.javaback.repository.EntertainamentHomeRepository;
import com.s5.javaback.service.abstraction.EntertainamentService;
import org.aspectj.lang.reflect.CatchClauseSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class EntertainamentServiceImpl implements EntertainamentService {
    @Autowired
    EntertainamentMapper mapper;
    @Autowired
    EntertainamentHomeRepository enterRepository;

    @Override
    public ResponseEntity<?> create(EntertainamentHomeRequest request) {
        try {
            EntertainamentHome e = mapper.dtoToEntity(request);
            enterRepository.save(e);
            return new ResponseEntity(e, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<EntertainamentHomeResponse> getById(Long id) {

         Optional<EntertainamentHome> h= enterRepository.findById(id);
         if (h.isPresent()){
             EntertainamentHomeResponse home =  mapper.entityToDto(h.get());
            return new ResponseEntity(home,HttpStatus.ACCEPTED);
         }
    else {
        return new ResponseEntity("empty",HttpStatus.ACCEPTED);
         }
    }
    @Override
    public ResponseEntity<EntertainamentHomeResponse> update(Long id, EntertainamentHomeRequest request) throws Exception {
        try {
            Optional<EntertainamentHome> home = enterRepository.findById(id);
            if (home.isPresent()) {
                EntertainamentHome h = mapper.update(home.get(), request);    }

                return new ResponseEntity("update", HttpStatus.ACCEPTED);

        } catch (Exception e)
        {   return new ResponseEntity("update fail", HttpStatus.BAD_REQUEST);}
    }
}
