package com.s5.javaback.config.seeder;

import com.s5.javaback.model.entity.Image;
import com.s5.javaback.model.entity.Role;
import com.s5.javaback.model.entity.User;
import com.s5.javaback.repository.IRoleRepository;
import com.s5.javaback.repository.IUserRepository;
import com.s5.javaback.repository.ImageRepository;
import com.s5.javaback.util.enums.RoleType;
import com.s5.javaback.util.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class DataSeeder {
    private static final String PASSWORD = "12345678";
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        // create role
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            createRoles();
        }

        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            createUsers();
        }
    }
    private void createRoles() {
        createRole(1L, RoleType.ADMIN);
        createRole(2L, RoleType.USER);
    }
    private void createRole(long id, RoleType rolesEnum) {
        Role role = new Role();
        role.setId(id);
        role.setName(rolesEnum.getFullRoleName());
        roleRepository.save(role);
    }

    private void createUsers() {

        Role r = roleRepository.findById(1L).get();
        User user = new User();
        user.setEmail("admin@nc-eventos.com");
        user.setUsername("NC-Eventos");
        user.setPassword(passwordEncoder.encode(PASSWORD));
        user.setName("No-Country");
        user.setStatus(UserStatus.ENABLED);
        user.addRole(r);
        user.setImage(new Image());
        createImage(user);
    }
    private void createImage(User user) {
        Image img = new Image();
        img.setImageUrl("https://group6nocountrygnavarro.s3.amazonaws.com/1664630878400_user.webp");
        img.setFileName("user_img");
        Image create = imageRepository.save(img);
        user.setImage(create);
        userRepository.save(user);
    }
}
