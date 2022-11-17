package com.s5.javaback.service;

import com.s5.javaback.mapper.UserMapper;
import com.s5.javaback.model.entity.Role;
import com.s5.javaback.model.entity.User;
import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.UserResponse;
import com.s5.javaback.repository.IRoleRepository;
import com.s5.javaback.repository.IUserRepository;
import com.s5.javaback.service.abstraction.UserService;
import com.s5.javaback.util.enums.RoleType;
import com.s5.javaback.util.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserRepository repository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    EmailService emailService;

    @Override
    public UserResponse create(UserRequest request) throws Exception {
        if (!request.passwordsMatch()) {
            throw new Exception("Las contraseñas no coinciden");
        }

        if (repository.findByUsernameOrEmail(request.getUsername(), request.getEmail()).isPresent()) {
            throw new Exception("Este usuario ya está registrado");
        }

        final var user = mapper.toEntity(request);
        user.setPassword(encoder.encode(request.getPassword()));

        final var role = roleRepository.findByName(RoleType.ROLE_USER.getName())
                .orElseGet(() -> new Role(RoleType.ROLE_USER));

        user.addRole(role);
        User userCreate = repository.save(user);
        emailService.sendWelcome(userCreate);
        return mapper.toResponse(userCreate);

    }

    @Override
    public List<UserResponse> getAll(String status) {
        if (status != null) {
            return mapper.toResponses(repository.findByStatus(UserStatus.valueOf(status)));
        }

        return mapper.toResponses(repository.findAll());
    }

    @Override
    public Optional<UserResponse> getById(long id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    public Optional<UserResponse> getByUsernameOrEmail(String username, String email) {
        return repository.findByUsernameOrEmail(username, email).map(mapper::toResponse);
    }

    @Override
    public Optional<UserResponse> update(long id, UserRequest request) throws Exception {
        final var user = checkUser(id);

        user.setName(request.getName() != null ? request.getName() : user.getName());

        if (request.getPassword() != null) {
            user.setPassword(encoder.encode(request.getPassword()));
        }

        return Optional.of(mapper.toResponse(repository.save(user)));
    }

    @Override
    public void delete(long id) throws Exception {
        final var user = checkUser(id);
        user.setStatus(UserStatus.DISABLED);

        repository.save(user);
    }

    @Override
    public User findByUsername(String userName) {
        User user = repository.findByUsername(userName);
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado");
        }
        return user;
    }

    private User checkUser(long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado!"));
    }
}
