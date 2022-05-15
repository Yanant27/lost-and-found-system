package hyk.springframework.lostandfoundsystem.services;

import hyk.springframework.lostandfoundsystem.domain.UserAccountInfo;

import java.util.List;
import java.util.UUID;

/**
 * @author Htoo Yanant Khin
 **/
public interface UserAccountInfoService {
    List<UserAccountInfo> getUserAccountInfos();

    UserAccountInfo findUserAccountInfoById(UUID id);

    UserAccountInfo saveUserAccountInfo(UserAccountInfo userAccountInfo);
}
