package HW3.Deneme.Dto;


import lombok.Data;

@Data
public class OrderRequest {
    private int userId;
    private int productId;
    private int quantity;
}
