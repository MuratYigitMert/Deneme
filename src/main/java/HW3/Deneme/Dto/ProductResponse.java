package HW3.Deneme.Dto;

import lombok.Data;

@Data
public class ProductResponse {
    private int Id;
    private int categoryId;
    private String name;
    private double price;
    private String explanation;
    private String base64Image;


}
