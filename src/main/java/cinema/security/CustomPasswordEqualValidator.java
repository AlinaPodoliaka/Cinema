package cinema.security;

import cinema.dto.UserRegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomPasswordEqualValidator implements
        ConstraintValidator<PasswordMatcher, UserRegistrationDto> {
    @Override
    public boolean isValid(UserRegistrationDto user,
                           ConstraintValidatorContext constraintValidatorContext) {
        return user.getPassword()
                .equals(user.getRepeatPassword());
    }
}
