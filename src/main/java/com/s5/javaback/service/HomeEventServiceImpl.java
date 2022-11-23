package com.s5.javaback.service;
import com.s5.javaback.mapper.HomeEventMapper;
import com.s5.javaback.model.entity.HomeEvent;
import com.s5.javaback.model.request.HomeEventRequest;
import com.s5.javaback.model.response.HomeEventResponse;
import com.s5.javaback.repository.EntertainamentHomeRepository;
import com.s5.javaback.service.abstraction.HomeEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class HomeEventServiceImpl implements HomeEventService {
    @Autowired
    HomeEventMapper mapper;
    @Autowired
    EntertainamentHomeRepository enterRepository;

    @Override
    public ResponseEntity<?> create(HomeEventRequest request) {
        try {
            HomeEvent e = mapper.dtoToEntity(request);
            enterRepository.save(e);
            return new ResponseEntity(e, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<HomeEventResponse> getById(Long id) {

         Optional<HomeEvent> h= enterRepository.findById(id);
         if (h.isPresent()){
             HomeEventResponse home =  mapper.entityToDto(h.get());
            return new ResponseEntity(home,HttpStatus.ACCEPTED);
         }
    else {
        return new ResponseEntity("empty",HttpStatus.ACCEPTED);
         }
    }
    @Override
    public ResponseEntity<HomeEventResponse> update(Long id, HomeEventRequest request) throws Exception {
        try {
            Optional<HomeEvent> home = enterRepository.findById(id);
            if (home.isPresent()) {
                HomeEvent h = mapper.update(home.get(), request);    }

                return new ResponseEntity("update", HttpStatus.ACCEPTED);

        } catch (Exception e)
        {   return new ResponseEntity("update fail", HttpStatus.BAD_REQUEST);}
    }
}