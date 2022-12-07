package com.s5.javaback.service;

import com.s5.javaback.mapper.UserMapper;
import com.s5.javaback.model.entity.User;
import com.s5.javaback.model.request.AuthRequest;
import com.s5.javaback.model.request.AuthResponse;
import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.UserResponse;
import com.s5.javaback.repository.IRoleRepository;
import com.s5.javaback.repository.IUserRepository;
import com.s5.javaback.repository.ImageRepository;
import com.s5.javaback.service.abstraction.ImageService;
import com.s5.javaback.service.abstraction.UserService;
import com.s5.javaback.util.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final IUserRepository repository;
    private final IRoleRepository roleRepository;
    private final UserMapper mapper;
    private final EmailService emailService;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public UserResponse create(UserRequest request) throws Exception {
        if (!request.passwordsMatch()) {
            throw new Exception("Las contraseñas no coinciden");
        }

        if (repository.findByUsernameOrEmail(request.getUsername(), request.getEmail()).isPresent()) {
            throw new Exception("Este usuario ya está registrado");
        }

        final var user = mapper.toEntity(request);

        user.addRole(roleRepository.findById(2L).orElseThrow(() -> new Exception("Rol no encontrado.")));
        user.setImage(imageRepository.findById(1L).orElseThrow(() -> new Exception("Imagen no encontrada.")));

        final var newUser = repository.save(user);
        emailService.sendWelcome(newUser);

        return mapper.toResponse(newUser);

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
    @Transactional
    public Optional<UserResponse> update(UserRequest request, MultipartFile image) throws Exception {
        final var user = getInfoUser();
        final var img = imageService.update(user.getImage().getId(), image);

        user.setImage(img);
        user.setName(request.getName() != null ? request.getName() : user.getName());
        user.setPassword(request.getPassword());

        return Optional.of(mapper.toResponse(repository.save(user)));
    }

    @Override
    public void delete(long id) throws Exception {
        final var user = checkUser(id);
        user.setStatus(UserStatus.DISABLED);

        repository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    }

    @Override
    public AuthResponse authentication(AuthRequest request) {
       try {
           final var userId = this.getByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail())
                   .map(UserResponse::getId)
                   .orElseThrow(() -> new Exception("Algo salió mal."));
           final var jwt = "";

           return new AuthResponse(userId, jwt);
       } catch (Exception ex) {
           throw new RuntimeException(ex.getMessage());
       }
    }

    @Override
    public User getUserEntityById(long userId) throws Exception {
        return repository.findById(userId)
                .orElseThrow(() -> new Exception("Usuario con ID #" + userId + " no encontrado."));
    }

    private User checkUser(long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado!"));
    }

    @Override
    public User getInfoUser() throws Exception {
        final var userInstance = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(userInstance instanceof UserDetails)) {
            throw new UsernameNotFoundException("Something went wrong...");
        }

        return repository.findByUsername(((UserDetails) userInstance).getUsername())
                .orElseThrow(() -> new Exception("User not found."));

    }

}
