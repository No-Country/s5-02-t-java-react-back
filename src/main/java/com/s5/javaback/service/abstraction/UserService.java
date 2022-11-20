package com.s5.javaback.service.abstraction;

import com.s5.javaback.model.entity.User;
import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserResponse create(UserRequest request) throws Exception;

    List<UserResponse> getAll(String status);

    Optional<UserResponse> getById(long id);

    Optional<UserResponse> getByUsernameOrEmail(String username, String email);
    User getInfoUser();

    Optional<UserResponse> update(UserRequest request,  MultipartFile image) throws Exception;

    void delete(long id) throws Exception;

    User findByUsername(String userName);
}
