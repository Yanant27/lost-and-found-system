package hyk.springframework.lostandfoundsystem.services;

import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;

import java.util.List;
import java.util.UUID;

public interface LostFoundItemService {

    List<LostFoundItem> getLostFoundItems();

    LostFoundItem findItemById(UUID id);

    LostFoundItem saveItem(LostFoundItem lostFoundItem);

    void deleteByItemId(UUID id);
}
