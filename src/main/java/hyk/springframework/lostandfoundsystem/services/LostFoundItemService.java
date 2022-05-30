package hyk.springframework.lostandfoundsystem.services;

import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import hyk.springframework.lostandfoundsystem.enums.Type;

import java.util.List;
import java.util.UUID;

public interface LostFoundItemService {

    List<LostFoundItem> findAllItems();

    List<LostFoundItem> findAllItemsByUserId(Integer userId);

    LostFoundItem findItemById(UUID itemId);

    LostFoundItem saveItem(LostFoundItem lostFoundItem);

    void deleteItemById(UUID itemId);

    Long countItemByType(Type type);
}
