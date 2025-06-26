package HW3.Deneme.Controller;

import HW3.Deneme.Dto.DtoConverter;
import HW3.Deneme.Dto.ProductResponse;
import HW3.Deneme.Service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(Pageable pageable) {
        Page<ProductResponse> response = productService.getAllProducts(pageable)
                .map(DtoConverter::toDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable int id) {
        return ResponseEntity.ok(DtoConverter.toDto(productService.getProductById(id)));
    }




    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponse>> searchProducts(
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) List<String> categoryNames,
            Pageable pageable
    ) {
        Page<ProductResponse> result = productService
                .searchProducts(searchText, categoryNames, pageable)
                .map(DtoConverter::toDto);
        return ResponseEntity.ok(result);
    }


}
