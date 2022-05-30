package hyk.springframework.lostandfoundsystem.repositories.security;

import hyk.springframework.lostandfoundsystem.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Htoo Yanant Khin
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameEqualsAndIdNot(String username, Integer userId);
//    List<User> findAllByAccountNonLockedAndLastModifiedDateIsBefore(boolean b, Timestamp valueOf);
    Optional<User> findById(Integer id);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailEqualsAndIdNot(String email, Integer userId);
}
