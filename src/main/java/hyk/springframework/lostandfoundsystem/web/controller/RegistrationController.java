package hyk.springframework.lostandfoundsystem.web.controller;

import hyk.springframework.lostandfoundsystem.domain.security.User;
import hyk.springframework.lostandfoundsystem.services.UserService;
import hyk.springframework.lostandfoundsystem.validation.PasswordConstraintValidator;
import hyk.springframework.lostandfoundsystem.validation.PasswordMatchingValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Htoo Yanant Khin
 **/
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final String REGISTRATION_FORM = "register";

    private final UserService userService;
    private final PasswordConstraintValidator passwordConstraintValidator;
    private final PasswordMatchingValidator passwordMatchingValidator;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;

    @GetMapping
    public String initRegistrationForm(Model model) {
        log.debug("Registration Controller - Show registration form");
        model.addAttribute("user", new User());
        return REGISTRATION_FORM;
    }

    @PostMapping
    // @Valid parameter must be followed by BindingResult parameter
    public String processRegistrationForm(@Valid @ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) {
        log.debug("Registration Controller - Process registration - Start");
        // Check password constraint
        passwordConstraintValidator.validate(user, result);

        // Check password match
        passwordMatchingValidator.validate(user, result);

        // Check duplicate username
        if (userService.isUsernameAlreadyExisted(user.getUsername(), -1)) {
            result.rejectValue("username", "username.duplicate");
        }

        // Check duplicate email
        if (userService.isEmailAlreadyExisted(user.getEmail(), -1)) {
            result.rejectValue("email", "email.duplicate");
        }

        if (result.hasErrors()) {
            return REGISTRATION_FORM;
        } else {
            String rawPassword = user.getPassword();
            user.setPassword(encoder.encode(rawPassword));
            User savedUser = userService.saveUser(user);
            authWithAuthManager(request, savedUser.getUsername(), rawPassword);
            log.debug("Registration Controller - Process registration - End");
            return "redirect:/index";
        }
    }

    /**
     * Auto login after registration.
     *
     * @param request
     * @param username
     * @param password
     */
    private void authWithAuthManager(HttpServletRequest request, String username, String password) {
        log.debug("Registration Controller - Update username and password in security context");
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
