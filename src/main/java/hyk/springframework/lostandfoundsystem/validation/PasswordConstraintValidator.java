package hyk.springframework.lostandfoundsystem.validation;

import hyk.springframework.lostandfoundsystem.domain.security.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Htoo Yanant Khin
 **/
@Component
@Slf4j
public class PasswordConstraintValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.debug("Check password constraint");
        User userDto = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");

        /*
        Password validation
        1. Between 8 and 20 (both inclusive)
        2. At least one digit
        3. At lease one lowercase, one uppercase
        4. At lease one special character
         */
        String regex  = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userDto.getPassword());
        if (! matcher.matches()) {
            log.debug("Validate user input from view layer - Invalid password");
            errors.rejectValue("password", "password.invalid");
        }
    }
}
