package cinema.controllers;

import cinema.dto.UserRequestDto;
import cinema.exceptions.AuthenticationException;
import cinema.service.AuthenticationService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class);

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/register")
    public String registerUser(@RequestBody UserRequestDto userRequestDto) {
        authenticationService.register(userRequestDto.getEmail(),
                userRequestDto.getPassword());
        return "You successfully registered";
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody UserRequestDto userRequestDto) {
        try {
            authenticationService.login(userRequestDto.getEmail(), userRequestDto.getPassword());
            return "You have successfully login";
        } catch (AuthenticationException e) {
            LOGGER.error("Cannot login user", e);
            return "Invalid email or password";
        }
    }
}

