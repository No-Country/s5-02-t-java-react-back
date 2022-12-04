package com.s5.javaback.service.abstraction;

import com.s5.javaback.model.entity.HomeEvent;
import com.s5.javaback.model.request.HomeEventRequest;
import com.s5.javaback.model.response.HomeEventResponse;
import com.s5.javaback.model.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HomeEventService {

    ResponseEntity<?> create( List<MultipartFile> postImage, HomeEventRequest request) throws Exception;
    ResponseEntity<HomeEventResponse> getById(Long id);
    ResponseEntity<HomeEventResponse> update(Long id, HomeEventRequest request) throws Exception;

    HomeEvent getHomeBy(Long idHome);

    void save(HomeEvent event);

    List<HomeEventResponse> getHomeByName(String name);

    PageResponse<HomeEventResponse> getAll(int size, int page, String dir, String type, boolean enablePage);
}
