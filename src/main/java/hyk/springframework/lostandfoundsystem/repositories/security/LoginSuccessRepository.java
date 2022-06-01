package hyk.springframework.lostandfoundsystem.repositories.security;

import hyk.springframework.lostandfoundsystem.domain.security.LoginSuccess;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Htoo Yanant Khin
 */
public interface LoginSuccessRepository extends JpaRepository<LoginSuccess, Integer> {
}