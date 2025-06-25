package HW3.Deneme.Controller;


import HW3.Deneme.Dto.DtoConverter;
import HW3.Deneme.Dto.ProductResponse;
import HW3.Deneme.Dto.RoleChangeRequest;
import HW3.Deneme.Entity.Product;
import HW3.Deneme.Entity.User;
import HW3.Deneme.Service.ProductService;
import HW3.Deneme.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")

public class AdminController {

    private final UserService userService;
    private final ProductService productService;

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/change-role")
    public ResponseEntity<?> changeUserRole(@RequestBody RoleChangeRequest request) {
        System.out.println("Inside changeUserRole...");
        System.out.println("Security Context Auth: " + SecurityContextHolder.getContext().getAuthentication());
        User updated = userService.changeUserRole(request.getUserId(), request.getRoleName());
        return ResponseEntity.ok("User role updated to " + updated.getRole().getName());
    }
    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product deleted");
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable int id, @RequestBody Product product) {
        return ResponseEntity.ok(DtoConverter.toDto(productService.updateProduct(id, product))) ;
    }
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/addProduct")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(DtoConverter.toDto(productService.addProduct(product)));
    }
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/upload")
    public ResponseEntity<ProductResponse> uploadProduct(
            @RequestParam("name") String name,
            @RequestParam("price") Double price,
            @RequestParam("explanation") String explanation,
            @RequestParam("categoryId") int categoryId,
            @RequestParam("image") MultipartFile imageFile
    ) throws IOException {
        String base64Image = Base64.getEncoder().encodeToString(imageFile.getBytes());
        Product saved = productService.addProductWithImage(name, price, explanation, categoryId, base64Image);
        return ResponseEntity.ok(DtoConverter.toDto(saved));
    }
}
