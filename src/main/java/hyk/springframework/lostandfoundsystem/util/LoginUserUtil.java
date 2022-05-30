package hyk.springframework.lostandfoundsystem.util;

import hyk.springframework.lostandfoundsystem.domain.security.Role;
import hyk.springframework.lostandfoundsystem.domain.security.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author Htoo Yanant Khin
 **/
@Slf4j
public class LoginUserUtil {
    public static User getLoginUser() {
        log.debug("Get currently login user information");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null &&
                authentication.getPrincipal() != null &&
                authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return new User();
    }

    public static boolean isAdmin() {
        log.debug("Check currently login user is admin or not");
        User user = getLoginUser();
        Optional<Role> matchRole = user.getRoles().stream()
                .filter(role -> role.getName().equals("ADMIN"))
                .findFirst();
        return matchRole.isPresent();
    }
}
