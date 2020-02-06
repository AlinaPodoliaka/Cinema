package cinema.service.impl;

import cinema.exceptions.AuthenticationException;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private static UserService userService;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User loginUser = userService.findByEmail(email);
        if (loginUser != null && loginUser.getPassword()
                .equals(HashUtil.hashPassword(password, loginUser.getSalt()))) {
            return loginUser;
        }
        throw new AuthenticationException("Invalid email or password");
    }

    @Override
    public User register(String email, String password) {
        User registerUser = new User();
        registerUser.setEmail(email);
        registerUser.setPassword(password);
        User user = userService.add(registerUser);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
