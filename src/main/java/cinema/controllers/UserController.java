package cinema.controllers;

import cinema.dto.UserRequestDto;
import cinema.dto.UserResponseDto;
import cinema.model.User;
import cinema.service.UserService;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/add")
    public String add(@RequestBody @Valid UserRequestDto userDto) {
        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        userService.add(user);
        return "You successfully added user";
    }

    @GetMapping(value = "/byemail")
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        UserResponseDto userDto = new UserResponseDto();
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
