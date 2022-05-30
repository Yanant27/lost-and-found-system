package hyk.springframework.lostandfoundsystem.services;

import hyk.springframework.lostandfoundsystem.domain.security.Role;
import hyk.springframework.lostandfoundsystem.domain.security.User;
import hyk.springframework.lostandfoundsystem.exceptions.ResourceNotFoundException;
import hyk.springframework.lostandfoundsystem.repositories.security.RoleRepository;
import hyk.springframework.lostandfoundsystem.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Htoo Yanant Khin
 **/
@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public List<User> findAllUsers() {
        log.debug("Service - Find all users");
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Integer userId) {
        log.debug("Service - Find user by user ID: " + userId);
        return userRepository.findById(userId).orElseThrow(() -> {
            throw new ResourceNotFoundException("User Not Found with ID: " + userId);
        });
    }

    @Override
    public boolean isUsernameAlreadyExisted(String username, Integer userId) {
        log.debug("Service - Check duplicate username");
        if (userId > 0) {
            return userRepository.findByUsernameEqualsAndIdNot(username, userId).isPresent();
        } else {
            return userRepository.findByUsername(username).isPresent();
        }
    }

    @Override
    public boolean isEmailAlreadyExisted(String email, Integer userId) {
        log.debug("Service - Check duplicate email");
        if (userId > 0) {
            return userRepository.findByEmailEqualsAndIdNot(email, userId).isPresent();
        } else {
            return userRepository.findByEmail(email).isPresent();
        }

    }

    @Override
    public User saveUser(User user) {
        log.debug("Service - Save user with ID: " + user.getId());

        // Set "USER" as default role
        if (user.getRoles() == null || user.getRoles().size() == 0) {
            // In order to avoid UnsupportedOperationException, don't use "Set.of" to create role set
            Set<Role> roles = new HashSet();
            roles.add(roleRepository.findByName("USER").orElseThrow(() -> {
                throw new ResourceNotFoundException("USER role not found");
            }));
            user.setRoles(roles);
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        if (findUserById(userId) != null) {
            log.debug("Service - Delete user with ID: " + userId);
            userRepository.deleteById(userId);
        } else {
            throw new ResourceNotFoundException("Requested User is not found. ID: " + userId);
        }
    }
}
