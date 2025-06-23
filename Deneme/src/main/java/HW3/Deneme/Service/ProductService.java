package HW3.Deneme.Service;

import HW3.Deneme.Entity.Category;
import HW3.Deneme.Entity.Product;
import HW3.Deneme.Repository.CategoryRepo;
import HW3.Deneme.Repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    private final CategoryRepo categoryRepo;

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    public Product getProductById(int id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public Product addProductWithImage(String name, Double price, String explanation, int categoryId, String base64Image) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setExplanation(explanation);
        product.setCategory(category);
        product.setBase64Image(base64Image);

        return productRepo.save(product);
    }

    public Page<Product> searchProducts(String searchText, List<String> categoryNames, Pageable pageable) {
        if ((searchText == null || searchText.isEmpty()) && (categoryNames == null || categoryNames.isEmpty())) {
            return productRepo.findAll(pageable);
        }
        return productRepo.findByCategoryOrProductNames(
                searchText != null ? searchText : "",
                categoryNames != null ? categoryNames.stream().map(String::toLowerCase).toList() : List.of(),
                pageable);
    }
}
