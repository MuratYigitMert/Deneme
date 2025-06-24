package HW3.Deneme.Controller;

import HW3.Deneme.Entity.Product;
import HW3.Deneme.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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




    @GetMapping("/search")
    public Page<Product> searchProducts(
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) List<String> categoryNames,
            Pageable pageable
    ) {
        return productService.searchProducts(searchText, categoryNames, pageable);
    }


}
