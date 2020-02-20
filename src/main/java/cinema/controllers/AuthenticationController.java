package cinema.controllers;

import cinema.dto.UserRequestDto;
import cinema.exceptions.AuthenticationException;
import cinema.model.User;
import cinema.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/register")
    public User registerUser(@RequestBody UserRequestDto userRequestDto) {
        return authenticationService.register(userRequestDto.getEmail(),
                userRequestDto.getPassword());
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody UserRequestDto userRequestDto) throws AuthenticationException {

        authenticationService.login(userRequestDto.getEmail(), userRequestDto.getPassword());
        return "You have successfully login";
    }
}

