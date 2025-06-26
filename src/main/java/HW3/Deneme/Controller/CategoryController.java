package HW3.Deneme.Controller;


import HW3.Deneme.Dto.CategoryResponse;
import HW3.Deneme.Dto.DtoConverter;
import HW3.Deneme.Entity.Category;
import HW3.Deneme.Service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final ICategoryService ICategoryService;
    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> getAllCategories(Pageable pageable) {
        Page<Category> categories = ICategoryService.getAllCategories(pageable);
        Page<CategoryResponse> response = categories.map(DtoConverter::toDto);
        return ResponseEntity.ok(response);
    }
}
