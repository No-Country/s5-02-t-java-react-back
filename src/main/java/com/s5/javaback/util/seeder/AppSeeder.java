package com.s5.javaback.util.seeder;
import com.s5.javaback.model.entity.Role;
import com.s5.javaback.model.entity.User;
import com.s5.javaback.repository.IRoleRepository;
import com.s5.javaback.repository.IUserRepository;
import com.s5.javaback.util.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppSeeder {
    private static final String PASSWORD = "12345678";
//    private final String[] categoryName = {"HOMBRE", "MUJER", "NIÑOS", "OFERTAS", "NUEVAS OFERTAS", "OFERTAS RELAMPAGO"};
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        // create role
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            createRoles();
        }
        // create Category

        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            createUsers();
        }
    }

    private void createRoles() {
        createRole(1L, RoleType.ADMIN);
        createRole(2L, RoleType.USER);
    }


    private void createRole(long id, RolesEnum rolesEnum) {
        Role role = new Role();
        role.setId(id);
        role.setName(rolesEnum.getFullRoleName());
        role.setDescription(rolesEnum.getName());
        role.setTimestamp(new Timestamp(System.currentTimeMillis()));
        roleRepository.save(role);
    }

    private void createUsers() {
        User user = new User();
        user.setEmail("admin@nc-events.com");
        user.setname=("No-Country");
        user.setUsername("No-Country");
        user.setPassword(passwordEncoder.encode(PASSWORD));
        user.setStatus;
        user.setRole(roleRepository.findById(1L).get());
    }
}
