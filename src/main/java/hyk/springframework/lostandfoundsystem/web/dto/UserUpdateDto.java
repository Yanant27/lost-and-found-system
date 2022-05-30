package hyk.springframework.lostandfoundsystem.web.dto;

import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import hyk.springframework.lostandfoundsystem.domain.security.Role;
import hyk.springframework.lostandfoundsystem.enums.State;
import hyk.springframework.lostandfoundsystem.validation.ValidPhoneNumber;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * @author Htoo Yanant Khin
 **/
@Data
//@NoArgsConstructor
public class UserUpdateDto {
    private Integer id;

    @NotEmpty
    @Size(min = 5, max = 30)
    private String username;

    @NotEmpty
    @Size(min = 5, max = 50)
    private String fullName;

    @ValidPhoneNumber
    private String phoneNumber;

    //    @ValidEmail
    @Email
    private String email;

    private String Street;

    private String city;

    private State state;

    private Set<Role> roles;

    private List<LostFoundItem> lostFoundItems;

    private Boolean accountNonLocked;

    private Boolean enabled;
}
