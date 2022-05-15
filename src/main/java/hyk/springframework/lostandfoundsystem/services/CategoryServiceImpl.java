package hyk.springframework.lostandfoundsystem.services;

import hyk.springframework.lostandfoundsystem.domain.Category;
import hyk.springframework.lostandfoundsystem.exceptions.ItemNotFoundException;
import hyk.springframework.lostandfoundsystem.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Htoo Yanant Khin
 **/
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCagegoryById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("Category not found, ID: " + id));
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(UUID id) {
        categoryRepository.deleteById(id);
    }
}
