package hyk.springframework.lostandfoundsystem.domain.security;

import hyk.springframework.lostandfoundsystem.domain.Address;
import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Htoo Yanant Khin
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//    @NotEmpty
//    @Size(min = 5, max = 30)
    private String username;

//    @NotEmpty
//    @ValidPassword
    private String password;

//    @NotEmpty
    @Transient
    private String confirmedPassword;

//    @NotEmpty
//    @Size(min = 5, max = 50)
    private String fullName;

//    @ValidPhoneNumber
    private String phoneNumber;

//    @ValidEmail
//    @Email
    private String email;

    @Enumerated
    private Address address;

    @Singular // use with singular form "authority"
    /*
     * fetch = FetchType.EAGER -> can degrade performance
     * Collections are lazy-loaded by default
     * Or use @Transactional of spring framework
     */
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LostFoundItem> lostFoundItems;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdTimestamp;

    @UpdateTimestamp
    private LocalDateTime lastModifiedTimestamp;

    @Transient
    public Set<GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(Role::getAuthorities)
                .flatMap(Set::stream)
                .map(authority -> {
                    return new SimpleGrantedAuthority(authority.getPermission());
                })
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
