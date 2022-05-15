package hyk.springframework.lostandfoundsystem.domain;

import hyk.springframework.lostandfoundsystem.domain.security.User;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author Htoo Yanant Khin
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserAccountInfo extends BaseEntity{
    private String fullName;
    private String phoneNumber;
    private String email;

    @Embedded
    private Address address;

//    @OneToOne(mappedBy = "userAccountInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToOne
    private User user;
}
