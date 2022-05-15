package hyk.springframework.lostandfoundsystem.repositories;

import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Htoo Yanant Khin
 */
public interface LostFoundItemRepository extends JpaRepository<LostFoundItem, UUID> {
}
