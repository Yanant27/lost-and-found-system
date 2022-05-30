package hyk.springframework.lostandfoundsystem.services;

import hyk.springframework.lostandfoundsystem.domain.Address;
import hyk.springframework.lostandfoundsystem.domain.security.Role;
import hyk.springframework.lostandfoundsystem.domain.security.User;
import hyk.springframework.lostandfoundsystem.exceptions.ResourceNotFoundException;
import hyk.springframework.lostandfoundsystem.repositories.security.RoleRepository;
import hyk.springframework.lostandfoundsystem.repositories.security.UserRepository;
import hyk.springframework.lostandfoundsystem.web.dto.UserDto;
import hyk.springframework.lostandfoundsystem.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Htoo Yanant Khin
 **/
@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    @Override
    public List<UserDto> findAllUsers() {
        log.debug("Service - Find all users");
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findUserById(Integer userId) {
        log.debug("Service - Find user by user ID: " + userId);
        return userMapper.userToUserDto(userRepository.findById(userId).orElseThrow(() -> {
            throw new ResourceNotFoundException("User Not Found with ID: " + userId);
        }));
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
    public User saveUser(UserDto userDto, boolean isNewPassword) {
        log.debug("Service - Save user with ID: " + userDto.getId());

        User user;
        if (isNewPassword) {
            user = new User();
            user.setPassword(encoder.encode(userDto.getPassword()));
        } else{
            user = userRepository.findById(userDto.getId()).orElseThrow(() -> {
                throw new ResourceNotFoundException("Requested User is not found. ID: " + userDto.getId());
            });
        }
        user.setUsername(userDto.getUsername());
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setFullName(userDto.getFullName());
        user.setAddress(Address.builder().state(userDto.getState()).city(userDto.getCity()).street(userDto.getStreet()).build());

        // Set "USER" as default role
        if (userDto.getRoles() == null || userDto.getRoles().size() == 0) {
            // In order to avoid UnsupportedOperationException, don't use "Set.of" to create role set
            Set<Role> roles = new HashSet();
            roles.add(roleRepository.findByName("USER").orElseThrow(() -> {
                throw new ResourceNotFoundException("USER role not found");
            }));
            user.setRoles(roles);
        } else {
            user.setRoles(userDto.getRoles());
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
