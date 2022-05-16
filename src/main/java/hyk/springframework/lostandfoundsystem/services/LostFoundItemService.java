package hyk.springframework.lostandfoundsystem.services;

import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import hyk.springframework.lostandfoundsystem.enums.Type;

import java.util.List;
import java.util.UUID;

public interface LostFoundItemService {

    List<LostFoundItem> findAllLostFoundItems();

    LostFoundItem findLostFoundItemById(UUID itemId);

    List<LostFoundItem> findLostFoundItemByAccountId(UUID accountId);

    LostFoundItem saveLostFoundItem(LostFoundItem lostFoundItem);

    void deleteLostFoundItemById(UUID itemId);

    Long countLostFoundItemByType(Type type);
}
