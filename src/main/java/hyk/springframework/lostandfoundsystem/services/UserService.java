package hyk.springframework.lostandfoundsystem.services;

import hyk.springframework.lostandfoundsystem.domain.security.User;
import hyk.springframework.lostandfoundsystem.web.dto.UserDto;

import java.util.List;

/**
 * @author Htoo Yanant Khin
 **/
public interface UserService {
    List<UserDto> findAllUsers();

    UserDto findUserById(Integer id);

    boolean isUsernameAlreadyExisted(String username, Integer userId);

    boolean isEmailAlreadyExisted(String email, Integer userId);

    User saveUser(UserDto userDto, boolean isNewPassword);

    void deleteUser(Integer id);
}
