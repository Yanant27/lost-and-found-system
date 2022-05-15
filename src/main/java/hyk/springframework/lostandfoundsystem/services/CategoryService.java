package hyk.springframework.lostandfoundsystem.services;

import hyk.springframework.lostandfoundsystem.domain.Category;

import java.util.List;
import java.util.UUID;

/**
 * @author Htoo Yanant Khin
 **/
public interface CategoryService {
    List<Category> getCategories();

    Category findCagegoryById(UUID id);

    Category saveCategory(Category category);

    void deleteCategoryById(UUID id);
}
