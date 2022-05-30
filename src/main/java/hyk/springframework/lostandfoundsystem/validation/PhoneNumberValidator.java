package hyk.springframework.lostandfoundsystem.validation;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    /*
     +959123456789
     959425272093
     09123456789
     */
    private static final String PHONE_NUMBER_PATTERN = "(\\+)?(95)?((9\\d{9})|(09)(\\d{9}))";
    private static final Pattern PATTERN = Pattern.compile(PHONE_NUMBER_PATTERN);

    @Override
    public boolean isValid(final String phoneNumber, final ConstraintValidatorContext context) {
        log.debug("Validate phone number");
        return (validatePhoneNumber(phoneNumber));
    }

    private boolean validatePhoneNumber(final String phoneNumber) {
        Matcher matcher = PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }
}
