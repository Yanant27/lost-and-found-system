package hyk.springframework.lostandfoundsystem.repositories;

import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import hyk.springframework.lostandfoundsystem.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * @author Htoo Yanant Khin
 */
public interface LostFoundItemRepository extends JpaRepository<LostFoundItem, UUID> {

    Long countLostFoundItemByType(Type type);

    @Query("FROM LostFoundItem i where i.account.id = :accountId")
    List<LostFoundItem> findAllByCurrentAccount(UUID accountId);
}
