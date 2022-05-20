package hyk.springframework.lostandfoundsystem.services;

import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import hyk.springframework.lostandfoundsystem.enums.Type;
import hyk.springframework.lostandfoundsystem.repositories.LostFoundItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Htoo Yanant Khin
 */
@RequiredArgsConstructor
@Service
public class LostFoundItemServiceImpl implements LostFoundItemService {

    private final LostFoundItemRepository lostFoundItemRepository;

    @Override
    public List<LostFoundItem> findAllLostFoundItems() {
        return lostFoundItemRepository.findAll();
    }

    @Override
    public List<LostFoundItem> findAllLostFoundItemByUserId(Integer userId) {
        return lostFoundItemRepository.findAllByUserId(userId);
    }

    @Override
    public LostFoundItem findLostFoundItemById(UUID itemId) {
        return lostFoundItemRepository.findByIdSecure(itemId);
//        return lostFoundItemRepository.findById(itemId).orElseThrow(
//                () -> new ResourceNotFoundException("Item not found, ID: " + itemId));
    }

    @Override
    public LostFoundItem saveLostFoundItem(LostFoundItem lostFoundItem) {
//        User user = LoginUserUtil.getLoginUser();
//
//        if (lostFoundItem.getUserInfo().getId() == null) {
//            lostFoundItem.setUserInfo(
//                    userInfoRepository.findById(user.getUserInfo().getId())
//                            .orElseThrow(ResourceNotFoundException::new));
//        }

//        lostFoundItem.setCreatedBy(user.getUsername());
//        lostFoundItem.setModifiedBy(user.getUsername());

//        if (! StringUtils.hasLength(lostFoundItem.getCreatedBy())) {
//            lostFoundItem.setCreatedBy(user.getUsername());
//        }
//
//        if (! StringUtils.hasLength(lostFoundItem.getModifiedBy())) {
//            lostFoundItem.setModifiedBy(user.getUsername());
//        }

        return lostFoundItemRepository.save(lostFoundItem);
    }

    @Override
    public void deleteLostFoundItemById(UUID itemId) {
        lostFoundItemRepository.deleteByIdSecure(itemId);
    }

    @Override
    public Long countLostFoundItemByType(Type type) {
        return lostFoundItemRepository.countLostFoundItemByType(type);
    }
}
