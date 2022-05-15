package hyk.springframework.lostandfoundsystem.domain.security;

import hyk.springframework.lostandfoundsystem.domain.UserAccountInfo;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Htoo Yanant Khin
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;
    private String password;

    @Singular // use with singular form "authority"
    /*
     * fetch = FetchType.EAGER -> can degrade performance
     * Collections are lazy-loaded by default
     * Or use @Transactional of spring framework
     */
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    // owning side
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private Set<Role> roles; // plural form

    @Builder.Default
    private Boolean accountNonExpired = true;

    @Builder.Default
    private Boolean accountNonLocked = true;

    @Builder.Default
    private Boolean credentialsNonExpired = true;

    @Builder.Default
    private Boolean enabled = true;

//    @OneToOne(fetch = FetchType.EAGER)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserAccountInfo userAccountInfo;
}
