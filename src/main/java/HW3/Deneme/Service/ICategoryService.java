package HW3.Deneme.Service;

import HW3.Deneme.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {
    Page<Category> getAllCategories(Pageable pageable);
}
