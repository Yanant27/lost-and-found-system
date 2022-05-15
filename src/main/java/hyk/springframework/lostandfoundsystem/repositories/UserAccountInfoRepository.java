package hyk.springframework.lostandfoundsystem.repositories;

import hyk.springframework.lostandfoundsystem.domain.UserAccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Htoo Yanant Khin
 **/
public interface UserAccountInfoRepository extends JpaRepository<UserAccountInfo, UUID> {
}
