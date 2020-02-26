package cinema.dto;

import cinema.security.EmailValidator;
import cinema.security.PasswordMatcher;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatcher
public class UserRegistrationDto {
    @NotNull
    @EmailValidator
    private String email;
    @NotNull
    @Size(min = 5)
    private String password;
    @NotNull
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
