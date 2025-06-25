package HW3.Deneme.Dto;

import lombok.Data;

@Data
public class RegisterResponse {
    private String username;
    private String email;
    private int roleId;
}
