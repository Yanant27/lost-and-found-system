package hyk.springframework.lostandfoundsystem.web.controller;

import hyk.springframework.lostandfoundsystem.domain.security.User;
import hyk.springframework.lostandfoundsystem.repositories.security.RoleRepository;
import hyk.springframework.lostandfoundsystem.services.UserService;
import hyk.springframework.lostandfoundsystem.validation.PasswordValidator;
import hyk.springframework.lostandfoundsystem.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Htoo Yanant Khin
 **/
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/usermanagement/")
public class UserManageController {
    private final String USER_UPDATE_FORM = "admin/userUpdateForm";
    private final String USER_CREATE_FORM = "admin/userCreateForm";
    private final String USER_SHOW_REDIRECT_URI = "redirect:/admin/usermanagement/show/";

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordValidator validator;

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/show")
    public String showAllUsers(Model model) {
        log.debug("UserManageController Controller - Show all users");
        model.addAttribute("users", userService.findAllUsers());
        return "admin/allUsers";
    }

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/show/{userId}")
    public String showAllUsers(@PathVariable Integer userId, Model model) {
        log.debug("UserManageController Controller - Show use by user ID: " + userId);
        model.addAttribute("user", userService.findUserById(userId));
        return "admin/userDetail";
    }

    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @GetMapping("/new")
    public String initUserCreateForm(Model model) {
        log.debug("UserManageController Controller - Show user creation form");
        model.addAttribute("user", new UserDto());
        model.addAttribute("allRoles", roleRepository.findAll());
        return USER_CREATE_FORM;
    }

    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping("/new")
    // @Valid parameter must be followed by BindingResult parameter
    public String processUserCreateForm(@Valid @ModelAttribute("user") UserDto user,
                                        BindingResult result, Model model) {
        log.debug("UserManageController Controller - Process user creation - Start");
        // Check password constraint
        validator.validate(user, result);

        // Check duplicate username
        if (userService.isUsernameAlreadyExisted(user.getUsername(), -1)) {
            result.rejectValue("username", "username.duplicate", "Username has already been taken");
        }

        // Check duplicate email
        if (userService.isEmailAlreadyExisted(user.getEmail(), -1)) {
            result.rejectValue("email", "email.duplicate", "E-mail has already been taken");
        }

        // Check confirmed password is empty or not
        if (user.getConfirmedPassword().isEmpty()) {
            result.rejectValue("confirmedPassword", "confirmedPassword.empty", "Confirmed password must be filled");
        }

        // Check password matching or not
        if (! user.getPassword().equals(user.getConfirmedPassword())) {
            result.rejectValue("confirmedPassword", "password.unmatched", "Passwords don't match");
        }

        if (result.hasErrors()) {
            model.addAttribute("allRoles", roleRepository.findAll());
            return USER_CREATE_FORM;
        } else {
            User savedUserDto = userService.saveUser(user, true);
            log.debug("UserManageController Controller - Process user creation - End");
            return USER_SHOW_REDIRECT_URI + savedUserDto.getId();
        }
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @GetMapping("/edit/{userId}")
    public String initUserUpdateForm(@PathVariable Integer userId, Model model) {
        log.debug("UserManageController Controller - Show user update form");
        model.addAttribute("user", userService.findUserById(userId));
        model.addAttribute("allRoles", roleRepository.findAll());
        return USER_UPDATE_FORM;
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PostMapping("/edit")
    // @Valid parameter must be followed by BindingResult parameter
    public String processUserUpdateForm(@Valid @ModelAttribute("user") UserDto user,
                                        BindingResult result, Model model) {
        log.debug("UserManageController Controller - Process user update - Start");
        // Check password constraint
        validator.validate(user, result);

        // Check duplicate username
        if (userService.isUsernameAlreadyExisted(user.getUsername(), user.getId())) {
            result.rejectValue("username", "username.duplicate", "Username has already been taken");
        }

        // Check duplicate email
        if (userService.isEmailAlreadyExisted(user.getEmail(), user.getId())) {
            result.rejectValue("email", "email.duplicate", "E-mail has already been taken");
        }

        if (result.hasErrors()) {
            model.addAttribute("allRoles", roleRepository.findAll());
            return USER_UPDATE_FORM;
        } else {
            User savedUser = userService.saveUser(user, false);
            log.debug("UserManageController Controller - Process user update - End");
            return USER_SHOW_REDIRECT_URI + savedUser.getId();
        }
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Integer userId) {
        log.debug("UserManageController Controller - Delete user by ID: " + userId);
        userService.deleteUser(userId);
        return USER_SHOW_REDIRECT_URI;
    }
}
