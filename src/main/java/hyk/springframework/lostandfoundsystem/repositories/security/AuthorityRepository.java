package hyk.springframework.lostandfoundsystem.repositories.security;

import hyk.springframework.lostandfoundsystem.domain.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Htoo Yanant Khin
 */
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
