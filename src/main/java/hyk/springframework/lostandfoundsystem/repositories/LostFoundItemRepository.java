package hyk.springframework.lostandfoundsystem.repositories;

import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import hyk.springframework.lostandfoundsystem.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Htoo Yanant Khin
 */
public interface LostFoundItemRepository extends JpaRepository<LostFoundItem, UUID> {

    Optional<LostFoundItem> findById(UUID itemId);

    Long countLostFoundItemByType(Type type);

    List<LostFoundItem> findAllByUserId(Integer userId);
}
