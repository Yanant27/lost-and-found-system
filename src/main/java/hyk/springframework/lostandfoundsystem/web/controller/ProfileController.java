package hyk.springframework.lostandfoundsystem.web.controller;

import hyk.springframework.lostandfoundsystem.domain.security.User;
import hyk.springframework.lostandfoundsystem.services.UserService;
import hyk.springframework.lostandfoundsystem.util.LoginUserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @author Htoo Yanant Khin
 **/
@Slf4j
@RequiredArgsConstructor
@Controller
public class ProfileController {

    private final UserService userService;

    private static final String PROFILE_UPDATE_FORM = "profile/profileForm";

    @GetMapping("/profile/{userId}")
    public String showProfileInfo(@PathVariable Integer userId, Model model) {
        checkPermission(userId);
        log.debug("LostFoundItem Controller - Show profile");
        model.addAttribute("user", userService.findUserById(userId));
        return "profile/profileDetail";
    }

    @GetMapping("/profile/{userId}/edit")
    public String initUpdateProfileForm(@PathVariable Integer userId, Model model) {
        checkPermission(userId);
        log.debug("LostFoundItem Controller - Show profile update form");
        model.addAttribute("user", userService.findUserById(userId));
        return PROFILE_UPDATE_FORM;
    }

    @PostMapping("/profile/edit")
    // @Valid parameter must be followed by BindingResult parameter
    public String processUpdateProfileForm(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return PROFILE_UPDATE_FORM;
        } else {
            User savedUser = userService.saveUser(user);
            // If username is updated, set updated username to security context
            LoginUserUtil.getLoginUser().setUsername(savedUser.getUsername());
            log.debug("LostFoundItem Controller - Process profile update");
            return "redirect:/profile/" + savedUser.getId();
        }
    }

    /**
     * To ensure that user can only have access to its data, not other user's data.
     *
     * @param userId
     */
    private void checkPermission(Integer userId) {
        log.debug("LostFoundItem Controller - Check permission");
        if (! LoginUserUtil.getLoginUser().getId().equals(userId)) {
            throw new AccessDeniedException("You don't have the permission to perform " +
                    "this operation on other user's data");
        }
    }
}
