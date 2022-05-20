package hyk.springframework.lostandfoundsystem.services;

import hyk.springframework.lostandfoundsystem.domain.UserInfo;

import java.util.List;
import java.util.UUID;

/**
 * @author Htoo Yanant Khin
 **/
public interface UserInfoService {
    List<UserInfo> getUserInfos();

    UserInfo findUserInfoById(UUID userInfoId);

    UserInfo saveUserInfo(UserInfo userInfo);
}
