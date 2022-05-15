package hyk.springframework.lostandfoundsystem.services;

import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
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
    public List<LostFoundItem> getLostFoundItems() {
        return lostFoundItemRepository.findAll();
    }

    @Override
    public LostFoundItem findItemById(UUID id) {
        return lostFoundItemRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("Item not found, ID: " + id));
    }

    @Override
    public LostFoundItem saveItem(LostFoundItem lostFoundItem) {
        return lostFoundItemRepository.save(lostFoundItem);
    }

    @Override
    public void deleteByItemId(UUID id) {
        lostFoundItemRepository.deleteById(id);
    }
}
