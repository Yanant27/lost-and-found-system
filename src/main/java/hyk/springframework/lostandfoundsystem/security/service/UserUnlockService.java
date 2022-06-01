package hyk.springframework.lostandfoundsystem.security.service;

import hyk.springframework.lostandfoundsystem.domain.security.User;
import hyk.springframework.lostandfoundsystem.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Htoo Yanant Khin
 **/
@Slf4j
@RequiredArgsConstructor
@Service
public class UserUnlockService {
    private final UserRepository userRepository;

    @Scheduled(fixedRate = 300000) // run every 5 mins, in real-world app, set in properties file
    public void unlockAccount() {
        log.debug("Running Unlock Accounts");
        List<User> lockedUsers = userRepository.findAllByAccountNonLockedAndLastModifiedTimestampIsBefore(
                false, Timestamp.valueOf(LocalDateTime.now().minusDays(1)));

        if (lockedUsers.size() > 0) {
            log.debug("Locked Accounts Found, Unlocking");
            lockedUsers.forEach(user -> user.setAccountNonLocked(true));
            userRepository.saveAll(lockedUsers);
        }
    }
}
