package hyk.springframework.lostandfoundsystem.repositories;

import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import hyk.springframework.lostandfoundsystem.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author Htoo Yanant Khin
 */
public interface LostFoundItemRepository extends JpaRepository<LostFoundItem, UUID> {

    Long countLostFoundItemByType(Type type);

    @Query("select i from LostFoundItem i where i.user.id = ?1 and " +
           "(true = :#{hasAuthority('lostFoundItem.read')} or i.user.id = ?#{principal?.id})")
    List<LostFoundItem> findAllByUserId(Integer userId);

//    @Query("FROM LostFoundItem i where i.id = :itemId and i.userInfo.id = :loginUserId")
//    LostFoundItem findByItemIdAndLoginUserId(UUID itemId, UUID loginUserId);

//    ${#authentication?.principal?.userInfo.id}
//    @Transactional
    @Query("select i from LostFoundItem i where i.id =?1 and " +
           "(true = :#{hasAnyAuthority('lostFoundItem.read', 'lostFoundItem.loginUser.read')} or " +
           "i.user.id = ?#{principal?.id})")
    LostFoundItem findByIdSecure(UUID itemId);

    // To fix javax.persistence.TransactionRequiredException: Executing an update/delete query, add two annotations
    @Modifying
    @Transactional
    @Query("delete from LostFoundItem i where i.id =?1 and " +
           "(true = :#{hasAuthority('lostFoundItem.delete')} or i.user.id = ?#{principal?.id})")
    void deleteByIdSecure(UUID itemId);

//    LostFoundItem saveSecure(UUID itemId);
}
