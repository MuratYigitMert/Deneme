package HW3.Deneme.Service;

import HW3.Deneme.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Page<Product> getAllProducts(Pageable pageable);

    Product getProductById(int id);

    Product addProduct(Product product);

    Product addProductWithImage(String name, Double price, String explanation, int categoryId, String base64Image);

    Page<Product> searchProducts(String searchText, List<String> categoryNames, Pageable pageable);

    void deleteProductById(int id);

    Product updateProduct(int id, Product product);
}
