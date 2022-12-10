package com.s5.javaback.service.abstraction;

import com.s5.javaback.model.entity.User;
import com.s5.javaback.model.request.AuthRequest;
import com.s5.javaback.model.request.AuthResponse;
import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.UserResponse;
import com.s5.javaback.security.model.UserFirebase;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserResponse create() throws Exception;

    List<UserResponse> getAll(String status);

    Optional<UserResponse> getById(long id);

    Optional<UserResponse> getByEmail(String email);

    Optional<UserResponse> update(UserRequest request,  MultipartFile image) throws Exception;

    void delete() throws Exception;

    User getUserEntityById(long userId) throws Exception;

    UserFirebase getUserFirebase();

    Optional<UserResponse> getUserLogged();
}
