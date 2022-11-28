package com.s5.javaback.service;

import com.s5.javaback.mapper.UserMapper;
import com.s5.javaback.model.entity.Image;
import com.s5.javaback.model.entity.Role;
import com.s5.javaback.model.entity.User;
import com.s5.javaback.model.request.AuthRequest;
import com.s5.javaback.model.request.AuthResponse;
import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.UserResponse;
import com.s5.javaback.repository.IRoleRepository;
import com.s5.javaback.repository.IUserRepository;
import com.s5.javaback.repository.ImageRepository;
import com.s5.javaback.security.jwt.JwtUtil;
import com.s5.javaback.security.service.UserDetailsServiceImpl;
import com.s5.javaback.service.abstraction.ImageService;
import com.s5.javaback.service.abstraction.UserService;
import com.s5.javaback.util.enums.RoleType;
import com.s5.javaback.util.enums.UserStatus;
import com.s5.javaback.util.validations.UserValidations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
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
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserValidations validate;
    @Autowired
    private UserDetailsServiceImpl serviceDetails;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private  AuthenticationManager manager;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    public UserResponse create(UserRequest request) throws Exception {
        if (!request.passwordsMatch()) {
            throw new Exception("Las contraseñas no coinciden");
        }
        if(request.getName().isEmpty()|| request.getName().isBlank()||request.getName()==null){
            throw new Exception("El nombre no puede estar vacio o ser nulo");
        }
        if(request.getUsername().isEmpty()||request.getUsername().isBlank()||request.getUsername()==null){
            throw new Exception("El username no puede estar vacio o ser nulo");
        }
        if (repository.findByUsernameOrEmail(request.getUsername(), request.getEmail()).isPresent()) {
            throw new Exception("Este usuario ya está registrado");
        }
        final var user = mapper.toEntity(request);
        user.setPassword(encoder.encode(request.getPassword()));
        user.addRole(roleRepository.findById(2L).get());
        user.setImage(imageRepository.findById(1L).get());
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
    @Transactional
    public Optional<UserResponse> update(UserRequest request,  MultipartFile image) throws Exception {
        Image img;
        User user = getInfoUser();
        img = imageService.update(user.getImage().getId(), image);
        user.setImage(img);
        user.setName(request.getName() != null ? request.getName() : user.getName());
        if (request.getPassword() != null) {
            user.setPassword(encoder.encode(request.getPassword()));
        }
        User newUser=repository.save(user);
        return Optional.of(mapper.toResponse(newUser));
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

    @Override
    public AuthResponse authentication(AuthRequest request) {
       try {
          manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(), request.getPassword()));

           final var userId = this.getByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail())
                   .map(UserResponse::getId)
                   .orElseThrow();
           final var userDetails = serviceDetails.loadUserByUsername(request.getUsernameOrEmail());
           final var jwt = jwtUtil.generateToken(userDetails);

           return new AuthResponse(userId, jwt);
       } catch (BadCredentialsException ex) {
           throw new RuntimeException(ex.getMessage());
       }
    }

    @Override
    public User getUserById(long userId) {
        Optional<User> user = repository.findById(userId);
        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Usuario no encontrado");
        }
        return user.get();
    }

    private User checkUser(long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado!"));
    }
    @Override
    public User getInfoUser() {
        String username;
        Object userInstance = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         try {
            if (userInstance instanceof UserDetails) {
                 username = (  ((UserDetails) userInstance).getUsername());
            }

        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
        return repository.findByUsername(((UserDetails) userInstance).getUsername());
    }

}
