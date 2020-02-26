package cinema.service.impl;

import cinema.dao.RoleDao;
import cinema.exceptions.AuthenticationException;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleDao roleDao;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User loginUser = userService.findByEmail(email);
        if (loginUser != null && loginUser.getPassword()
                .equals(passwordEncoder.encode(password))) {
            return loginUser;
        }
        throw new AuthenticationException("Invalid email or password");
    }

    @Override
    public User register(String email, String password) {
        User registerUser = new User();
        registerUser.setEmail(email);
        registerUser.setPassword(passwordEncoder.encode(password));
        registerUser.setRoles(roleDao.get("USER"));
        User user = userService.add(registerUser);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
