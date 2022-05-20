package hyk.springframework.lostandfoundsystem.repositories;

import hyk.springframework.lostandfoundsystem.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Htoo Yanant Khin
 **/
public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {
}
