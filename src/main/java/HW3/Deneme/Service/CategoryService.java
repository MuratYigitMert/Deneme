package HW3.Deneme.Service;

import HW3.Deneme.Entity.Category;
import HW3.Deneme.Repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class CategoryService {
    public final CategoryRepo categoryRepo;
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepo.findAll(pageable);
    }
}
