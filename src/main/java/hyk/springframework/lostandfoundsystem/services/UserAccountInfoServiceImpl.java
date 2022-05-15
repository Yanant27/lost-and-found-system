package hyk.springframework.lostandfoundsystem.services;

import hyk.springframework.lostandfoundsystem.domain.UserAccountInfo;
import hyk.springframework.lostandfoundsystem.exceptions.ItemNotFoundException;
import hyk.springframework.lostandfoundsystem.repositories.UserAccountInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Htoo Yanant Khin
 **/
@RequiredArgsConstructor
@Service
public class UserAccountInfoServiceImpl implements UserAccountInfoService {
    private final UserAccountInfoRepository userAccountInfoRepository;

    @Override
    public List<UserAccountInfo> getUserAccountInfos() {
        return userAccountInfoRepository.findAll();
    }

    @Override
    public UserAccountInfo findUserAccountInfoById(UUID id) {
        return userAccountInfoRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("User Account not found, ID: " + id));
    }

    @Override
    public UserAccountInfo saveUserAccountInfo(UserAccountInfo userAccountInfo) {
        return userAccountInfoRepository.save(userAccountInfo);
    }
}
