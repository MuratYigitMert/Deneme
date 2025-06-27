package HW3.Deneme.Service.Impl;

import HW3.Deneme.Entity.Category;
import HW3.Deneme.Entity.Product;
import HW3.Deneme.Exception.ResourceNotFoundException;
import HW3.Deneme.Repository.CategoryRepo;
import HW3.Deneme.Repository.ProductRepo;
import HW3.Deneme.Service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepo productRepo;

    private final CategoryRepo categoryRepo;

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepo.findAll(pageable);
    }
    @Override
    public Product getProductById(int id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }
    @Transactional
    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }
    @Override
    @Transactional
    public Product addProductWithImage(String name, Double price, String explanation, int categoryId, String base64Image) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setExplanation(explanation);
        product.setCategory(category);
        product.setBase64Image(base64Image);

        return productRepo.save(product);
    }


    @Override
    public Page<Product> searchProducts(String searchText, List<String> categoryNames, Pageable pageable) {
        if ((searchText == null || searchText.isEmpty()) && (categoryNames == null || categoryNames.isEmpty())) {
            return productRepo.findAll(pageable);
        }
        if (categoryNames != null && categoryNames.isEmpty()) {
            categoryNames = null;
        }

        return productRepo.findByCategoryOrProductNames(
                searchText != null ? searchText : "",
                categoryNames != null ? categoryNames.stream().map(String::toLowerCase).toList() : null,
                pageable);
    }
    @Transactional
    @Override
    public void deleteProductById(int id) {
         productRepo.deleteById(id);

    }
    @Transactional
    @Override
    public Product updateProduct(int id, Product product) {
        Product prevproduct= productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        prevproduct.setName(product.getName());
        prevproduct.setPrice(product.getPrice());
        prevproduct.setExplanation(product.getExplanation());
        prevproduct.setCategory(product.getCategory());
        prevproduct.setBase64Image(product.getBase64Image());

        return productRepo.save(prevproduct);
    }
}
