package hyk.springframework.lostandfoundsystem.repositories;

import hyk.springframework.lostandfoundsystem.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Htoo Yanant Khin
 **/
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
