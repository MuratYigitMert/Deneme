package HW3.Deneme.Controller;

import HW3.Deneme.Entity.Product;
import HW3.Deneme.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<Product> getAllProducts(Pageable pageable) {
        return productService.getAllProducts(pageable);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }

    // Basic addProduct with raw JSON body (no image)
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // New endpoint: Upload product with base64 image from file
    @PostMapping("/upload")
    public ResponseEntity<Product> uploadProduct(
            @RequestParam("name") String name,
            @RequestParam("price") Double price,
            @RequestParam("explanation") String explanation,
            @RequestParam("categoryId") int categoryId,
            @RequestParam("image") MultipartFile imageFile
    ) throws IOException {
        String base64Image = Base64.getEncoder().encodeToString(imageFile.getBytes());
        Product saved = productService.addProductWithImage(name, price, explanation, categoryId, base64Image);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/search")
    public Page<Product> searchProducts(
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) List<String> categoryNames,
            Pageable pageable
    ) {
        return productService.searchProducts(searchText, categoryNames, pageable);
    }
}
