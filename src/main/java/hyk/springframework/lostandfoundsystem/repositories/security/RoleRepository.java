package hyk.springframework.lostandfoundsystem.repositories.security;

import hyk.springframework.lostandfoundsystem.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Htoo Yanant Khin
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String roleName);
}
