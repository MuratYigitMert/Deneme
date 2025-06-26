package HW3.Deneme.Service.Impl;

import HW3.Deneme.Entity.Category;
import HW3.Deneme.Repository.CategoryRepo;
import HW3.Deneme.Service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepo categoryRepo;

    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepo.findAll(pageable);
    }
}