package HW3.Deneme.Dto;


import HW3.Deneme.Entity.Orders;
import lombok.Data;

@Data
public class OrderResponse {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private int userId;
}
