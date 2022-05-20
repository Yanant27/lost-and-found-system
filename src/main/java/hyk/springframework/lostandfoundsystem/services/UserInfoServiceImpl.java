package hyk.springframework.lostandfoundsystem.services;

import hyk.springframework.lostandfoundsystem.domain.UserInfo;
import hyk.springframework.lostandfoundsystem.exceptions.ResourceNotFoundException;
import hyk.springframework.lostandfoundsystem.repositories.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Htoo Yanant Khin
 **/
@RequiredArgsConstructor
@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository userInfoRepository;

    @Override
    public List<UserInfo> getUserInfos() {
        return userInfoRepository.findAll();
    }

    @Override
    public UserInfo findUserInfoById(UUID userInfoId) {
        return userInfoRepository.findById(userInfoId).orElseThrow(
                () -> new ResourceNotFoundException("UserInfo not found, ID: " + userInfoId));
    }

    @Override
    public UserInfo saveUserInfo(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }
}
