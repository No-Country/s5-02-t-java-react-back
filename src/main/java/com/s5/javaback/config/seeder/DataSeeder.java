package com.s5.javaback.config.seeder;

import com.s5.javaback.mapper.UserMapper;
import com.s5.javaback.model.entity.Image;
import com.s5.javaback.model.entity.Role;
import com.s5.javaback.model.entity.User;
import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.repository.IRoleRepository;
import com.s5.javaback.repository.IUserRepository;
import com.s5.javaback.repository.ImageRepository;
import com.s5.javaback.util.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DataSeeder {
    private static final String PASSWORD = "12345678";
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageRepository imageRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) throws Exception {
        // create role
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            createRoles();
        }

        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            createUsers();
            createHomeEvent();
        }

    }
    private void createRoles() {
        createRole(1L, RoleType.ADMIN);
        createRole(2L, RoleType.USER);
    }
    private void createRole(long id, RoleType rolesEnum) {
        roleRepository.save(new Role(id, rolesEnum.getFullRoleName()));
    }

    private void createUsers() throws Exception {
        final var role = roleRepository.findById(1L).orElseThrow(() -> new Exception("Role not found."));
        final var userReq = new UserRequest("No-Country", "NC-Eventos", "admin@nc-eventos.com", PASSWORD, PASSWORD);
        final var user = userMapper.toEntity(userReq);

        user.addRole(role);
        user.setImage(new Image());
        createImage(user);
    }
    private void createImage(@Valid User user) {
        Image img = new Image();
        img.setImageUrl("https://group6nocountrygnavarro.s3.amazonaws.com/1664630878400_user.webp");
        img.setFileName("user_img");
        Image create = imageRepository.save(img);
        user.setImage(create);
        userRepository.save(user);
    }
    private void createHomeEvent() {
        Image img = new Image();
        img.setImageUrl("https://groups5nocontry.s3.amazonaws.com/1669647595714_salon-de-fiestas.jpeg");
        img.setFileName("home_img");
        Image create = imageRepository.save(img);
    }
}
