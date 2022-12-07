package com.s5.javaback.service;

import com.google.firebase.auth.UserRecord;
import com.s5.javaback.mapper.UserMapper;
import com.s5.javaback.model.entity.Image;
import com.s5.javaback.model.entity.User;
import com.s5.javaback.model.request.AuthRequest;
import com.s5.javaback.model.request.AuthResponse;
import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.UserResponse;
import com.s5.javaback.repository.IRoleRepository;
import com.s5.javaback.repository.IUserRepository;
import com.s5.javaback.repository.ImageRepository;
import com.s5.javaback.security.model.UserFirebase;
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
    public UserResponse create() throws Exception {
        final var user = mapper.toEntity(getUserFirebase());
        final var userByEmail = repository.findByEmail(user.getEmail());

        if (userByEmail.isPresent()) {
            return mapper.toResponse(userByEmail.get());
        }

        user.addRole(roleRepository.findById(2L).orElseThrow(() -> new Exception("Rol no encontrado.")));

        final var image = new Image();
        image.setImageUrl(user.getImage().getImageUrl());
        image.setFileName("user_img");

        user.setImage(imageRepository.save(image));

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
    public Optional<UserResponse> getByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public Optional<UserResponse> update(UserRequest request, MultipartFile image) throws Exception {
        final var user = repository.findByEmail(this.getUserFirebase().getEmail())
                .orElseThrow(() -> new Exception("User not found."));

        final var img = imageService.update(user.getImage().getId(), image);

        user.setImage(img);
        user.setName(request.getName() != null ? request.getName() : user.getName());

        return Optional.of(mapper.toResponse(repository.save(user)));
    }

    @Override
    public void delete() throws Exception {
        final var user = repository.findByEmail(this.getUserFirebase().getEmail())
                .orElseThrow(() -> new Exception("User not found"));

        user.setStatus(UserStatus.DISABLED);

        repository.save(user);
    }

    @Override
    public User getUserEntityById(long userId) throws Exception {
        return repository.findById(userId)
                .orElseThrow(() -> new Exception("Usuario con ID #" + userId + " no encontrado."));
    }

    @Override
    public UserFirebase getUserFirebase() {
        return mapper.toFirebase((UserRecord) SecurityContextHolder.getContext().getAuthentication().getDetails());
    }

    @Override
    public boolean isEnabled() throws Exception {
        return repository.findByEmail(this.getUserFirebase().getEmail())
                .orElseThrow(() -> new Exception("User not found"))
                .getStatus()
                .equals(UserStatus.ENABLED);
    }

    private User checkUser(long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado!"));
    }

}
