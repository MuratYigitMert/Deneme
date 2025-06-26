package HW3.Deneme.Dto;
import HW3.Deneme.Dto.ProductResponse;
import HW3.Deneme.Entity.Category;
import HW3.Deneme.Entity.Product;
import HW3.Deneme.Entity.Role;
import HW3.Deneme.Entity.User;

public class DtoConverter {
    private DtoConverter() {

    }


    public static ProductResponse toDto(Product product) {
        ProductResponse dto = new ProductResponse();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setExplanation(product.getExplanation());
        dto.setBase64Image(product.getBase64Image());
        dto.setCategoryId(product.getCategory().getId());
        return dto;
    }
    public static CategoryResponse toDto(Category category) {
        CategoryResponse dto = new CategoryResponse();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
    public static RoleResponse toDto(Role role) {
        RoleResponse dto = new RoleResponse();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }
    public static UserResponse toDto(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setRoleId(user.getRole().getId());
        return dto;
    }
}


