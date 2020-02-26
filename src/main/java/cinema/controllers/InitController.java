package cinema.controllers;

import cinema.model.Role;
import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.UserService;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void postConstruct() {
        Role admin = new Role();
        admin.setRoleName("ADMIN");
        roleService.add(admin);
        Role user = new Role();
        user.setRoleName("USER");
        roleService.add(user);

        User userTest = new User();
        userTest.setEmail("admin@gmail.com");
        userTest.setPassword(passwordEncoder.encode("12345"));
        userTest.setRoles(admin);
        userService.add(userTest);
    }
}
