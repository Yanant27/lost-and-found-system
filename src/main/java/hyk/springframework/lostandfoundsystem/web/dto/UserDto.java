package hyk.springframework.lostandfoundsystem.web.dto;

import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import hyk.springframework.lostandfoundsystem.domain.security.Role;
import hyk.springframework.lostandfoundsystem.enums.State;
import hyk.springframework.lostandfoundsystem.validation.ValidEmail;
import hyk.springframework.lostandfoundsystem.validation.ValidPhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * @author Htoo Yanant Khin
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;

    @Size(min = 5, max = 30)
    private String username;

    private String password;

    private String confirmedPassword;

    @Size(min = 5, max = 50)
    private String fullName;

    @ValidPhoneNumber
    private String phoneNumber;

    @ValidEmail
    private String email;

    private String street;

    private String city;

    private State state;

    private Set<Role> roles;

    private List<LostFoundItem> lostFoundItems;

    private Boolean accountNonLocked;

    private Boolean enabled;
}
