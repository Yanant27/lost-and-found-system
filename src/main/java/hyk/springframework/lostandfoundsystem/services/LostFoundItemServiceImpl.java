package hyk.springframework.lostandfoundsystem.services;

import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import hyk.springframework.lostandfoundsystem.enums.Type;
import hyk.springframework.lostandfoundsystem.exceptions.ItemNotFoundException;
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
    public LostFoundItem findLostFoundItemById(UUID itemId) {
        return lostFoundItemRepository.findById(itemId).orElseThrow(
                () -> new ItemNotFoundException("Item not found, ID: " + itemId));
    }

    @Override
    public List<LostFoundItem> findLostFoundItemByAccountId(UUID accountId) {
        return lostFoundItemRepository.findAllByCurrentAccount(accountId);
    }

    @Override
    public LostFoundItem saveLostFoundItem(LostFoundItem lostFoundItem) {
        return lostFoundItemRepository.save(lostFoundItem);
    }

    @Override
    public void deleteLostFoundItemById(UUID itemId) {
        lostFoundItemRepository.deleteById(itemId);
    }

    @Override
    public Long countLostFoundItemByType(Type type) {
        return lostFoundItemRepository.countLostFoundItemByType(type);
    }
}
